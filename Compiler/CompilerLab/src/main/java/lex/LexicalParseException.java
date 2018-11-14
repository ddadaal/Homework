package lex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class LexicalParseException extends RuntimeException {

    @Getter private String error;

    public static void raise(String error) {
        throw new LexicalParseException(error);
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return error;
    }
}
