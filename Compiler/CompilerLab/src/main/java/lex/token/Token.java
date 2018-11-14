package lex.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
public class Token {
    @Getter private String lexeme;
    @Getter private TokenType type;

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
