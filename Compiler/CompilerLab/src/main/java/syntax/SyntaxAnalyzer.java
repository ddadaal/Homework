package syntax;

import lombok.AllArgsConstructor;
import lombok.Getter;
import syntax.internal.Production;
import syntax.internal.Symbol;

import java.util.List;


@AllArgsConstructor
public class SyntaxAnalyzer {
    @Getter
    private List<Production> productions;

    @Getter
    private Symbol startSymbol;


    public void init() {
        // 1. add s' into the productions
        augmentProduction();

        // 2. build graph



    }


    public void analyze() {

    }

    public void augmentProduction() {

        Symbol symbol = new Symbol("S'");

        Production newProduction = new Production(symbol, startSymbol);

        productions.add(newProduction);
        startSymbol = symbol;

    }


}
