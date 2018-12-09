package lex.internal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import util.Constants;

@EqualsAndHashCode
@ToString
public class RegexNode {


    public enum RENodeType {
        CHAR('?', -1),
        STAR('*', -1),
        OR('|', 1),
        LEFT_PARENTHESIS('(', -1),
        RIGHT_PARENTHESIS(')', -1),
        CONCATENATION(Constants.DOT, 2);

        private char c;
        @Getter private int priority;
        RENodeType(char c, int priority) {
            this.c = c;
            this.priority = priority;
        }
    }

    @Getter private char lexeme;
    @Getter private RENodeType type;

    public RegexNode(char lexeme) {
        this.lexeme = lexeme;
        this.type =RENodeType.CHAR;
    }

    public RegexNode(RENodeType type) {
        this.type = type;
        this.lexeme = type.c;

    }


}
