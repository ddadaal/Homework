package lex;

import lex.token.TokenType;

public class Rule {
    private String regex;
    private TokenType tokenType;

    public Rule(String regex, TokenType tokenType) {
        this.regex = regex;
        this.tokenType = tokenType;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}
