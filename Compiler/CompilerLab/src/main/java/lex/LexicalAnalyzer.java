package lex;

import lex.internal.*;
import lex.token.Token;
import lombok.Getter;
import symboltable.SymbolTable;
import util.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class LexicalAnalyzer {

    private int index = 0;

    @Getter
    private String input;

    @Getter
    private SymbolTable symbolTable;

    @Getter
    private DFA dfa;

    public LexicalAnalyzer(String input, SymbolTable symbolTable, DFA dfa) {
        this.input = input;
        this.symbolTable = symbolTable;
        this.dfa = dfa;
    }

    public static LexicalAnalyzer construct(String input, SymbolTable symbolTable, List<Rule> rules) {

        DFA dfa = constructDFA(rules);

        return new LexicalAnalyzer(input ,symbolTable, dfa);
    }

    public static DFA constructDFA(List<Rule> rules) {
        List<NFA> subNFAs = rules.stream().map(NFA::constructNFA).collect(Collectors.toList());

        NFANode dummyEndState = new NFANode();
        NFANode startState = new NFANode();

        startState.getEdges().put(Constants.EPSILON,
            subNFAs.stream()
                .map(NFA::getStart)
                .collect(Collectors.toList())
        );

        NFA finalNFA = new NFA(startState, dummyEndState);

        return DFA.constructDFA(finalNFA);

    }


    private Token returnToken(String str, DFANode position) {
        if (position.isEndState()) {
            return new Token(str, position.getEndStateTokenTypes().get(0));
        } else {
            LexicalParseException.raise("Error when returning token.");
            // never reaches
            return null;
        }
    }

    public void resetIndex() {
        index = 0;
    }

    public List<Token> getAllRemainingTokens() {
        List<Token> result = new ArrayList<>();

        Token t;
        while ((t = getNextToken()) != null) {
            result.add(t);
        }
        return result;
    }

    public Token getNextToken() {

        StringBuilder buffer = new StringBuilder();
        DFANode position = dfa.getStart();

        while (index < input.length() + 1) {

            if (index == input.length()) {
                // read complete.
                // returns token immediately
                // increment prevents repeated read
                index++;
                return returnToken(buffer.toString(), position);
            }

            // try move one more char
            char c = input.charAt(index);
            DFANode newPosition = position.move(c);

            if (newPosition == null) {
                // can't move, try return token from previous position
                return returnToken(buffer.toString(), position);
            } else {
                // can move. move one more
                buffer.append(c);
                position = newPosition;
                index++;
            }

        }

        return null;
    }

}
