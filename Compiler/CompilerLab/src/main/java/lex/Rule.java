package lex;

import lex.token.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
public class Rule {

    @Getter
    private String regex;

    @Getter
    private TokenType tokenType;

}
