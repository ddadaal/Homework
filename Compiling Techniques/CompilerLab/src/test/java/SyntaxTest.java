import lex.token.TokenType;
import lombok.var;
import org.junit.Test;
import syntax.SyntaxAnalyzer;
import syntax.internal.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static util.Constants.AUGMENTED_START_SYMBOL;
import static util.Constants.DOLLAR_SYMBOL;


public class SyntaxTest {
    @Test
    public void testLR0DFA() {
        // example 4.45
        var S = Symbol.nonterminal("S");
        var L = Symbol.nonterminal("L");
        var R = Symbol.nonterminal("R");

        var ASSIGN = Symbol.terminal(TokenType.ASSIGN);
        var STAR = Symbol.terminal(TokenType.STAR);
        var ID = Symbol.terminal(TokenType.IDENTIFIER);


        Production[] productions = {
            new Production(S, L, ASSIGN, R),
            new Production(S, R),
            new Production(L, STAR, R),
            new Production(L, ID),
            new Production(R, L)
        };

        var list = ProductionList.fromUnaugmentedList(productions, S);

        var lr0dfa = LRDFA.constructLR0DFA(list);

        // manual check

    }

    @Test
    public void testCalculateFirst() {

        // S' -> A
        // A -> BA
        // A -> int

        // B -> assign B
        // B -> {nothing}

        var A = Symbol.nonterminal("A");
        var B = Symbol.nonterminal("B");

        var INT = Symbol.terminal(TokenType.INT);
        var ASSIGN = Symbol.terminal(TokenType.ASSIGN);

        Production[] productions = {
            new Production(A, B, A),
            new Production(A, INT),
            new Production(B, ASSIGN, B),
            new Production(B),
        };

        var list = ProductionList.fromUnaugmentedList(productions, A);


        var expectedFirstA = new HashSet<>(Arrays.asList(
            INT, ASSIGN
        ));

        var actual = list.first(A);

        assertEquals(expectedFirstA, actual);


    }

    @Test
    public void testClosureWithLookaheadSymbol() {
        // example 4.39
        // S' -> S
        // S -> C C
        // C -> if C
        // C -> =

        // expect: Closure{S' -> S, $R} = { (S' -> ·S, $R), (S -> ·CC, $R), (C -> ·if C, if | =), ( C -> ·=, if | =) }
        var S = Symbol.nonterminal("S");
        var C = Symbol.nonterminal("C");

        var IF = Symbol.terminal(TokenType.IF);
        var ASSIGN = Symbol.terminal(TokenType.ASSIGN);


        var startProduction = new Production(AUGMENTED_START_SYMBOL, S);

        Production[] productions = {
            startProduction,
            new Production(S, C, C),
            new Production(C, IF, C),
            new Production(C, ASSIGN),
        };

        var productionList = new ProductionList(Arrays.asList(productions), startProduction, AUGMENTED_START_SYMBOL);

        var kernelItem = new LRItem(startProduction, 0, DOLLAR_SYMBOL);

        var actual = LRDFA.closure(Collections.singletonList(kernelItem), productionList);


        var expected = Arrays.asList(
            new LRItem(new Production(AUGMENTED_START_SYMBOL, S), 0, DOLLAR_SYMBOL),
            new LRItem(new Production(S, C, C), 0, DOLLAR_SYMBOL),
            new LRItem(new Production(C, IF, C), 0, IF),
            new LRItem(new Production(C, IF, C), 0, ASSIGN),
            new LRItem(new Production(C, ASSIGN), 0, IF),
            new LRItem(new Production(C, ASSIGN), 0, ASSIGN)

        );

        assertEquals(expected, actual);

    }


    @Test
    public void testLALRConstruction() {

        var S = Symbol.nonterminal("S");
        var L = Symbol.nonterminal("L");
        var R = Symbol.nonterminal("R");

        var ASSIGN = Symbol.terminal(TokenType.ASSIGN);
        var STAR = Symbol.terminal(TokenType.STAR);
        var ID = Symbol.terminal(TokenType.IDENTIFIER);


        Production[] productions = {
            new Production(S, L, ASSIGN, R),
            new Production(S, R),
            new Production(L, STAR, R),
            new Production(L, ID),
            new Production(R, L)
        };

        var list = ProductionList.fromUnaugmentedList(productions, S);

        var lr0dfa = LRDFA.constructLR0DFA(list);

        // example 4.45

        LRDFA.addLookaheadSymbols(lr0dfa, list);


    }

    @Test
    public void syntaxAnalyzeFinalTest() {
        // example 4.31

        var E = Symbol.nonterminal("E");
        var T = Symbol.nonterminal("T");
        var F = Symbol.nonterminal("F");

        var plus = Symbol.terminal(TokenType.PLUS);
        var lparen = Symbol.terminal(TokenType.LEFT_PARENTHESIS);
        var rparen = Symbol.terminal(TokenType.RIGHT_PARENTHESIS);
        var star = Symbol.terminal(TokenType.STAR);
        var id = Symbol.terminal(TokenType.IDENTIFIER);

        Production[] productions = {
            new Production(E, E, plus, T),
            new Production(E, T),
            new Production(T, T, star, F),
            new Production(T, F),
            new Production(F, lparen, E, rparen),
            new Production(F, id)
        };

        var analyzer = SyntaxAnalyzer.construct(Arrays.asList(productions), E);

        var expected = Arrays.asList(
            productions[5],
            productions[3],
            productions[5],
            productions[2],
            productions[1],
            productions[5],
            productions[3],
            productions[0]
        );

        Stream<Symbol> tokens = Stream.of(
            id, star, id, plus, id, DOLLAR_SYMBOL
        ).map(x -> Symbol.terminal(x.getTokenType()));

        assertEquals(expected, analyzer.getProductionSequence(tokens.iterator()));

    }

    @Test
    public void testLian() {

        var S = Symbol.nonterminal("S");
        var b = Symbol.terminal(TokenType.STAR); // b用*代替
        var T = Symbol.nonterminal("T");
        var a = Symbol.terminal(TokenType.PLUS); // a用+代替
        var c = Symbol.terminal(TokenType.MINUS); // c用-代替

        Production[] productions = {
            new Production(S, T, b), // S -> Tb
            new Production(T, S, a), // T -> Sa
            new Production(T,c ) // T -> c
        };

        var productionList = ProductionList.fromUnaugmentedList(productions, S);

        var firstS = productionList.first(S);
        var firstT = productionList.first(T);

        System.out.println(firstS);
        System.out.println(firstT);
    }

}
