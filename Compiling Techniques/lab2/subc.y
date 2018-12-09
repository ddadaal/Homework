%define parse.error verbose
%define parse.lac full
%define parse.trace

%{
  #include <stdio.h>
  #include "symtable.h"

  #define ENABLE_TRACE


  int yylex(); 
  void printExpression(char* left, char* right)
  int yyerror(const char *); 
  void handleTypedef(char*);
%}

%token <sv> IDENTIFIER
%token <iv> INT_CONST CHAR_CONST 
%token <fv> FLOAT_CONST
%token <sv> STR_CONST
%token AUTO BREAK CASE CHAR CONST CONTINUE DEFAULT DO DOUBLE ELSE ENUM EXTERN FLOAT FOR GOTO IF INT LONG REGISTER RETURN SHORT SIGNED SIZEOF STATIC STRUCT SWITCH TYPEDEF UNION UNSIGNED VOID VOLATILE WHILE INLINE _BOOL _COMPLEX _IMAGINARY RESTRICT
%token LEFT_PARENTHESIS RIGHT_PARENTHESIS LEFT_BRACKET RIGHT_BRACKET LEFT_BRACE RIGHT_BRACE SEMICOLON COMMA DOT COLON ELLIPSIS
%token STAR AND PLUS MINUS QUESTION_MARK PTR_ACCESS
%token ASSIGN PLUS_ASSIGN MINUS_ASSIGN MUL_ASSIGN DIV_ASSIGN MOD_ASSIGN LEFT_SHIFT_ASSIGN RIGHT_SHIFT_ASSIGN AND_ASSIGN XOR_ASSIGN OR_ASSIGN AND_AND_ASSIGN OR_OR_ASSIGN
%token INCRE DECRE
%token TYPE_NAME
%token DIV MOD
%token LT GT LE GE EQUAL NOT_EQUAL
%token AND_AND OR_OR EXCLAMATION
%token LEFT_SHIFT RIGHT_SHIFT NEGATE OR XOR
%union {
  int iv;
  char *sv;
  double fv;
}

%%

start: 
    start root_declaration { printExpression("start", "start root_declaration"); }
  | root_declaration { printExpression("start", "root_declaration"); }
  ;

root_declaration:
    func_definition
  | declaration
  ;

declaration:
  typedef_declaration
  | struct_or_union_declaration
  | variable_declaration
  | func_declaration
  ;

type_name_declaration:
    type variable_or_member
  ;

variable_declaration:
    type variable_declaration_list SEMICOLON
  ;

variable_declaration_list:
    variable_declaration_item
  | variable_declaration_list COMMA variable_declaration_item
  ;

variable_declaration_item:
    variable_or_member
  | variable_or_member ASSIGN expression
  ;

statement: 
    braced_statement
  | expression_statement
  | if_statement
  | switch_statement
  | labeled_statement
  | while_statement
  | for_statement
  | goto_statement
  | return_statement
  | continue_and_break_statement
  | declaration
  ;

continue_and_break_statement:
    CONTINUE SEMICOLON
  | BREAK SEMICOLON
  ;

goto_statement:
    GOTO IDENTIFIER SEMICOLON
  ;

return_statement:
    RETURN SEMICOLON
  | RETURN expression SEMICOLON
  ;

while_statement:
    WHILE LEFT_PARENTHESIS expression RIGHT_PARENTHESIS statement
  ;

for_statement:
    FOR LEFT_PARENTHESIS expression_statement expression_statement expression RIGHT_PARENTHESIS statement
  | FOR LEFT_PARENTHESIS variable_declaration expression_statement expression RIGHT_PARENTHESIS statement
  ;  


labeled_statement:
    IDENTIFIER COLON statement
  | CASE constant COLON statement
  | DEFAULT COLON statement
  ;

braced_statement:
    LEFT_BRACE braced_statement_items RIGHT_BRACE
  ;

braced_statement_items:
    braced_statement_items statement
  | 
  ;


if_statement:
    IF LEFT_PARENTHESIS expression RIGHT_PARENTHESIS statement
  | IF LEFT_PARENTHESIS expression RIGHT_PARENTHESIS statement ELSE statement
  ; 

switch_statement:
    SWITCH LEFT_PARENTHESIS expression RIGHT_PARENTHESIS braced_statement
  ;

expression_statement:
    expression SEMICOLON
  | SEMICOLON
  ;

expression: 
    assignment_expression
  | expression COMMA assignment_expression
  ; 

assignment_expression: 
    unary_expression assignment_operator assignment_expression
  | conditional_expression
  ;

assignment_operator: 
    ASSIGN
  | PLUS_ASSIGN
  | MINUS_ASSIGN
  | MUL_ASSIGN
  | DIV_ASSIGN
  | MOD_ASSIGN
  | LEFT_SHIFT_ASSIGN
  | RIGHT_SHIFT_ASSIGN
  | AND_ASSIGN
  | OR_ASSIGN
  | XOR_ASSIGN
  | AND_AND_ASSIGN
  | OR_OR_ASSIGN
  ;

conditional_expression: 
    logical_expression
  | logical_expression QUESTION_MARK expression COLON conditional_expression
  ;

