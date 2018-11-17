package lex;

import lex.internal.*;
import lex.token.Token;
import lex.token.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;
import symboltable.SymbolTable;
import util.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class LexicalAnalyzer implements Iterator<Token> {


    @Getter
    private ListIterator<Character> characterIterator;

    @Getter
    private SymbolTable symbolTable;

    @Getter
    private DFA dfa;

    public LexicalAnalyzer(ListIterator<Character> characterIterator, SymbolTable symbolTable, DFA dfa) {
        this.characterIterator = characterIterator;
        this.symbolTable = symbolTable;
        this.dfa = dfa;
    }

    public static LexicalAnalyzer construct(ListIterator<Character> characterIterator, SymbolTable symbolTable, List<Rule> rules) {

        DFA dfa = constructDFA(rules);

        return new LexicalAnalyzer(characterIterator, symbolTable, dfa);
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


    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        try {
            var token = next();
            for (int i =0;i<token.getLexeme().length();i++) {
                characterIterator.previous();
            }
            return true;
        } catch (LexicalParseException e) {
            return false;
        }
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Token next() {

        StringBuilder buffer = new StringBuilder();
        DFANode position = dfa.getStart();

        while (characterIterator.hasNext()) {

            // try move one more char
            char c = characterIterator.next();
            DFANode newPosition = position.move(c);

            if (newPosition == null) {
                // can't move, reverse and token from previous position

                characterIterator.previous();

                if (position.isEndState()) {
                    return new Token(
                        buffer.toString(),
                        position.getEndStateTokenTypes().get(0)
                    );
                } else {
                    // find the next expected chars
                    String more = position.getEdges().keySet().stream().map(Object::toString).collect(Collectors.joining(", "));
                    throw new LexicalParseException(
                        "Expect: " + more
                        );
                }
            } else {
                // can move. move one more
                buffer.append(c);
                position = newPosition;
            }

        }

        if (position.isEndState()) {
            return new Token(buffer.toString(), position.getEndStateTokenTypes().get(0));
        }

        String more = position.getEdges().keySet().stream().map(Object::toString).collect(Collectors.joining(", "));
        throw new LexicalParseException(
            String.format(
                "Unexpected end of input. Expect: %s",
                more
            )
        );
    }
}
