package syntax;

import lex.token.TokenType;
import lombok.var;
import syntax.internal.Production;
import syntax.internal.ProductionList;
import syntax.internal.Symbol;

import java.util.*;

public class MyYaccReader {
    public static ProductionList read(Iterator<String> lineIterator) {

        var productions = new ArrayList<Production>();

        var nonterminalMap = new HashMap<String, Symbol>();

        var startSymbol = nonterminalMap.computeIfAbsent(lineIterator.next(), Symbol::nonterminal);

        int lineIndex = 1;


        String s;
        while (lineIterator.hasNext()) {
            // skip empty lines
            while ((s=lineIterator.next()).length() == 0) {
                lineIndex++;
            }

            // read the left non-terminal symbol and put into map
            var left = nonterminalMap.computeIfAbsent(s, Symbol::nonterminal);

            // continuously read right productions until a line "%" is read
            while (lineIterator.hasNext() && !(s = lineIterator.next()).trim().equals("%")) {
                lineIndex++;
                var rightList= new ArrayList<Symbol>();
                for (var right: s.trim().split("\\s+")) {
                    if (right.equals("")) {
                        continue;
                    }
                    try {
                        var tokenType = TokenType.valueOf(right);
                        rightList.add(Symbol.terminal(tokenType));
                    } catch (IllegalArgumentException e) {
                        rightList.add(nonterminalMap.computeIfAbsent(right, Symbol::nonterminal));
                    }
                }

                productions.add(new Production(left, rightList));
            }

        }

        return ProductionList.fromUnaugmentedList(productions, startSymbol);
    }


}
