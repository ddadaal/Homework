package syntax.internal;


import lombok.*;
import util.Constants;

import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
public class LRDFA {
    @Getter
    private LRDFANode startState;
    @Getter
    private List<LRDFANode> endStates;

    @Getter
    private List<LRDFANode> allNodes;


    public static LRDFA constructLALRDFA(ProductionList productionList) {


        // 1. construct LR(0) item set

        LRDFA dfa = constructLR0DFA(productionList);

        // 2. add lookahead symbols

        addLookaheadSymbols(dfa,productionList);

        // 3 return the LALR(1) DFA

        return dfa;


    }


    // Construct LR0 DFA
    public static LRDFA constructLR0DFA(ProductionList productionList) {


        var startProduction = productionList.getStartProduction();

        var nodes = new ArrayList<LRDFANode>();

        var stack = new Stack<LRDFANode>();

        // create initial node

        var startItem = new LRItem(startProduction, 0);
        var firstKernel = Collections.singletonList(startItem);

        List<LRItem> closure = closure(firstKernel, productionList);
        var startNode = new LRDFANode(firstKernel, closure);

        nodes.add(startNode);
        stack.push(startNode);

        while (!stack.empty()) {
            LRDFANode node = stack.pop();

            for (Symbol symbol : node.getMovableSymbols()) {

                // inter-state expansion. shift point
                List<LRItem> shifted = new ArrayList<>();
                for (var item : node.getLrItems()) {
                    if (symbol.equals(item.getSymbolAfterDot())) {
                        shifted.add(item.shift());
                    }
                }

                // instate expansion
                List<LRItem> newClosure = closure(shifted, productionList);

                // construct node
                var newNode = new LRDFANode(shifted, newClosure);

                LRDFANode existing = null;

                // find existing node if there is
                for (LRDFANode e : nodes) {
                    if (e.equals(newNode)) {
                        existing = e;
                        break;
                    }
                }

                // there is existing node. connect them
                if (existing != null) {
                    node.setEdge(symbol, existing);
                } else {
                    // there isn't. connect them and add
                    node.setEdge(symbol, newNode);
                    nodes.add(newNode);
                    stack.push(newNode);
                }


            }
        }

        List<LRDFANode> endStates = nodes.stream().filter(LRDFANode::isEndState).collect(Collectors.toList());

        return new LRDFA(startNode, endStates, nodes);

    }


    // epsilon closure
    // if an item is a LR(1) item, the lookahead symbols will also be added
    public static List<LRItem> closure(List<LRItem> kernel, ProductionList productionList) {

        List<LRItem> result = new ArrayList<>(kernel);

        Stack<LRItem> stack = new Stack<>();

        for (var item : kernel) {
            stack.push(item);
        }


        while (!stack.isEmpty()) {
            LRItem item = stack.pop();


            if (item.isReducible()) {
                continue;
            }
            Symbol leftSymbol = item.getSymbolAfterDot();
            if (leftSymbol.isNonTerminalSymbol()) {
                for (var production : productionList.getProductions()) {
                    if (production.getLeft().equals(leftSymbol)) {
                        var newLrItem = new LRItem(production, 0);
                        if (item.isLALR1Item()) {
                            // if the item is a LALR(1) item, lookahead item should also be added
                            // skip the next symbol first
                            var shifted = item.shift();
                            Set<Symbol> lookaheadSymbols = new HashSet<>();
                            if (shifted.isReducible()) {
                                // if the item is reducible, add the lookahead symbol in it
                                lookaheadSymbols.add(shifted.getLookaheadSymbol());
                            } else {
                                // if not reducible, add all first set in it
                                lookaheadSymbols.addAll(productionList.first(shifted.getSymbolAfterDot()));
                            }

                            // for all lookahead symbols, add the item into result
                            for (var x : lookaheadSymbols) {
                                var newItem = newLrItem.setLookaheadSymbol(x);
                                if (!result.contains(newItem)) {
                                    result.add(newItem);
                                    stack.push(newItem);
                                }
                            }
                        } else {
                            if (!result.contains(newLrItem)) {
                                result.add(newLrItem);
                                stack.push(newLrItem);
                            }
                        }

                    }
                }

            }
        }


        return result;

    }


