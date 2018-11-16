import lex.token.TokenType;
import lombok.var;
import org.junit.Test;
import syntax.SyntaxAnalyzer;
import syntax.internal.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.Constants.*;


public class SyntaxTest {

    private LRDFA constructExample4dot45LR0DFA() {
        // example 4.45
        var S = new Symbol("S");
        var L = new Symbol("L");
        var R = new Symbol("R");

        var ASSIGN = new Symbol(TokenType.ASSIGN);
        var STAR = new Symbol(TokenType.STAR);
        var ID = new Symbol(TokenType.IDENTIFIER);


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

        return lr0dfa;
    }

    @Test
    public void testLR0DFA() {
        constructExample4dot45LR0DFA();

    }

    @Test
    public void testCalculateFirst() {

        // S' -> A
        // A -> BA
        // A -> int

        // B -> assign B
        // B -> {nothing}

        var A = new Symbol("A");
        var B = new Symbol("B");

        var INT = new Symbol(TokenType.INT);
        var ASSIGN = new Symbol(TokenType.ASSIGN);

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
        var S = new Symbol("S");
        var C = new Symbol("C");

        var IF = new Symbol(TokenType.IF);
        var ASSIGN = new Symbol(TokenType.ASSIGN);


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

        var S = new Symbol("S");
        var L = new Symbol("L");
        var R = new Symbol("R");

        var ASSIGN = new Symbol(TokenType.ASSIGN);
        var STAR = new Symbol(TokenType.STAR);
        var ID = new Symbol(TokenType.IDENTIFIER);


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

        var E = new Symbol("E");
        var T = new Symbol("T");
        var F = new Symbol("F");

        var plus = new Symbol(TokenType.PLUS);
        var lparen = new Symbol(TokenType.LEFT_PARENTHESIS);
        var rparen = new Symbol(TokenType.RIGHT_PARENTHESIS);
        var star = new Symbol(TokenType.STAR);
        var id = new Symbol(TokenType.IDENTIFIER);

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

        var tokens = Arrays.asList(
            id, star, id, plus, id, DOLLAR_SYMBOL
        );

        assertEquals(expected, analyzer.getProductionSequence(tokens.iterator()));

    }

}
