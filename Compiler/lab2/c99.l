%option yylineno

%{
    #include <stdio.h>
    // #include "lex.h"
    #include "c99yy.h"  //由bison生成


    extern int yyerror(char *);
    
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

\/\/.* {} 
\/\* { handleComment(); }


"auto" {  return AUTO; }
"break" {  return BREAK; }
"case" {  return CASE; }
"char" {  return CHAR;}
"const" {  return CONST; }
"continue" {  return CONTINUE; }
"default" {  return DEFAULT; }
"do" {  return DO; }
"double" {  return DOUBLE;}
"else" {   return ELSE;}
"enum" {  return ENUM;}
"extern" {  return EXTERN;}
"float" {  return FLOAT;}
"for" {   return FOR;}
"goto" {  return GOTO; }
"if" {  return IF; }
"int" {  return INT; }
"long" {  return LONG; }
"register" {   return REGISTER; }
"return" {  return RETURN; }
"short" {  return SHORT; }
"signed" {  return SIGNED; }
"sizeof" {  return SIZEOF;}
"static" {  return STATIC;}
"struct" {  return STRUCT; }
"switch" {  return SWITCH;}
"typedef" {  return TYPEDEF;}
"union" {   return UNION;}
"unsigned" {  return UNSIGNED; }
"void" {  return VOID;}
"volatile" {  return VOLATILE;}
"while" {  return WHILE;}
"inline" {  return INLINE;}
"_Bool" {   return _BOOL;}
"_Complex" {  return _COMPLEX;}
"_Imaginary" {  return _IMAGINARY;}
"restrict" {  return RESTRICT;}

{letter}({letter}|{digit}|_)* {  yylval.sv = strdup(yytext); return IDENTIFIER;  }

{hex}({int_type})? {  yylval.iv = strtol(yytext, 0, 10); return INT_CONST; }
{octa}({int_type})? {  yylval.iv = strtol(yytext, 0, 10); return INT_CONST; }
0|[1-9][0-9]*({int_type})? { yylval.iv = strtol(yytext, 0, 10); return INT_CONST; }

\'([^'\\\n]|{escaped_char})\' {}

({float_no_e}){float_type}? {}

(({float_no_e})|{digit}+|{hex})(e|E)(\+|-)?{digit}+{float_type}? {}

(\"([^\"\n]|(\\\n))*\"({WS})*)+ { yylval.sv = strdup(yytext); return STR_CONST; }

"=" { return ASSIGN; }
"+=" { return PLUS_ASSIGN; }
"-=" { return MINUS_ASSIGN; }
"*=" { return MUL_ASSIGN; }
"/=" { return DIV_ASSIGN; }
"%=" { return MOD_ASSIGN; }
"<<=" { return LEFT_SHIFT_ASSIGN; }
">>=" { return RIGHT_SHIFT_ASSIGN; }
"&=" { return AND_ASSIGN; }
"^=" { return XOR_ASSIGN; }
"|=" { return OR_ASSIGN; }
"&&=" { return AND_AND_ASSIGN; }
"||=" { return OR_OR_ASSIGN; }

"++" { return INCRE; }
"--" { return DECRE; }

"/" { return DIV; }
"%" { return MOD; }

"==" { return EQUAL; }
"!=" { return NOT_EQUAL; }
"<" { return LT; }
">" { return GT;}
"<=" { return LE;}
">=" { return GE;}

"->" { return PTR_ACCESS; }

"&&" { return AND_AND; }
"||" { return OR_OR; }
"!" { return EXCLAMATION; }
"..." { return ELLIPSIS; }

"<<" { return LEFT_SHIFT; }
">>" { return RIGHT_SHIFT; }
"|" { return OR; }
"^" { return XOR; }
"~" { return NEGATE; }

"?" { return QUESTION_MARK; }
"*" { return STAR;}
"&" { return AND; } 
"+" { return PLUS; }
"-" { return MINUS; }


\( { return LEFT_PARENTHESIS; }
\) { return RIGHT_PARENTHESIS; }
\[ { return LEFT_BRACKET; }
\] { return RIGHT_BRACKET; }
\{ { return LEFT_BRACE; }
\} { return RIGHT_BRACE; }
\; { return SEMICOLON; }
\, { return COMMA; }
\. { return DOT; }
\: { return COLON; }

{WS}* { /* do nothing */ }



. { 
    printf("\033[31mEncountered unknown char %s near line %d.\033[0m\n", yytext, yylineno);
    #ifdef CRASH_ON_UNKNOWN 
    return 1;
    #endif
}



%%

void handleComment() {
    
    while(1) {
        char c = input();
        if (c == '*') {
            char c1 = input();
            if (c1 == '/') {
                break;
            }
        }
    }
    
}

void onmatch(char * type) {
    printf("<%s, %s>\n", type, yytext);
}

int yywrap() 
{ 
   return(1); 
} 