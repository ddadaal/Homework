%define parse.error verbose
%define parse.lac full
%define parse.trace

%{
  #include <stdio.h>
  #include "symtable.h"

  // #define ENABLE_TRACE


  int yylex();
  int yyerror(const char *);
  void handleTypedef(char*);
%}

%token <sv> IDENTIFIER FUNC_NAME
%token <iv> INT_CONST CHAR_CONST
%token <fv> FLOAT_CONST
%token <sv> STR_CONST
%token VOID ELLIPSIS INT RETURN IF ELSE WHILE
%token OR_OR EQUAL MINUS PLUS STAR LT LG
%token LEFT_PARENTHESIS RIGHT_PARENTHESIS LEFT_BRACE RIGHT_BRACE INCRE SEMICOLON
%token ASSIGN COMMA DIV INC LE NOT_EQUAL
%union {
  int iv;
  char *sv;
  double fv;
}

%%

start:

  | start top_level_element
 ;


top_level_element:
   func_definition
 | declaration
 ;

declaration:
    func_declaration
  | variable_declaration
  ;

func_declaration:
    func_prototype SEMICOLON
  ;


variable_declaration:
    type IDENTIFIER SEMICOLON
  | type IDENTIFIER ASSIGN expression SEMICOLON
  ;

func_prototype:
    type IDENTIFIER LEFT_PARENTHESIS func_param RIGHT_PARENTHESIS { handleTypedef($2); }
  ;

func_definition:
    func_prototype braced_statement
  ;

func_param:
    type IDENTIFIER
  | ELLIPSIS
  |
  ;

type:
    INT
  | VOID
  ;

statement:
    braced_statement
  | if_statement
  | while_statement
  | return_statement
  | expression SEMICOLON
  | SEMICOLON
  | declaration
  ;

braced_statement:
    LEFT_BRACE braced_statement_items RIGHT_BRACE
  ;

braced_statement_items:
    statement
  | statement braced_statement_items
  ;

if_statement:
    IF LEFT_PARENTHESIS expression RIGHT_PARENTHESIS braced_statement ELSE braced_statement
  ;

while_statement:
    WHILE LEFT_PARENTHESIS expression RIGHT_PARENTHESIS braced_statement
  ;

return_statement:
    RETURN expression SEMICOLON
  ;

expression:
    assignment_expression
  ;

assignment_expression:
    atomic_expression ASSIGN expression
  | logical_expression
  ;

logical_expression:
    equality_expression
  | logical_expression OR_OR equality_expression
  ;

equality_expression:
    comparison_expression
  | equality_expression EQUAL comparison_expression
  | equality_expression NOT_EQUAL comparison_expression
  ;

comparison_expression:
    add_minus_expression
  | comparison_expression LT add_minus_expression
  | comparison_expression LE add_minus_expression
  ;

add_minus_expression:
    mul_div_expression
  | add_minus_expression PLUS mul_div_expression
  | add_minus_expression MINUS mul_div_expression
  ;

mul_div_expression:
    unary_expression
  | mul_div_expression STAR unary_expression
  | mul_div_expression DIV unary_expression
  ;

unary_expression:
    atomic_expression
  | FUNC_NAME LEFT_PARENTHESIS func_call_parameters RIGHT_PARENTHESIS { printf("Function call %s\n", $1); }
  | IDENTIFIER INC
  | PLUS unary_expression
  | MINUS unary_expression
  ;

func_call_parameters:
    assignment_expression
  | func_call_parameters COMMA assignment_expression
  ;

atomic_expression:
    INT_CONST
  | STR_CONST
  | LEFT_PARENTHESIS expression RIGHT_PARENTHESIS
  | IDENTIFIER
  ;


%%


void handleTypedef(char* str) {
  if (!find(str)) {
    add(str);
    printf("Adding a new function %s\n", str);
  }
  printf("Detected existing function %s\n", str);
}

int yyerror(const char * err)
{
  printf("Syntax error:%s\n", err);
}


int main()
{
  initSymtable();

  #ifdef ENABLE_TRACE
  yydebug = 1;
  #endif

  yyparse();
}