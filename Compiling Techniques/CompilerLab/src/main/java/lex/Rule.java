package lex;

import lex.token.TokenType;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
public class Rule {

    @Getter
    private String regex;

    @Getter
    private TokenType tokenType;

    @Override
    public String toString() {
        return regex + " " + tokenType.name();
    }
}
