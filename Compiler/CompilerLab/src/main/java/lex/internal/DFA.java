package lex.internal;

import lex.token.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;
import util.Constants;

import java.util.*;

@AllArgsConstructor
public class DFA {

    @Getter private DFANode start;

    @Getter private List<DFANode> end;


    private static DFANode getUnmarkedDFA(Map<DFANode, Boolean> map) {
        for (var entry : map.entrySet()) {
            if (!entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static DFA constructDFA(NFA nfa) {

        Map<DFANode, Boolean> existingNodes = new HashMap<>();

        // add initial state s0

        var closure = epsilonClosure(Arrays.asList(nfa.getStart()));
        DFANode s0 = new DFANode(closure);
        existingNodes.put(s0, false);

        DFANode node;
        while ((node = getUnmarkedDFA(existingNodes)) != null) {
            existingNodes.put(node, true);

            for (char c: node.getEdgeCharset()) {

                // get move set
                Set<NFANode> move = new HashSet<>();
                for (NFANode nfaNode: node.getNfaNodes()) {
                    move.addAll(nfaNode.move(c));
                }

                // e-closure
                var eclosure = epsilonClosure(new ArrayList<>(move));

                // new state acquired
                DFANode newNode = new DFANode(eclosure);

                // if the state doesn't exist, add it
                DFANode existing = null;
                for (var entry : existingNodes.entrySet()) {
                    if (entry.getKey().equals(newNode)) {
                        existing = entry.getKey();
                        break;
                    }
                }

                // not found. so it's a new state
                if (existing == null) {
                    existingNodes.put(newNode, false);
                    node.setTarget(c, newNode);
                } else {
                    // found. connect them
                    node.setTarget(c, existing);
                }


            }



        }

        // calculate end states
        List<DFANode> endStates = new ArrayList<>();

        for (DFANode d: existingNodes.keySet()) {
            List<TokenType> endStateTokenTypes = new ArrayList<>();

            for (NFANode nfaNode: d.getNfaNodes()) {
                if (nfaNode.getEndStateToken() != null) {
                    endStateTokenTypes.add(nfaNode.getEndStateToken());
                }
            }

            if (endStateTokenTypes.size() >0) {
                d.setEndStateTokenTypes(endStateTokenTypes);
                endStates.add(d);
            }
        }

        return new DFA(s0, endStates);
    }

    private static List<NFANode> epsilonClosure(List<NFANode> kernel) {
        List<NFANode> results = new ArrayList<>(kernel);

        Stack<NFANode> stack = new Stack<>();

        // 1. push all items in nodes to stack
        for (NFANode node: kernel) {
            stack.push(node);
        }

        // 2. dfs!
        while (!stack.empty()) {
            NFANode node = stack.pop();

            // find all targets of epsilon edge of the node
            List<NFANode> targets = node.move(Constants.EPSILON);

            for (NFANode target : targets) {
                // if target is not in results, add it into it and also push it into stack
                if (!results.contains(target)) {
                    results.add(target);
                    stack.push(target);
                }
            }
        }

        return results;

    }

}
