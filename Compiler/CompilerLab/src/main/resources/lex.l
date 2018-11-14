%option yylineno

%{
    #include <stdio.h>
    // #include "lex.h"
    #include "subcyy.h"  //由bison生成
    #include "symtable.h"



    extern int yyerror(char *);
    
    void onmatch(char * type);
    void handleComment();
    int handleError();
    int handleIdentifier();

    

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

void { return VOID; }
int { return INT; }
return { return RETURN; }
while { return WHILE; }
if { return IF; }
else { return ELSE; }


\.\.\. { return ELLIPSIS; }
== { return EQUAL; }
= { return ASSIGN; }
\; { return SEMICOLON; }
\* { return STAR; }
\|\| { return OR_OR; }
\{ { return LEFT_BRACE; }
\} { return RIGHT_BRACE; }
\( { return LEFT_PARENTHESIS; }
\) { return RIGHT_PARENTHESIS; }
, { return COMMA; }
\+ { return PLUS; }
\- { return MINUS; }
\/ { return DIV; }
\+\+ { return INC; }
\< { return LT; }
\<= { return LE; }

[a-zA-Z]([0-9a-zA-Z_])* { return handleIdentifier(); }

0|[1-9][0-9]* { yylval.iv = strtol(yytext, 0, 10);  return INT_CONST; }

\"([^\"\n]|\\\n)*\" { yylval.sv = strdup(yytext);  return STR_CONST; }

[\ \n] { /* ignored */ }

. { 
    printf("\033[31mEncountered unknown char %s near line %d.\033[0m\n", yytext, yylineno);
    #ifdef CRASH_ON_UNKNOWN 
    return 1;
    #endif
}



%%

int handleIdentifier() {
    yylval.sv = strdup(yytext);

    if (find(yytext)) {
        return FUNC_NAME;
    }
    return IDENTIFIER;
}

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