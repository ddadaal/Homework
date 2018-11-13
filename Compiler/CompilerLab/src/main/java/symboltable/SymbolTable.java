package symboltable;

import lex.token.Token;
import lex.token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    private List<String> functions;

    public SymbolTable() {
        functions = new ArrayList<>();
    }

    public TokenType parse(String s) {
        if (functions.contains(s)) {
            return TokenType.FUNC_NAME;
        }
        return TokenType.IDENTIFIER;
    }

    public void addFunction(String funcName) {
        functions.add(funcName);
    }

}
