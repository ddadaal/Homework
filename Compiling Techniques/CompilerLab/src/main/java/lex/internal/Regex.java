package lex.internal;


import lex.LexicalParseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

import static lex.internal.RegexNode.RENodeType;

@AllArgsConstructor
public class Regex {

    private static Map<Character, Character> escapedChars = new HashMap<>();

    private static void addRawEscaped(char c) {
        escapedChars.put(c, c);

    }

    static {
        escapedChars.put('n', '\n');
        escapedChars.put('r', '\r');
        addRawEscaped('"');
        addRawEscaped('.');
        addRawEscaped('=');
        addRawEscaped('<');
        addRawEscaped('(');
        addRawEscaped(')');
        addRawEscaped('{');
        addRawEscaped('}');
        addRawEscaped('-');
        addRawEscaped(';');
        addRawEscaped('[');
        addRawEscaped(']');
        addRawEscaped('|');
        addRawEscaped(' ');
        addRawEscaped('*');
        addRawEscaped('+');
        addRawEscaped('/');
        addRawEscaped('\\');

    }

    @Getter
    private List<RegexNode> nodes;

    @Getter
    private String regex;

    public static Regex constructRegex(String regex) {
        List<RegexNode> preprocessed = preprocess(regex);

        preprocessed = addConcatenations(preprocessed);

        return new Regex(toPostfix(preprocessed), regex);
    }


    public String getPostfix() {
        return showRENodes(nodes);
    }

    public static String showRENodes(List<RegexNode> nodes) {
        StringBuilder r2str = new StringBuilder();
        for (RegexNode r : nodes) {
            r2str.append(r.getLexeme());
        }

        return r2str.toString();
    }




    private static List<RegexNode> toPostfix(List<RegexNode> nodes) {
        List<RegexNode> result = new ArrayList<>();
        Stack<RegexNode> stack = new Stack<>();

        for (RegexNode node : nodes) {
            RENodeType type = node.getType();

            switch (type) {
                case STAR:
                case CHAR:
                    result.add(node);
                    break;
                case LEFT_PARENTHESIS:
                    stack.push(node);
                    break;
                case RIGHT_PARENTHESIS:
                    RegexNode r;
                    while (!(r=stack.pop()).getType().equals(RENodeType.LEFT_PARENTHESIS)) {
                        result.add(r);
                    }
                    break;
                case OR:
                case CONCATENATION:
                    while (!stack.empty() && type.getPriority() <= stack.peek().getType().getPriority()) {
                        result.add(stack.pop());
                    }
                    stack.push(node);
                    break;
            }
        }

        while(!stack.isEmpty()){
            result.add(stack.pop());
        }

        return result;

    }

    private static List<RegexNode> addConcatenations(List<RegexNode> nodes) {
        List<RegexNode> result = new ArrayList<>();

        for (int i =0;i<nodes.size()-1;i++) {
            RegexNode left = nodes.get(i);
            RegexNode right = nodes.get(i+1);

            result.add(left);
            if (shouldAddConcatenation(left, right)) {
                result.add(new RegexNode(RENodeType.CONCATENATION));
            }
        }

        result.add(nodes.get(nodes.size()-1));

        return result;
    }

    private static boolean shouldAddConcatenation(RegexNode left, RegexNode right) {
        boolean leftIsTheEndOfAnExpression =
            left.getType().equals(RENodeType.RIGHT_PARENTHESIS)
            || left.getType().equals(RENodeType.STAR)
            || left.getType().equals(RENodeType.CHAR)
            ;

        boolean rightIsTheStartOfAnExpression =
            right.getType().equals(RENodeType.LEFT_PARENTHESIS)
            || right.getType().equals(RENodeType.CHAR)
            ;

        return leftIsTheEndOfAnExpression && rightIsTheStartOfAnExpression;
    }

    public static List<RegexNode> preprocess(String regex) {
        ArrayList<RegexNode> result = new ArrayList<>();

        int bracketNestCount = 0;

        for (int i =0;i<regex.length();i++) {
            char c = regex.charAt(i);

            // handle escaped character
            if (c == '\\') {
                // escaped

                if (i == regex.length()-1) {
                    throw new LexicalParseException("Bad use of \\: expect a escaped char.");
                }

                i++;
                char nextChar = regex.charAt(i);

                Character r = escapedChars.get(nextChar);
                if (r == null) {
                    throw new LexicalParseException("Unexpected escaped char: " + nextChar);
                }
                result.add(new RegexNode(r));
            }

            // handle bracket

            else if (c == '[') {
                bracketNestCount++;
                result.add(new RegexNode(RENodeType.LEFT_PARENTHESIS));
                if (regex.charAt(i+1) == '^') {
                    i++;
                }
                continue;
            }

            else if (c == ']') {
                if (result.get(result.size()-1).getType().equals(RENodeType.OR)) {
                    result.remove(result.size()-1);
                }
                bracketNestCount--;
                result.add(new RegexNode(RENodeType.RIGHT_PARENTHESIS));
                continue;
            }

            // handle dash expression
            else if (bracketNestCount > 0 && c == '-') {

                // remove the previous |
                result.remove(result.size()-1);

                char startChar = result.get(result.size()-1).getLexeme();

                // remove the start char
                result.remove(result.size()-1);

                if (i == regex.length()-1) {
                    throw new LexicalParseException("Bad use of /: expect a end char after.");
                }

                i++;
                char endChar = regex.charAt(i);

                result.add(new RegexNode(RENodeType.LEFT_PARENTHESIS));

                for (char c1=startChar;c1<=endChar;c1++) {
                    result.add(new RegexNode(c1));
                    if (c1 != endChar) {
                        result.add(new RegexNode(RENodeType.OR));
                    }
                }

                result.add(new RegexNode(RENodeType.RIGHT_PARENTHESIS));

            }

            // handle . (anything except \n)
            else if (c == '.') {
                result.add(new RegexNode(RENodeType.LEFT_PARENTHESIS));
                for (char c1 = 32;c1<=126;c1++) {
                    if (c1 == '\n') {
                        continue;
                    }
                    result.add(new RegexNode(c1));

                    if (c1 != 126) {
                        result.add(new RegexNode(RENodeType.OR));
                    }
                }
                result.add(new RegexNode(RENodeType.RIGHT_PARENTHESIS));
            }

            // handle parentheses
            else if (c == '(') {
                result.add(new RegexNode(RENodeType.LEFT_PARENTHESIS));
            }

            else if (c == ')') {
                result.add(new RegexNode(RENodeType.RIGHT_PARENTHESIS));
            }

            // handle or and star

            else if (c == '*') {
                result.add(new RegexNode(RENodeType.STAR));
            }

            else if (c == '|') {
                result.add(new RegexNode(RENodeType.OR));
            }
            else {
                // normal char
                result.add(new RegexNode(c));
            }


            if (bracketNestCount > 0) {
                result.add(new RegexNode(RENodeType.OR));
            }



        }

        if (bracketNestCount > 0) {
            throw new LexicalParseException("Expect ending ]");
        }

        return result;
    }


}
