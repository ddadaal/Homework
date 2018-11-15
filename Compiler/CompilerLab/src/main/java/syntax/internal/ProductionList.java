package syntax.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;


public class ProductionList {

    @Getter private List<Production> productions;

    @Getter private Production startProduction;

    @Getter private ProductionSymbol startSymbol;


    @Getter private Map<ProductionSymbol, Set<ProductionSymbol>> followMap;


    public ProductionList(List<Production> productions, Production startProduction, ProductionSymbol startSymbol) {
        this.productions = productions;
        this.startProduction = startProduction;
        this.startSymbol = startSymbol;

        this.followMap = new HashMap<>();
    }

    public Set<ProductionSymbol> follow(ProductionSymbol symbol) {

        if (followMap.containsKey(symbol)) {
            return followMap.get(symbol);
        }

        // calculate follow
        Set<ProductionSymbol> symbols = new HashSet<>();





        followMap.put(symbol, symbols);

        return symbols;

    }



}
