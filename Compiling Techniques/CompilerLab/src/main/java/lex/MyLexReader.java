package lex;

import lex.internal.Regex;
import lex.token.TokenType;
import lombok.var;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyLexReader {

    public static List<Rule> read(Iterator<String> lineIterator) {
        var result = new ArrayList<Rule>();

        var lineIndex = 0;

        String s ;

        while (lineIterator.hasNext()) {

            // skip empty lines
            while ((s=lineIterator.next()).length() == 0) {
                lineIndex++;
            }

            var regex = s;
            lineIndex++;
            if (lineIterator.hasNext()) {
                var tokenInput = lineIterator.next();
                lineIndex++;
                try {
                    var tokenType = TokenType.valueOf(tokenInput);
                    result.add(new Rule(regex, tokenType));
                } catch (IllegalArgumentException e) {
                    throw new LexicalParseException(
                        String.format(
                            "Invalid input of TokenType %s in line %d",
                            tokenInput,
                            lineIndex
                            ));
                }
            }
        }

        return result;

    }
}
