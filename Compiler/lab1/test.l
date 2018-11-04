%option yylineno

%{
    #include <stdio.h>
    
    void onmatch(char * type);
    void handleComment();
    int handleError();

    // comment this to make the program NOT crash on unknown token
    // #define CRASH_ON_UNKNOWN
    
    #define COMMENT "Comment"
    #define KEYWORD "Keyword"
    #define IDENTIFIER "Identifier"
    #define INT_CONST "Integer Constant"
    #define CHAR_CONST "Character Constant"
    #define REAL_CONST "Real Number Constant"
    #define STR_CONST "String Constant"
    #define ASSIGN_OP "Assignment Operator"
    #define INCRE_DECRE_OP "Increment or Decrement"
    #define ARITHMETIC_OP "Arithmetic Operator"
    #define COMPARISON_OP "Comparison Operator"
    #define LOGICAL_OP "Logical Operator"
    #define BIT_OP "Bit Operator"
    #define POINTER_OP "Pointer Operator"
    #define SIZEOF_OP "sizeof Operator"
    #define QUESTION_MARK "Question mark (?)"
    #define UNKNOWN "Unknown"
    #define LEFT_PARENTHESIS "Left Parenthesis"
    #define RIGHT_PARENTHESIS "Right Parenthesis"
    #define LEFT_BRACKET "Left Bracket"
    #define RIGHT_BRACKET "Right Bracket"
    #define LEFT_BRACE "Left Brace"
    #define RIGHT_BRACE "Right Brace"
    #define SEMICOLON "Semicolon"
    #define COMMA "Comma"
    #define DOT "Dot"
    #define COLON "Colon"
    #define ELLIPSIS "Ellipsis"

    #define STAR "STAR"
    #define AND "AND"
    #define PLUS "PLUS"
    #define MINUS "MINUS"
%}

letter [a-zA-Z]
digit [0-9]
WS [ \n\t\f]
octa_num [0-7]+
octa 0{octa_num}
hex_num ({digit}|[a-fA-F])+
hex 0(x|X){hex_num}
int_type (l|L)|((u|U)(l|L)?(l|L)?)
escaped_char \\((\\)|(\?)|(\')|(\")|[abfnrtv]|([0-7]{1,3})|x({hex_num}))
float_no_e {digit}+\.{digit}*|{digit}*\.{digit}+
float_type (F|f|L|l)

%%

\/\/.* {onmatch(COMMENT);} 
\/\* { handleComment(); }


"auto" { onmatch(KEYWORD); }
"break" { onmatch(KEYWORD); }
"case" { onmatch(KEYWORD); }
"char" { onmatch(KEYWORD); }
"const" { onmatch(KEYWORD); }
"continue" { onmatch(KEYWORD); }
"default" { onmatch(KEYWORD); }
"do" { onmatch(KEYWORD); }
"double" { onmatch(KEYWORD); }
"else" { onmatch(KEYWORD); }
"enum" { onmatch(KEYWORD); }
"extern" { onmatch(KEYWORD); }
"float" { onmatch(KEYWORD); }
"for" { onmatch(KEYWORD); }
"goto" { onmatch(KEYWORD); }
"if" { onmatch(KEYWORD); }
"int" { onmatch(KEYWORD); }
"long" { onmatch(KEYWORD); }
"register" { onmatch(KEYWORD); }
"return" { onmatch(KEYWORD); }
"short" { onmatch(KEYWORD); }
"signed" { onmatch(KEYWORD); }
"sizeof" { onmatch(KEYWORD); }
"static" { onmatch(KEYWORD); }
"struct" { onmatch(KEYWORD); }
"switch" { onmatch(KEYWORD); }
"typedef" { onmatch(KEYWORD); }
"union" { onmatch(KEYWORD); }
"unsigned" { onmatch(KEYWORD); }
"void" { onmatch(KEYWORD); }
"volatile" { onmatch(KEYWORD); }
"while" { onmatch(KEYWORD); }
"inline" { onmatch(KEYWORD); }
"_Bool" { onmatch(KEYWORD); }
"_Complex" { onmatch(KEYWORD); }
"_Imaginary" { onmatch(KEYWORD); }
"restrict" { onmatch(KEYWORD); }

{letter}({letter}|{digit}|_)* { onmatch(IDENTIFIER); }

{hex}({int_type})? { onmatch(INT_CONST); }
{octa}({int_type})? { onmatch(INT_CONST); }
0|[1-9][0-9]*({int_type})? { onmatch(INT_CONST); }

\'([^'\\\n]|{escaped_char})\' {onmatch(CHAR_CONST);}

