import lex.token.TokenType;
import lombok.var;
import org.junit.Test;
import syntax.internal.LRDFA;
import syntax.internal.Production;
import syntax.internal.ProductionList;
import syntax.internal.Symbol;

import java.util.Arrays;


public class SyntaxTest {

    @Test
    public void testLR0DFA() {

        var TS = new Symbol("S'");

        var S = new Symbol("S");
        var L = new Symbol("L");
        var R = new Symbol("R");

        var ASSIGN = new Symbol(TokenType.ASSIGN);
        var STAR = new Symbol(TokenType.STAR);
        var ID = new Symbol(TokenType.IDENTIFIER);

        var startProduction = new Production(TS, S);

        Production[] productions = {
            startProduction,
            new Production(S, L, ASSIGN, R),
            new Production(S, R),
            new Production(L, STAR, R),
            new Production(L, ID),
            new Production(R, L)
        };

        var list = new ProductionList(Arrays.asList(productions), startProduction, TS);

        var lr0dfa = LRDFA.constructLR0DFA(list);

        // manual check


    }
}