logical_expression: 
    equality_expression
  | logical_expression OR_OR equality_expression
  | logical_expression AND_AND equality_expression
  ;

equality_expression:
    comparison_expression
  | equality_expression EQUAL comparison_expression
  | equality_expression NOT_EQUAL comparison_expression 
  ;

comparison_expression:
    shift_expression
  | comparison_expression LT shift_expression
  | comparison_expression GT shift_expression
  | comparison_expression LE shift_expression
  | comparison_expression GE shift_expression
  ;

shift_expression:
    add_minus_expression
  | shift_expression LEFT_SHIFT add_minus_expression
  | shift_expression RIGHT_SHIFT add_minus_expression
  ;

add_minus_expression:
    mul_div_expression
  | add_minus_expression PLUS mul_div_expression
  | add_minus_expression MINUS mul_div_expression
  ;

mul_div_expression:
    cast_expression
  | mul_div_expression STAR cast_expression
  | mul_div_expression DIV cast_expression
  | mul_div_expression MOD cast_expression
  ;

cast_expression:
    unary_expression
  | LEFT_PARENTHESIS cast_type RIGHT_PARENTHESIS cast_expression
  ;

cast_type:
    type
  | cast_type STAR
  ;

unary_expression:
    access_expression
  | unary_operator unary_expression
  | INCRE unary_expression
  | DECRE unary_expression
  | SIZEOF unary_expression
  | SIZEOF LEFT_PARENTHESIS unary_expression RIGHT_PARENTHESIS
  | SIZEOF type
  | SIZEOF LEFT_PARENTHESIS type RIGHT_PARENTHESIS
  ;

unary_operator:
    PLUS
  | MINUS
  | AND
  | STAR
  | NEGATE
  | EXCLAMATION
  ;

access_expression:
    atomic_expression
  | access_expression LEFT_BRACKET expression RIGHT_BRACKET
  | access_expression LEFT_PARENTHESIS RIGHT_PARENTHESIS { printf("function call with no parameter.\n"); }
  | access_expression LEFT_PARENTHESIS func_call_parameters RIGHT_PARENTHESIS { printf("function call with parameters.\n"); }
  | access_expression DOT IDENTIFIER
  | access_expression PTR_ACCESS IDENTIFIER
  | access_expression INCRE
  | access_expression DECRE
  ;

func_call_parameters: 
    assignment_expression
  | func_call_parameters COMMA assignment_expression
  ;

atomic_expression:
    IDENTIFIER
  | constant
  | LEFT_PARENTHESIS expression RIGHT_PARENTHESIS 
  ;

constant:
    INT_CONST { printf("Integer constant: %d\n", $1); }
  | FLOAT_CONST { printf("Float constant: %.2f\n", $1); }
  | STR_CONST { printf("Str constant: %s\n", $1); }
  | CHAR_CONST { printf("Char constant: %c\n", $1); }
  ;

typedef_declaration: 
    TYPEDEF typedef_target IDENTIFIER SEMICOLON { handleTypedef($3); }
  ;

typedef_target:
    type
  | struct_or_union_declaration
  | typedef_target STAR
  ;

struct_or_union_declaration: 
    struct_or_union_type_definition_head LEFT_BRACE struct_or_union_members RIGHT_BRACE { printf("A struct\n"); }
  ;

struct_or_union: 
    STRUCT
  | UNION
  ;

struct_or_union_members:
    struct_or_union_members struct_or_union_member
  | 
  ;

struct_or_union_member: 
    type variable_or_member SEMICOLON { printf("identifier\n");}
  | IDENTIFIER variable_or_member SEMICOLON
  ;

struct_or_union_type_definition_head: 
    struct_or_union IDENTIFIER { printf("A struct name : %s\n", $2); }
  | struct_or_union
  ;

type: 
    basic_type
  | TYPE_NAME
  ;

basic_type: 
    INT
  | FLOAT
  | DOUBLE
  | CHAR
  | VOID
  ;

variable_or_member: 
    IDENTIFIER
  | STAR variable_or_member
  ;

func_definition: 
    func_prototype braced_statement  { printf("A function declaration\n"); }
  ;

func_prototype:
    function_specifier LEFT_PARENTHESIS argument_list RIGHT_PARENTHESIS { printf("A function prototype \n");}
  ;

func_declaration:
    func_prototype SEMICOLON
  ;

function_specifier:
    type_name_declaration
  | func_decorators type_name_declaration
  ;

func_decorator: 
    INLINE 
  | STATIC
  ;

func_decorators: 
    func_decorators func_decorator
  | func_decorator
  ;

argument_list: 
    canonical_argument_list COMMA ELLIPSIS
  | canonical_argument_list
  | ELLIPSIS
  |
  ;

canonical_argument_list: 
    type_name_declaration
  | canonical_argument_list COMMA type_name_declaration
  ;


%%

void printExpression(char* left, char* right) {
  printf("%s -> %s\n", left, right);
}

void handleTypedef(char* str) {
  if (!find(str)) {
    add(str);
    printf("Adding a new type %s\n", str);
  }
  printf("Detected existing type %s\n", str);
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