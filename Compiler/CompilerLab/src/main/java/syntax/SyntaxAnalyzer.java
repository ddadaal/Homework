package syntax;

import lex.LexicalAnalyzer;
import lex.token.Token;
import symboltable.SymbolTable;

import java.util.List;

public interface SyntaxAnalyzer {
    void analyze(LexicalAnalyzer lex, SymbolTable symbolTable);
}
