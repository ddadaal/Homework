package lex;

import lex.internal.DFANode;
import lex.token.Token;
import symboltable.SymbolTable;

import java.util.List;

public class LexicalAnalyzer {

    private String input;
    private int index;
    private SymbolTable symbolTable;
    private DFANode dfa;

    public LexicalAnalyzer(String input, SymbolTable symbolTable){
        this.input = input;
        this.index = 0;
        this.symbolTable = symbolTable;
    }

    public Token analyze() {

    }
}
