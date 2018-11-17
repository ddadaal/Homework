package syntax;

import lex.token.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;
import syntax.internal.*;
import util.Constants;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@AllArgsConstructor
public class SyntaxAnalyzer {
    @Getter
    private ProductionList productionList;
    @Getter
    private LRDFA lalrdfa;

    public static SyntaxAnalyzer construct(List<Production> productions, Symbol startSymbol) {

        var productionList = ProductionList.fromUnaugmentedList(productions, startSymbol);

        return construct(productionList);
    }

    public static SyntaxAnalyzer construct(ProductionList productionList) {
        var lalrdfa = LRDFA.constructLALRDFA(productionList);

        return new SyntaxAnalyzer(productionList, lalrdfa);
    }

    public List<Production> getProductionSequence(Iterator<Symbol> symbolIterator) {


        var productionSequence = new ArrayList<Production>();


        var stateStack = new Stack<LRDFANode>();

        var symbolStack = new Stack<Symbol>();

        // add initial state
        stateStack.push(lalrdfa.getStartState());

        var symbol = symbolIterator.next();

        while (true) {

            // find the state transition target
            var targetState = stateStack.peek().goTo(symbol);

            // find all reducibles
            List<LRItem> reducibles = new ArrayList<>();
            for (var item: stateStack.peek().getLrItems()) {
                if (item.isReducible() && item.getLookaheadSymbol().equals(symbol)) {
                    // find the reducible item
                    reducibles.add(item);
                }
            }

            // multiple applicable reducible items. R/R conflict
            if (reducibles.size() > 1) {

                throw new SyntaxParseException(
                    String.format(
                        "R/R conflict found.\nConflict production %s",
                        reducibles.stream()
                            .map(x -> x.getProduction().toString())
                            .collect(Collectors.joining("\n"))
                    )
                );
            }

            // there is a reducible production


            if (reducibles.size() == 1) {

                var reducibleItem = reducibles.get(0);
                // S and R both available. S/R conflict
                if (targetState != null) {
                    throw new SyntaxParseException(
                        String.format(
                            "S/R conflict found.\nShift Target: %s\nReduce Production: %s",
                            targetState,
                            reducibleItem
                        )
                    );
                } else {
                    // reduce

                    var production = reducibleItem.getProduction();

                    // if it's accepted item (left is S' and is reducible). break
                    if (production.getLeft().equals(productionList.getStartSymbol())) {
                        break;
                    }

                    productionSequence.add(production);

                    // pop the right symbols
                    for (int j=0;j<production.rightSize();j++) {
                        stateStack.pop();
                        symbolStack.pop();
                    }

                    // push left symbol into symbol
                    symbolStack.push(production.getLeft());

                    // push next state into symbol
                    stateStack.push(stateStack.peek().goTo(symbolStack.peek()));

                    continue;
                }
            }

            // there is no such an entry (state, symbol) in the table.
            // raise error and show expected tokens
            if (targetState == null) {
                var expectedTokens = stateStack.peek().getEdges().keySet().stream()
                    .filter(x -> !x.isNonTerminalSymbol())
                    .map(x -> x.getTokenType().toString())
                    .collect(Collectors.joining(", "));

                throw new SyntaxParseException(
                    String.format(
                        "Unexpected token %s. Expect: %s",
                        symbol.getTokenType().toString(),
                        expectedTokens
                    ));
            }


            // there is no reducible and 1 move

            // push in state and read next input

            stateStack.push(targetState);
            symbolStack.push(symbol);

            if (symbolIterator.hasNext()) {
                symbol = symbolIterator.next();

            } else {
                symbol = Constants.DOLLAR_SYMBOL;
            }
        }

        return productionSequence;
    }



}
