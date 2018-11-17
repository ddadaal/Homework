package syntax.internal;

import lex.token.Token;
import lex.token.TokenType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Symbol of a production
 * can be either a non-terminal or terminal by calling different constructor
 */
@EqualsAndHashCode
public class Symbol {
    @Getter private String ntName;
    @Getter private TokenType tokenType;



    public static Symbol terminal(TokenType tokenType) {
        return new Symbol(tokenType);
    }

    public static Symbol nonterminal(String name) {
        return new Symbol(name);
    }


    private Symbol(String ntName) {
        this.ntName = ntName;
    }

    private Symbol(TokenType tokenType) {
        this.tokenType = tokenType;
    }


    public boolean isNonTerminalSymbol() {
        return ntName != null;
    }

    @Override
    public String toString() {
        if (isNonTerminalSymbol()) {
            return ntName;
        } else {
            return tokenType.name();
        }
    }
}