({float_no_e}){float_type}? {onmatch(REAL_CONST);}

(({float_no_e})|{digit}+|{hex})(e|E)(\+|-)?{digit}+{float_type}? {onmatch(REAL_CONST);}

(\"([^\"\n]|(\\\n))*\"({WS})*)+ { onmatch(STR_CONST); }

"=" {onmatch(ASSIGN_OP);}
"+=" {onmatch(ASSIGN_OP);}
"-=" {onmatch(ASSIGN_OP);}
"*=" {onmatch(ASSIGN_OP);}
"/=" {onmatch(ASSIGN_OP);}
"%=" {onmatch(ASSIGN_OP);}
"<<=" {onmatch(ASSIGN_OP);}
">>=" {onmatch(ASSIGN_OP);}
"&=" {onmatch(ASSIGN_OP);}
"^=" {onmatch(ASSIGN_OP);}
"|=" {onmatch(ASSIGN_OP);}

"++" {onmatch(INCRE_DECRE_OP);}
"--" {onmatch(INCRE_DECRE_OP);}

"/" {onmatch(ARITHMETIC_OP);}
"%" {onmatch(ARITHMETIC_OP);}

"==" {onmatch(COMPARISON_OP);}
"!=" {onmatch(COMPARISON_OP);}
"<" {onmatch(COMPARISON_OP);}
">" {onmatch(COMPARISON_OP);}
"<=" {onmatch(COMPARISON_OP);}
">=" {onmatch(COMPARISON_OP);}

"&&" {onmatch(LOGICAL_OP);}
"||" {onmatch(LOGICAL_OP);}
"!" {onmatch(LOGICAL_OP);}

"<<" {onmatch(BIT_OP);}
">>" {onmatch(BIT_OP);}
"|" {onmatch(BIT_OP);}
"^" {onmatch(BIT_OP);}
"~" {onmatch(BIT_OP);}

"?" {onmatch(QUESTION_MARK);}
"*" {onmatch(STAR);}
"&" {onmatch(AND);}
"+" {onmatch(PLUS);}
"-" {onmatch(MINUS);}


\( {onmatch(LEFT_PARENTHESIS); }
\) {onmatch(RIGHT_PARENTHESIS);}
\[ {onmatch(LEFT_BRACKET);}
\] {onmatch(RIGHT_BRACKET);}
\{ {onmatch(LEFT_BRACE);}
\} {onmatch(RIGHT_BRACE);}
\; {onmatch(SEMICOLON);}
\, {onmatch(COMMA);}
\. {onmatch(DOT);}
\: {onmatch(COLON);}

{WS}* { /* ignored */}

"..." {onmatch(ELLIPSIS);}

. { 
    printf("\033[31mEncountered unknown char %s near line %d.\033[0m\n", yytext, yylineno);
    #ifdef CRASH_ON_UNKNOWN 
    return 1;
    #endif
}



%%

void handleComment() {
    printf("<" COMMENT ", ");
    
    while(1) {
        char c = input();
        if (c == '*') {
            char c1 = input();
            if (c1 == '/') {
                break;
            } else {
                putchar(c);
            }
        } else {
            putchar(c);
        }
    }

    printf(">\n");

    
}

void onmatch(char * type) {
    printf("<%s, %s>\n", type, yytext);
}

int yywrap() 
{ 
   return(1); 
} 

int main()
{
    yylex();
}
