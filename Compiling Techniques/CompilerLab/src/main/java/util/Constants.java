package util;

import lex.token.TokenType;
import syntax.internal.Symbol;

public class Constants {
    public static char EPSILON = 'ε';
    public static char DOT = '·';

    public static Symbol DOLLAR_SYMBOL = Symbol.terminal(TokenType.DOLLAR_R);


    public static Symbol AUGMENTED_START_SYMBOL = Symbol.nonterminal("S'");
    public static Symbol EXTERNAL_SYMBOL = Symbol.terminal(TokenType.UNKNOWN);
}

