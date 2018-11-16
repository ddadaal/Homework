package lex.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TokenType {
    VOID("void"),
    INT("int"),
    RETURN("return"),
    WHILE("while"),
    IF("if"),
    ELSE("else"),

    ELLIPSIS("..."),
    EQUAL("=="),
    ASSIGN("="),
    SEMICOLON(";"),
    STAR("*"),
    OR_OR("||"),
    LEFT_BRACE("{"),
    RIGHT_BRACE("}"),
    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")"),
    COMMA(","),
    PLUS("+"),
    MINUS("-"),
    DIV("/"),
    INC("++"),
    LT("<"),
    LE("<="),

    IDENTIFIER("id"),
    FUNC_NAME("func_name"),
    INT_CONST("int_const"),
    STR_CONST("str_const"),

    IGNORED(""),
    UNKNOWN("#");

    @Getter private String str;

    @Override
    public String toString() {
        return str;
    }
}
