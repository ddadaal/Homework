import lex.LexicalAnalyzer;
import lex.MyLexReader;
import lex.token.Token;
import lex.token.TokenType;
import lombok.var;
import org.junit.Test;
import symboltable.SymbolTable;
import syntax.MyYaccReader;
import syntax.SyntaxAnalyzer;
import syntax.internal.Production;
import syntax.internal.Symbol;
import util.BufferedIterator;
import util.FileUtil;

import java.util.*;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;


public class IntegrationTest {

    @Test
    public void testExample431() {
        var lexFile = "/example4.31/lex.myl";
        var yaccFile = "/example4.31/syn.myy";
        var inputFile = "/example4.31/input.c";

        Iterator<String> lexReader = FileUtil.getLineIterator(lexFile);
        Iterator<String> yaccReader = FileUtil.getLineIterator(yaccFile);

        ListIterator<Character> inputReader = new BufferedIterator<>(FileUtil.getCharIterator(inputFile));

        var symbolTable = new SymbolTable();

        LexicalAnalyzer lex = LexicalAnalyzer.construct(inputReader, symbolTable, MyLexReader.read(lexReader));

        var actualTokenSequence = new ArrayList<Token>();

        // Token iterator to Symbol Iterator
        Iterator<Symbol> symbolIterator = StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(lex, Spliterator.ORDERED),
            false)
            .filter(x -> !x.getType().equals(TokenType.IGNORED))
            .map(x -> {
                actualTokenSequence.add(x);
                return Symbol.terminal(x.getType());
            })
            .iterator();


        SyntaxAnalyzer syntax = SyntaxAnalyzer.construct(MyYaccReader.read(yaccReader));

        List<Production> actualProductionSequence = syntax.getProductionSequence(symbolIterator);

        var E = Symbol.nonterminal("E");
        var T = Symbol.nonterminal("T");
        var F = Symbol.nonterminal("F");

        var plus = Symbol.terminal(TokenType.PLUS);
        var lparen = Symbol.terminal(TokenType.LEFT_PARENTHESIS);
        var rparen = Symbol.terminal(TokenType.RIGHT_PARENTHESIS);
        var star = Symbol.terminal(TokenType.STAR);
        var id = Symbol.terminal(TokenType.IDENTIFIER);

        Production[] productions = {
            new Production(E, E, plus, T),
            new Production(E, T),
            new Production(T, T, star, F),
            new Production(T, F),
            new Production(F, lparen, E, rparen),
            new Production(F, id)
        };

        var expectedProductionSeq = Arrays.asList(
            productions[5],
            productions[3],
            productions[5],
            productions[2],
            productions[1],
            productions[5],
            productions[3],
            productions[0]
        );

        var expectedTokenSeq = Arrays.asList(
            new Token("abc", TokenType.IDENTIFIER),
            new Token("*", TokenType.STAR),
            new Token("ab123", TokenType.IDENTIFIER),
            new Token("+", TokenType.PLUS),
            new Token("s_25", TokenType.IDENTIFIER)
        );

        assertEquals(expectedTokenSeq, actualTokenSequence);
        assertEquals(expectedProductionSeq, actualProductionSequence);

    }

}
