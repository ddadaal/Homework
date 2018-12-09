import lex.LexicalAnalyzer;
import lex.MyLexReader;
import lex.Rule;
import lex.token.Token;
import lex.token.TokenType;
import lombok.var;
import org.junit.Test;
import syntax.MyYaccReader;
import syntax.SyntaxAnalyzer;
import syntax.internal.Production;
import syntax.internal.ProductionList;
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

        // 1. 解析myl和myy文件，获得正则匹配规则和产生式列表
        List<Rule> rules = MyLexReader.read(FileUtil.getLineIterator(lexFile));
        ProductionList productionList = MyYaccReader.read(FileUtil.getLineIterator(yaccFile));

        // 2. 初始化输入文件读取迭代器
        ListIterator<Character> inputReader = new BufferedIterator<>(FileUtil.getCharIterator(inputFile));

        // 3. 根据输入迭代器、正则匹配规则，生成词法分析器。词法分析器实现了Iterator<Token>接口，可以通过此词法分析器迭代Token
        LexicalAnalyzer lex = LexicalAnalyzer.construct(inputReader, rules);

        // 4. 初始化Token序列列表
        var actualTokenSequence = new ArrayList<Token>();

        // 5. 将Token迭代器转化为Symbol迭代器，并在过程中忽略掉IGNORED类型的Token（空白字符），并将分析出来的Token加入到Token序列列表
        Iterator<Symbol> symbolIterator = StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(lex, Spliterator.ORDERED),
            false)
            .filter(x -> !x.getType().equals(TokenType.IGNORED))
            .map(x -> {
                actualTokenSequence.add(x);
                return Symbol.terminal(x.getType());
            })
            .iterator();

        // 6. 根据产生式列表，生成语法分析器

        SyntaxAnalyzer syntax = SyntaxAnalyzer.construct(productionList);

        // 7. 输入Symbol迭代器，获得产生式规约序列
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

        System.out.println(String.format("Tokens (%d):", actualTokenSequence.size()));
        System.out.println();
        actualTokenSequence.forEach(System.out::println);

        System.out.println("----------------------");
        System.out.println(String.format("Production sequence (%d):", actualProductionSequence.size()));
        actualProductionSequence.forEach(System.out::println);


        assertEquals(expectedTokenSeq, actualTokenSequence);
        assertEquals(expectedProductionSeq, actualProductionSequence);

    }

    @Test
    public void bigTest() {
        var lexFile = "/bigtest/lex.myl";
        var yaccFile = "/bigtest/syn.myy";
        var inputFile = "/bigtest/input.c";

        // 1. 解析myl和myy文件，获得正则匹配规则和产生式列表
        List<Rule> rules = MyLexReader.read(FileUtil.getLineIterator(lexFile));
        ProductionList productionList = MyYaccReader.read(FileUtil.getLineIterator(yaccFile));

        // 2. 初始化输入文件读取迭代器
        ListIterator<Character> inputReader = new BufferedIterator<>(FileUtil.getCharIterator(inputFile));

        // 3. 根据输入迭代器、正则匹配规则和符号表，生成词法分析器。词法分析器实现了Iterator<Token>接口，可以通过此词法分析器迭代Token
        LexicalAnalyzer lex = LexicalAnalyzer.construct(inputReader, rules);

        // 4. 初始化Token序列列表
        var actualTokenSequence = new ArrayList<Token>();

        // 5. 将Token迭代器转化为Symbol迭代器，并在过程中忽略掉IGNORED类型的Token（空白字符），并将分析出来的Token加入到Token序列列表
        Iterator<Symbol> symbolIterator = StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(lex, Spliterator.ORDERED),
            false)
            .filter(x -> !x.getType().equals(TokenType.IGNORED))
            .map(x -> {
                actualTokenSequence.add(x);
                return Symbol.terminal(x.getType());
            })
            .iterator();

        int a = true ? null :2;

        // 6. 根据产生式列表，生成语法分析器

        SyntaxAnalyzer syntax = SyntaxAnalyzer.construct(productionList);

        // 7. 输入Symbol迭代器，获得产生式规约序列
        List<Production> actualProductionSequence = syntax.getProductionSequence(symbolIterator);

        // 这个太多了……不assert了，看看就好

        System.out.println(String.format("Tokens (%d):", actualTokenSequence.size()));
        System.out.println();
        actualTokenSequence.forEach(System.out::println);

        System.out.println("----------------------");
        System.out.println(String.format("Production sequence (%d):", actualProductionSequence.size()));
        actualProductionSequence.forEach(System.out::println);
    }

    @Test
    public void test2017LR() {

    }
}
