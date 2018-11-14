package syntax;

import lex.LexicalAnalyzer;
import lex.token.Token;
import symboltable.SymbolTable;

public class PrintSyntaxAnalyzer implements SyntaxAnalyzer {

    @Override
    public void analyze(LexicalAnalyzer lex, SymbolTable symbolTable) {
        Token token;
        while ((token = lex.analyze()) != null) {
            System.out.println(token.toString());
        }
    }
}
