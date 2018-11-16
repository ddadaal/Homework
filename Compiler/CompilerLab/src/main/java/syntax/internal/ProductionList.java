package syntax.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;
import syntax.SyntaxParseException;

import java.util.*;

import static util.Constants.AUGMENTED_START_SYMBOL;
import static util.Constants.EPSILON;
import static util.Constants.EPSILON_SYMBOL;


/**
 * Production list
 */
public class ProductionList {

    @Getter private List<Production> productions;

    @Getter private Production startProduction;

    @Getter private Symbol startSymbol;


    @Getter private Map<Symbol, Set<Symbol>> firstMap = new HashMap<>();

    private Map<Symbol, Boolean> canDeriveToEpsilonMap = new HashMap<>();


    public ProductionList(List<Production> productions, Production startProduction, Symbol startSymbol) {
        this.productions = productions;
        this.startProduction = startProduction;
        this.startSymbol = startSymbol;

    }


    public static ProductionList fromUnaugmentedList(List<Production> productions, Symbol startSymbol) {
        var startProduction = new Production(AUGMENTED_START_SYMBOL, startSymbol);

        List<Production> augmentedList = new ArrayList<>(productions);
        augmentedList.add(startProduction);

        return new ProductionList(augmentedList, startProduction, AUGMENTED_START_SYMBOL);
    }

    /**
     * Augmented production list with only one start production
     * The production must be augmented with
     * @param productions productions
     */
    public ProductionList(List<Production> productions, Symbol startSymbol) {
        this.productions = productions;
        this.startSymbol = startSymbol;

        var startProduction = productions.stream().filter(x -> x.getLeft().equals(startSymbol)).findFirst();
        if (startProduction.isPresent()) {
            this.startProduction = startProduction.get();
        } else {
            throw new SyntaxParseException("Expect a S' -> S production");
        }

    }




    public Set<Symbol> first(Symbol symbol) {

        if (firstMap.containsKey(symbol)) {
            return firstMap.get(symbol);
        }

        // calculate first
        Set<Symbol> symbols = new HashSet<>();

        if (!symbol.isNonTerminalSymbol()) {
            // if symbol is a terminal symbol, return itself
            symbols.add(symbol);
        } else {
            for (var production: productions) {

                if (!production.getLeft().equals(symbol)) {
                    continue;
                }

                for (var rightSymbol: production.getRight()) {

                    // ignore itself to avoid endless loop
                    if (rightSymbol.equals(symbol)) {
                        continue;
                    }

                    var set = first(rightSymbol);
                    symbols.addAll(set);

                    if (!canDeriveToEpsilon(rightSymbol)) {
                        break;
                    }
                }
            }
        }

        // remove epsilon
        symbols.remove(EPSILON_SYMBOL);


        firstMap.put(symbol, symbols);

        return symbols;

    }

    public boolean canDeriveToEpsilon(Symbol symbol) {
        if (canDeriveToEpsilonMap.containsKey(symbol)) {
            return canDeriveToEpsilonMap.get(symbol);
        }

        boolean result = false;

        if (!symbol.isNonTerminalSymbol()) {
            // if symbol is a terminal symbol, unless it's a epsilon, it can't derive to epsilon
            result = symbol.equals(EPSILON_SYMBOL);
        } else {
            for (var production: productions) {
                if (!production.getLeft().equals(symbol)) {
                    continue;
                }

                // if all right symbol can derive epsilon, the result would be true
                if (production.getRight().stream().allMatch(this::canDeriveToEpsilon)) {
                    result= true;
                    break;
                }
            }
        }

        canDeriveToEpsilonMap.put(symbol, result);
        return result;
    }



}
