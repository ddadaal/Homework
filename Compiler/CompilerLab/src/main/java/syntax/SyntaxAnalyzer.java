package syntax;

import lex.token.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;
import syntax.internal.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


@AllArgsConstructor
public class SyntaxAnalyzer {
    @Getter
    private ProductionList productionList;
    @Getter
    private LRDFA lalrdfa;

    public static SyntaxAnalyzer construct(List<Production> productions, Symbol startSymbol) {
        var productionList = ProductionList.fromUnaugmentedList(productions, startSymbol);

        var lalrdfa = LRDFA.constructDFA(productionList);

        return new SyntaxAnalyzer(productionList, lalrdfa);
    }

    public List<Production> getProductionSequence(List<Token> tokens) {

        var productionList = new ArrayList<Production>();

        LRDFANode state = lalrdfa.getStartState();

        var stateStack = new Stack<LRDFANode>();

        var symbolStack = new Stack<Symbol>();

        for (int i =0;i<tokens.size();i++) {
            
        }
    }

}
