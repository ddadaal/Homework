package lex.internal;

import lex.Rule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import util.Constants;

import java.util.Stack;

@AllArgsConstructor
public class NFA {
    @Getter private NFANode start;
    @Getter private NFANode end;

    public static NFA constructNFA(Rule rule) {


        Regex regex = Regex.constructRegex(rule.getRegex());

        Stack<NFA> stack = new Stack<>();

        for (RegexNode node: regex.getNodes()) {
            switch (node.getType()) {
                case CHAR:
                    stack.push(primitiveNFA(node.getLexeme()));
                    break;
                case CONCATENATION: {
                    NFA n2 = stack.pop();
                    NFA n1 = stack.pop();
                    stack.push(concatenateNFA(n1, n2));
                    break;
                }
                case STAR: {
                    NFA n = stack.pop();
                    stack.push(starNFA(n));
                    break;
                }
                case OR: {
                    NFA n2 = stack.pop();
                    NFA n1 = stack.pop();
                    stack.push(orNFA(n1, n2));
                    break;
                }
                default:
                    // never happens
            }


        }

        NFA resultNFA = stack.pop();

        resultNFA.getEnd().setEndStateToken(rule.getTokenType());

        return resultNFA;
    }

    private static NFA starNFA(NFA n) {
        NFANode start = new NFANode();
        NFANode end = new NFANode();


        start.addEdges(Constants.EPSILON, n.getStart(), end);

        n.getEnd().addEdges(Constants.EPSILON, n.getStart(), end);

        return new NFA(start, end);
    }

    private static NFA orNFA(NFA n1, NFA n2) {
        NFANode start = new NFANode();

        start.addEdges(Constants.EPSILON, n1.getStart(), n2.getStart());

        NFANode end = new NFANode();

        n1.getEnd().addEdges(Constants.EPSILON, end);
        n2.getEnd().addEdges(Constants.EPSILON, end);

        return new NFA(start, end);
    }

    private static NFA concatenateNFA(NFA n1, NFA n2) {
        NFANode start = n1.getStart();
        n1.getEnd().addEdges(Constants.EPSILON, n2.getStart());
        NFANode end = n2.getEnd();

        return new NFA(start, end);

    }

    private static NFA primitiveNFA(char c) {
        NFANode start = new NFANode();
        NFANode end = new NFANode();
        start.addEdges(c, end);

        return new NFA(start, end);
    }

}
