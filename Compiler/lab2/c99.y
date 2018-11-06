%{
  #include <stdio.h>
  int yylex();  //只是一个声明
  int yyerror(char *); //必须要有
%}
%token <sv> IDENTIFIER
%token <iv> INT_CONST CHAR_CONST 
%token <fv> FLOAT_CONST
%token <sv> STR_CONST
%token AUTO BREAK CASE CHAR CONST CONTINUE DEFAULT DO DOUBLE ELSE ENUM EXTERN FLOAT FOR GOTO IF INT LONG REGISTER RETURN SHORT SIGNED SIZEOF STATIC STRUCT SWITCH TYPEDEF UNION UNSIGNED VOID VOLATILE WHILE INLINE _BOOL _COMPLEX _IMAGINARY RESTRICT
%token ASSIGN_OP INCRE_DECRE_OP ARITHMETIC_OP COMPARISON_OP LOGICAL_OP BIT_OP POINTER_OP QUESTION_MARK LEFT_PARENTHESIS RIGHT_PARENTHESIS LEFT_BRACKET RIGHT_BRACKET LEFT_BRACE RIGHT_BRACE SEMICOLON COMMA DOT COLON ELLIPSIS
%token STAR AND PLUS MINUS
%union {
  int iv;
  char *sv;
  double fv;
}

%%

func_decl : typename IDENTIFIER LEFT_PARENTHESIS argment_list RIGHT_PARENTHESIS { printf("A function declaration %s\n", $2); }

;

argment_list : an_argment
    |  argment_list COMMA an_argment
    | 
    ;

an_argment: typename IDENTIFIER

typename : IDENTIFIER { printf("Typename %s\n", $1 ); }
    ;

%%
int yyerror(char * err)
{
  printf("Syntax error:%s\n", err);
}
 
 
int main()
{
  yyparse();
}