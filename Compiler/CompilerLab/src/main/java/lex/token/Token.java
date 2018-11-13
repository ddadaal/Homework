package lex.token;

import java.util.Objects;

public class Token {
    private String lexeme;
    private TokenType type;


    public Token(String lexeme, TokenType type) {
        this.lexeme = lexeme;
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("<%s, %s>", type.toString(), lexeme);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token = (Token) o;
        return Objects.equals(getLexeme(), token.getLexeme()) &&
            getType() == token.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLexeme(), getType());
    }
}
