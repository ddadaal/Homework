import lex.LexicalAnalyzer;
import lex.MyLexReader;
import lex.Rule;
import lex.internal.RegexNode;
import lex.internal.Regex;
import lex.token.Token;
import lex.token.TokenType;
import lombok.var;
import org.junit.Test;
import util.FileUtil;
import util.IteratorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LexTest {


    @Test
    public void testPreprocess() {

//         easy
        String regex1 = "a*|b";

        List<RegexNode> expected1 = new ArrayList<>();
        expected1.add(new RegexNode('a'));
        expected1.add(new RegexNode(RegexNode.RENodeType.STAR));
        expected1.add(new RegexNode(RegexNode.RENodeType.OR));
        expected1.add(new RegexNode('b'));

        List<RegexNode> actual1 = Regex.preprocess(regex1);

        assertEquals(expected1, actual1);


        // with [ preprocess
        String regex2 = "0|[1-9][0-9_]*";

        String expected2 = "0|((1|2|3|4|5|6|7|8|9))((0|1|2|3|4|5|6|7|8|9)|_)*";


        List<RegexNode> result2 = Regex.preprocess(regex2);

        assertEquals(expected2, Regex.showRENodes(result2));

    }

    @Test
    public void testPostfix() {
        // easy
        String regex1 = "a*|bcd";

        Regex r1 = Regex.constructRegex(regex1);

        String expected1 = "a*bc·d·|";

        assertEquals(expected1, r1.getPostfix());

        // hard

        // ((a|b|c))*·b·c|d·(a·p)*·((e|f))
        Regex r2 = Regex.constructRegex("[a-c]*bc|d(ap)*[e-f]");

        String expected2 = "ab|c|*b·c·dap·*·ef|·|";

        assertEquals(expected2, r2.getPostfix());


    }

    @Test
    public void testConstructNFA() {
        String regex = "a*";

        LexicalAnalyzer lex = new LexicalAnalyzer(null, null);

    }



    @Test
    public void testSimpleRulesAndInput() {
        Rule rule1 = new Rule("b|ba*", TokenType.COMMA);

        Rule rule2 = new Rule("[1-9]*b", TokenType.ASSIGN);

        List<Rule> rules = Arrays.asList(rule1, rule2);
        LexicalAnalyzer analyzer = LexicalAnalyzer.construct(
            IteratorUtil.strToIterator("1234bbaaaaa1b"),
            rules
        );


        List<Token> actuals = new ArrayList<>();
        analyzer.forEachRemaining(actuals::add);


        List<Token> expected = new ArrayList<>();
        expected.add(new Token("1234b", TokenType.ASSIGN));
        expected.add(new Token("baaaaa", TokenType.COMMA));
        expected.add(new Token("1b", TokenType.ASSIGN));

        assertEquals(expected, actuals);
    }

    private List<Token> parse(String str, Rule... rules) {

        LexicalAnalyzer analyzer = LexicalAnalyzer.construct(
            IteratorUtil.strToIterator(str),
            Arrays.asList(rules)
        );

        List<Token> actuals = IteratorUtil.iterateAll(analyzer);

        return actuals;
    }

    private void expectString(String str) {
        List<Token> actuals = parse(str,
            new Rule("\\\"(.)*\\\"", TokenType.STR_CONST)
        );

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(str, TokenType.STR_CONST));

        assertEquals(expected, actuals);
    }

    @Test
    public void testDotRegex() {

        List<Token> actual = parse("\"i is negative\\n\"", new Rule("\\\"(.|\\\\\\n)*\\\"", TokenType.STR_CONST));

        Token expected = new Token("\"i is negative\\n\"", TokenType.STR_CONST);

        assertEquals(expected, actual.get(0));
    }

    @Test
    public void lexFinalTest() {
        List<Rule> rules = new ArrayList<>(Arrays.asList(
            new Rule("void", TokenType.VOID),
            new Rule("int", TokenType.INT),
            new Rule("return", TokenType.RETURN),
            new Rule("while", TokenType.WHILE),
            new Rule("if", TokenType.IF),
            new Rule("else", TokenType.ELSE),

            new Rule("\\.\\.\\.", TokenType.ELLIPSIS),
            new Rule("==", TokenType.EQUAL),
            new Rule("=", TokenType.ASSIGN),
            new Rule("\\;", TokenType.SEMICOLON),
            new Rule("\\*", TokenType.STAR),
            new Rule("\\|\\|", TokenType.OR_OR),
            new Rule("\\{", TokenType.LEFT_BRACE),
            new Rule("\\}", TokenType.RIGHT_BRACE),
            new Rule("\\(", TokenType.LEFT_PARENTHESIS),
            new Rule("\\)", TokenType.RIGHT_PARENTHESIS),
            new Rule(",", TokenType.COMMA),
            new Rule("\\+", TokenType.PLUS),
            new Rule("\\-", TokenType.MINUS),
            new Rule("\\/", TokenType.DIV),
            new Rule("\\+\\+", TokenType.INC),
            new Rule("\\<", TokenType.LT),
            new Rule("\\<=", TokenType.LE),

            new Rule("[a-zA-Z]([0-9a-zA-Z_])*", TokenType.IDENTIFIER),
            new Rule("0|[1-9][0-9]*", TokenType.INT_CONST),
            // bypass " according to acsii code table
            new Rule("\\\"([\\ -!#-~]|\\\\\\n)*\\\"", TokenType.STR_CONST),

            new Rule("[\\ \\n]", TokenType.IGNORED)
        ));


        LexicalAnalyzer analyzer = LexicalAnalyzer.construct(
            IteratorUtil.strToIterator(FileUtil.getContentsOfResource("/input.c")),
            rules
        );

        IteratorUtil.iterateAll(analyzer).stream()
            .filter(x -> !x.getType().equals(TokenType.IGNORED))
            .forEach(System.out::println);



    }

    @Test
    public void testReader() {
        // test MyLexReader to read in rules
        List<Rule> rules = new ArrayList<>(Arrays.asList(
            new Rule("void", TokenType.VOID),
            new Rule("int", TokenType.INT),
            new Rule("return", TokenType.RETURN),
            new Rule("while", TokenType.WHILE),
            new Rule("if", TokenType.IF),
            new Rule("else", TokenType.ELSE),

            new Rule("\\.\\.\\.", TokenType.ELLIPSIS),
            new Rule("==", TokenType.EQUAL),
            new Rule("=", TokenType.ASSIGN),
            new Rule("\\;", TokenType.SEMICOLON),
            new Rule("\\*", TokenType.STAR),
            new Rule("\\|\\|", TokenType.OR_OR),
            new Rule("\\{", TokenType.LEFT_BRACE),
            new Rule("\\}", TokenType.RIGHT_BRACE),
            new Rule("\\(", TokenType.LEFT_PARENTHESIS),
            new Rule("\\)", TokenType.RIGHT_PARENTHESIS),
            new Rule(",", TokenType.COMMA),
            new Rule("\\+", TokenType.PLUS),
            new Rule("\\-", TokenType.MINUS),
            new Rule("\\/", TokenType.DIV),
            new Rule("\\+\\+", TokenType.INC),
            new Rule("\\<", TokenType.LT),
            new Rule("\\<=", TokenType.LE),

            new Rule("[a-zA-Z]([0-9a-zA-Z_])*", TokenType.IDENTIFIER),
            new Rule("0|[1-9][0-9]*", TokenType.INT_CONST),
            // bypass " according to ascii code table
            new Rule("\\\"([\\ -!#-~]|\\\\\\n)*\\\"", TokenType.STR_CONST),

            new Rule("[\\ \\n]", TokenType.IGNORED),
            new Rule(".", TokenType.UNKNOWN)
        ));

        var actual = MyLexReader.read(FileUtil.getLineIterator("/lex.myl"));

        assertEquals(rules, actual);

    }
}
