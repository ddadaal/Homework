%{
    #include <stdio.h>
%}

%%

[a-zA-Z\-]+ printf("word ");
\{ printf("obrace ");
\} printf("ebrace ");
\" printf("quote ");
\"[a-zA-Z\/\.\-]*\" printf("constant. length: %d ", yyleng-2);
\; printf("semicolon ");
\n printf("\n");


%%

int yywrap() 
{ 
   return(1); 
} 

int main()
{
    yylex();
}