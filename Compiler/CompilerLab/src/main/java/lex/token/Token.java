package lex.token;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@EqualsAndHashCode
public class Token {
    @Getter private String lexeme;
    @Getter private TokenType type;

    @Override
    public String toString() {
        return String.format("<%s, %s>", type.name(), lexeme);
    }
}
