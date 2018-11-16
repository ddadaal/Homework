import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import lex.LexicalAnalyzer;
import lex.Rule;
import lex.token.Token;
import lex.token.TokenType;
import symboltable.SymbolTable;
import util.IteratorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Rule rule1 = new Rule("b|ba*", TokenType.COMMA);

        Rule rule2 = new Rule("[1-9]*b", TokenType.ASSIGN);

        List<Rule> rules = Arrays.asList(rule1, rule2);
        SymbolTable symbolTable = new SymbolTable();
        LexicalAnalyzer analyzer = LexicalAnalyzer.construct(
            IteratorUtil.strToIterator("1234bbaaaaa1b"),
            symbolTable,
            rules
        );



    }
}
