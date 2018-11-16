package syntax.internal;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.var;
import util.Constants;

import java.util.*;
import java.util.stream.Collectors;

import static util.Constants.DOLLAR_SYMBOL;


@AllArgsConstructor
public class LRDFA {
    @Getter
    private LRDFANode startState;
    @Getter
    private List<LRDFANode> endStates;

    @Getter
    private List<LRDFANode> allNodes;



    public static LRDFA constructDFA(ProductionList productionList) {


        // 1. construct LR(0) item set

        LRDFA dfa = constructLR0DFA(productionList);

        // 2. add lookahead symbols


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

            for (Symbol symbol: node.getMovableSymbols()) {

                // inter-state expansion. shift point
                List<LRItem> shifted = new ArrayList<>();
                for (var item: node.getLrItems()) {
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
                for (LRDFANode e: nodes) {
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
                                var newItem = newLrItem.addLookaheadSymbol(x);
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
    public static LRDFA addLookaheadSymbols(LRDFA dfa, ProductionList productionList) {

        @EqualsAndHashCode
        @AllArgsConstructor
        class StackItem {
            LRDFANode node;
            LRItem lrItem;
        }

        var stack = new Stack<StackItem>();
        var resultSet = new HashSet<StackItem>();


        // 1. add $r to the start production
        var startNode = dfa.getStartState();
        var startItem = startNode.getKernel().get(0).addLookaheadSymbol(DOLLAR_SYMBOL);
        var stackItem = new StackItem(startNode, startItem);
        stack.push(stackItem);
        resultSet.add(stackItem);

        while (!stack.empty()) {
            var item = stack.peek();




        }


        return null;

    }
}
