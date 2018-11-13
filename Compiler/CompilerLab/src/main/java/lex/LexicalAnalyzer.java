package lex;

import lex.token.Token;
import lex.token.TokenType;
import symboltable.SymbolTable;

import java.util.List;
import java.util.stream.Collectors;

public class LexicalAnalyzer {

    private String input;
    private int index;
    private SymbolTable symbolTable;
    private List<Rule> rules;

    public LexicalAnalyzer(String input, SymbolTable symbolTable, List<Rule> rules){
        this.input = input;
        this.index = 0;
        this.symbolTable = symbolTable;
        this.rules = rules;
    }

    public Token analyze() {
        StringBuilder buffer = new StringBuilder();
        List<Rule> candidates = null;
        while (index < input.length()) {
            buffer.append(input.charAt(index));
            index++;
            List<Rule> matched = rules.stream()
                .filter(x -> matchRegex(buffer.toString(), x))
                .collect(Collectors.toList());

            if (matched.size() == 0) {
                break;
            } else {
                candidates = matched;
            }
        }

        if (candidates == null) { // no match
            return null;
        }

        Rule result = candidates.get(0);

        TokenType resultType = result.getTokenType();

        // apply sym table
        if (result.getTokenType().equals(TokenType.IDENTIFIER)) {
            resultType = symbolTable.parse(buffer.toString());
        }

        return new Token(buffer.toString(), resultType);

    }

    public boolean matchRegex(String str, Rule rule) {

    }
}
