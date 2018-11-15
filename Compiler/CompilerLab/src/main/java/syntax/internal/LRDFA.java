package syntax.internal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;

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



    public static LRDFA constructDFA(ProductionList productionList) {


        // 1. construct LR(0) item set

        LRDFA dfa = constructLR0DFA(productionList);

        // 2. add lookahead symbols


        return dfa;


    }


    // Construct LR0 DFA
    public static LRDFA constructLR0DFA(ProductionList productionList) {


        Production startProduction = productionList.getStartProduction();
        List<Production> productions = productionList.getProductions();

        List<LRDFANode> nodes = new ArrayList<>();

        Stack<LRDFANode> stack = new Stack<>();

        // create initial node

        LRItem startItem = new LRItem(startProduction, 0);
        List<LRItem> firstKernel = Collections.singletonList(startItem);

        List<LRItem> closure = closure(firstKernel, productions);
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
                List<LRItem> newClosure = closure(shifted, productions);

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
    public static List<LRItem> closure(List<LRItem> kernel, List<Production> productions) {
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
                for (var production : productions) {
                    if (production.getLeft().equals(leftSymbol)) {
                        var newLrItem = new LRItem(production, 0);
                        if (!result.contains(newLrItem)) {
                            result.add(newLrItem);
                            stack.push(newLrItem);
                        }
                    }
                }

            }
        }


        return result;

    }


    // add lookahead symbols
    public static void addLookaheadSymbols(LRDFA dfa, ProductionList productionList) {



    }
}
