package syntax.internal;

import lombok.Getter;
import lombok.var;

import java.util.*;

import static util.Constants.AUGMENTED_START_SYMBOL;


public class ProductionList {

    @Getter private List<Production> productions;

    @Getter private Production startProduction;

    @Getter private Symbol startSymbol;


    @Getter private Map<Symbol, Set<Symbol>> firstMemo = new HashMap<>();

    private Map<Symbol, Boolean> canDeriveToEpsilonMemo = new HashMap<>();


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

    public static ProductionList fromUnaugmentedList(Production[] productions, Symbol startSymbol) {
        return fromUnaugmentedList(Arrays.asList(productions), startSymbol);
    }

    public Set<Symbol> first(Symbol symbol) {

        return firstMemo.computeIfAbsent(symbol, key -> {
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

            return symbols;
        });

    }

    public boolean canDeriveToEpsilon(Symbol symbol) {


        return canDeriveToEpsilonMemo.computeIfAbsent(symbol, key -> {

            if (!symbol.isNonTerminalSymbol()) {
                // if symbol is a terminal symbol,  it can't derive to epsilon
                return false;
            } else {
                for (var production: productions) {
                    if (!production.getLeft().equals(symbol)) {
                        continue;
                    }

                    // if there is no right symbol (meaning this production derives to epsilon),
                    // or all right symbol can derive epsilon,
                    // the result would be true
                    if (production.rightSize() == 0 || production.getRight().stream().allMatch(this::canDeriveToEpsilon)) {
                        return true;
                    }
                }
                return false;
            }

        });
    }



}
