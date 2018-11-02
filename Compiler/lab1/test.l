%{
    #include <stdio.h>
    void onmatch(char * type);
    
    #define KEYWORD "Keyword"
    #define IDENTIFIER "Identifier"
    #define COMMENT "Comment"
    #define INT_CONST "Integer Constant"
    #define CHAR_CONST "Character Constant"
    #define REAL_CONST "Real Number Constant"
    #define STR_CONST "String Constant"
    #define OPERATOR "Operator"
    #define UNKNOWN "Unknown"
%}

letter [a-zA-Z]
digit [0-9]
WS [ \n\t]
octa_num [0-7]+
octa 0{octa_num}
hex_num ({digit}|[a-fA-F])+
hex 0(x|X){hex_num}
int_type (l|L)|((u|U)(l|L)?(l|L)?)
escaped_char \\((\\)|(\?)|(\')|(\")|[abefnrtv]|([0-7]{1,3})|x({hex_num}))
char {letter}|{digit}|{escaped_char}
float_no_e {digit}+\.{digit}*|{digit}*\.{digit}+
float_type (F|f|L|l)
float_e (e|E)(\+|-)?{digit}+

%%



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

\'({char})\' {onmatch(CHAR_CONST);}

({float_no_e}){float_type}? {onmatch(REAL_CONST);}
(({float_no_e})|{digit}+|{hex}){float_e}{float_type}? {onmatch(REAL_CONST);}

(\"((\\\n)|\'|({char})|[ \t])*\"({WS})*)+ { onmatch(STR_CONST); }

{WS}* { /* ignored */}

. { onmatch(UNKNOWN); }


%%



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
