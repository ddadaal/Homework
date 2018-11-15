package syntax;

import lex.token.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import symboltable.SymbolTable;
import syntax.internal.Production;
import syntax.internal.ProductionSymbol;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class SyntaxAnalyzer {
    @Getter
    private List<Production> productions;

    @Getter
    private ProductionSymbol startSymbol;


    public void init() {
        // 1. add s' into the productions
        augmentProduction();

        // 2. build graph



    }


    public void analyze() {

    }

    public void augmentProduction() {

        ProductionSymbol symbol = new ProductionSymbol("S'");

        Production newProduction = new Production(symbol, startSymbol);

        productions.add(newProduction);
        startSymbol = symbol;

    }


}
