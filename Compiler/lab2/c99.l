%option yylineno

%{
    #include <stdio.h>
    #include "lex.h"
    
    void onmatch(char * type);
    void handleComment();
    int handleError();

    // comment this to make the program NOT crash on unknown token
    #define CRASH_ON_UNKNOWN
    
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


"auto" { onmatch(AUTO); }
"break" { onmatch(BREAK); }
"case" { onmatch(CASE); }
"char" { onmatch(CHAR); }
"const" { onmatch(CONST); }
"continue" { onmatch(CONTINUE); }
"default" { onmatch(DEFAULT); }
"do" { onmatch(DO); }
"double" { onmatch(DOUBLE); }
"else" { onmatch(ELSE); }
"enum" { onmatch(ENUM); }
"extern" { onmatch(EXTERN); }
"float" { onmatch(FLOAT); }
"for" { onmatch(FOR); }
"goto" { onmatch(GOTO); }
"if" { onmatch(IF); }
"int" { onmatch(INT); }
"long" { onmatch(LONG); }
"register" { onmatch(REGISTER); }
"return" { onmatch(RETURN); }
"short" { onmatch(SHORT); }
"signed" { onmatch(SIGNED); }
"sizeof" { onmatch(SIZEOF); }
"static" { onmatch(STATIC); }
"struct" { onmatch(STRUCT); }
"switch" { onmatch(SWITCH); }
"typedef" { onmatch(TYPEDEF); }
"union" { onmatch(UNION); }
"unsigned" { onmatch(UNSIGNED); }
"void" { onmatch(VOID); }
"volatile" { onmatch(VOLATILE); }
"while" { onmatch(WHILE); }
"inline" { onmatch(INLINE); }
"_Bool" { onmatch(_BOOL); }
"_Complex" { onmatch(_COMPLEX); }
"_Imaginary" { onmatch(_IMAGINARY); }
"restrict" { onmatch(RESTRICT); }

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
"..." {onmatch(ELLIPSIS);}

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
