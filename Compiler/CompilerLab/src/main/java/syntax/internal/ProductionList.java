package syntax.internal;

import lombok.Getter;

import java.util.*;


/**
 * Production list
 */
public class ProductionList {

    @Getter private List<Production> productions;

    @Getter private Production startProduction;

    @Getter private Symbol startSymbol;


    @Getter private Map<Symbol, Set<Symbol>> followMap;


    /**
     * Augmented production list with only one start production
     * @param productions productions
     * @param startProduction start production
     * @param startSymbol start symbol
     */
    public ProductionList(List<Production> productions, Production startProduction, Symbol startSymbol) {
        this.productions = productions;
        this.startProduction = startProduction;
        this.startSymbol = startSymbol;

        this.followMap = new HashMap<>();
    }


    public Set<Symbol> follow(Symbol symbol) {

        if (followMap.containsKey(symbol)) {
            return followMap.get(symbol);
        }

        // calculate follow
        Set<Symbol> symbols = new HashSet<>();





        followMap.put(symbol, symbols);

        return symbols;

    }



}
