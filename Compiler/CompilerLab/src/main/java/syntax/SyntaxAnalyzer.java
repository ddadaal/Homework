package syntax;

import lex.LexicalAnalyzer;
import lombok.Getter;
import symboltable.SymbolTable;


public class SyntaxAnalyzer {

    private int index = 0;

    @Getter
    private SymbolTable symbolTable;


    public SyntaxAnalyzer(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }



}