    // add lookahead symbols
    public static void addLookaheadSymbols(LRDFA dfa, ProductionList productionList) {

        @EqualsAndHashCode
        @AllArgsConstructor
        class StateSpecificLRItem {
            LRDFANode node;
            LRItem lrItem;

            @Override
            public String toString() {
                return String.format("%s: %s", node.hashCode(), lrItem.toString());
            }
        }

        // 1. construct propagate map and spontaneously generated map.
        // during this process, all spontaneously generated lookahead symbol will be recorded into resultLookaheadsSymbolMap
        // and propagated relation (lookaheads of Key StateSpecificLRItem will be propagated to the Value StateSpecificLRItem) will be recorded into propagateMap

        var propagateMap = new HashMap<StateSpecificLRItem, List<StateSpecificLRItem>>();
        var resultLookaheadsSymbolMap = new HashMap<StateSpecificLRItem, List<Symbol>>();

        // add $R as spontaneously generated symbol for the S' -> S
        resultLookaheadsSymbolMap.put(
            new StateSpecificLRItem(dfa.getStartState(), dfa.getStartState().getKernel().get(0)),
            new ArrayList<>(Collections.singletonList(Constants.DOLLAR_SYMBOL))
        );


        val externalSymbol = Constants.EXTERNAL_SYMBOL;

        for (var node : dfa.getAllNodes()) {
            for (var item : node.getKernel()) {

                var stateSpecificKernelItem = new StateSpecificLRItem(node, item);

                // add all kernel items into spontaneously generated map
                resultLookaheadsSymbolMap.putIfAbsent(stateSpecificKernelItem, new ArrayList<>());

                for (var symbol : node.getMovableSymbols()) {

                    var targetNode = node.goTo(symbol);

                    var list = propagateMap.computeIfAbsent(stateSpecificKernelItem, k -> new ArrayList<>());

                    var probeItem = item.setLookaheadSymbol(externalSymbol);

                    var closure = closure(Collections.singletonList(probeItem), productionList);

                    for (var closureItem : closure) {
                        if (symbol.equals(closureItem.getSymbolAfterDot())) {

                            // remove the lookahead symbol
                            var shifted = closureItem.shift().setLookaheadSymbol(null);
                            var targetSSLRItem = new StateSpecificLRItem(targetNode, shifted);

                            if (closureItem.getLookaheadSymbol().equals(externalSymbol)) {
                                // this closure item is propagated.
                                // add the shifted item without the lookahead symbol

                                list.add(targetSSLRItem);

                            } else {
                                // this closureItem's symbol is spontaneously generated
                                // add it into the record
                                var listOfTargetSSLRItem = resultLookaheadsSymbolMap.computeIfAbsent(targetSSLRItem, k -> new ArrayList<>());
                                listOfTargetSSLRItem.add(closureItem.getLookaheadSymbol());
                            }
                        }
                    }

                }
            }
        }


        // remove empty propagated map entries
        propagateMap.entrySet().removeIf(x -> x.getValue().isEmpty());

        // 2. propagate lookaheads into resultLookaheadsSymbolMap

        boolean added = true;

        while (added) {
            added = false;
            for (var stateSpecificKernelItem : resultLookaheadsSymbolMap.keySet()) {
                var existingLookaheads = resultLookaheadsSymbolMap.get(stateSpecificKernelItem);

                var propagateTargetLookaheads = propagateMap.get(stateSpecificKernelItem);

                if (propagateTargetLookaheads == null) {
                    continue;
                }

                for (var propagateTarget : propagateTargetLookaheads) {
                    var list = resultLookaheadsSymbolMap.get(propagateTarget);
                    for (var lookahead : existingLookaheads) {
                        if (!list.contains(lookahead)) {
                            list.add(lookahead);
                            added = true;
                        }
                    }
                }
            }

        }


        // add the symbols back to the original DFA and result in final LALR(1) DFA!!!!!

        // clear up the kernel and lritems for each nodes
        for (var node: dfa.getAllNodes()) {
            node.setLrItems(new ArrayList<>());
            node.setKernel(new ArrayList<>());
        }

        for (var entry: resultLookaheadsSymbolMap.entrySet()) {
            var key = entry.getKey();
            var list = entry.getValue();

            var newKernel = list.stream()
                .map(symbol -> key.lrItem.setLookaheadSymbol(symbol))
                .distinct()
                .collect(Collectors.toList());

            var closure = closure(newKernel, productionList);

            key.node.getKernel().addAll(newKernel);
            key.node.getLrItems().addAll(closure);
        }


    }
}
