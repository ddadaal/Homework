%define parse.error verbose
%define parse.lac full
%define parse.trace

%{
  #include <stdio.h>

  // #define ENABLE_TRACE


  int yylex(); 
  int yyerror(const char *); 
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
    start declaration
  | start func_definition
  | declaration
  | func_definition
  ;

declaration:
  typedef_declaration
  | struct_or_union_declaration
  | variable_declaration
  ;

variable_declaration:
    type variable_or_member SEMICOLON
  | type variable_or_member ASSIGN expression SEMICOLON
  ;

statement: 
    braced_statement
  | expression_statement
  | if_statement
  | switch_statement
  ;

braced_statement:
   LEFT_BRACE braced_statement_items RIGHT_BRACE
  ;

braced_statement_items:
    braced_statement_items braced_statement_item
  | 
  ;

braced_statement_item:
    declaration
  | statement
  ;


if_statement:
    IF LEFT_PARENTHESIS expression RIGHT_PARENTHESIS statement
  | IF LEFT_PARENTHESIS expression RIGHT_PARENTHESIS statement ELSE statement
  ; 

switch_statement:
    SWITCH LEFT_PARENTHESIS expression RIGHT_PARENTHESIS switch_case_part optional_default
  ;

switch_case_part:
    switch_case_part CASE constant COLON statement
  | 
  ;

optional_default:
  
  | DEFAULT COLON statement 
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
    lvalue assignment_operator assignment_expression
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
  | logical_expression OR equality_expression
  | logical_expression AND equality_expression
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
    atomic_expression
  | lvalue
  | rvalue
  ;

lvalue: 
    IDENTIFIER
  | IDENTIFIER DOT IDENTIFIER
  | IDENTIFIER LEFT_BRACKET expression RIGHT_BRACKET
  | IDENTIFIER PTR_ACCESS IDENTIFIER
  ;

rvalue: 
    INCRE lvalue
  | DECRE lvalue
  | lvalue INCRE
  | lvalue DECRE
  | AND lrvalue
  | SIZEOF lrvalue
  | SIZEOF LEFT_PARENTHESIS lrvalue RIGHT_PARENTHESIS
  | PLUS lrvalue
  | MINUS lrvalue
  | EXCLAMATION lrvalue
  | NEGATE lrvalue
  | lrvalue LEFT_PARENTHESIS RIGHT_PARENTHESIS
  | lrvalue LEFT_PARENTHESIS func_call_parameters RIGHT_PARENTHESIS
  ;

func_call_parameters: 
    assignment_expression
  | func_call_parameters COMMA assignment_expression
  ;

lrvalue: 
    lvalue 
  | rvalue
  ;

atomic_expression:
    IDENTIFIER
  | constant
  | LEFT_PARENTHESIS expression RIGHT_PARENTHESIS 
  ;

constant:
    INT_CONST
  | FLOAT_CONST
  | STR_CONST
  | CHAR_CONST
  ;

typedef_declaration: 
    TYPEDEF type IDENTIFIER SEMICOLON
  | TYPEDEF struct_or_union_declaration IDENTIFIER SEMICOLON { printf("A type %s with struct\n", $3); }
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
  ;

struct_or_union_type_definition_head: 
    struct_or_union IDENTIFIER { printf("A struct name : %s\n", $2); }
  | struct_or_union
  ;

type: 
    basic_type 
  | struct_type
  ;

basic_type: 
    INT
  | FLOAT
  | DOUBLE
  | CHAR
  ;

struct_type: 
    IDENTIFIER
  ;

variable_or_member: 
    IDENTIFIER
  | pointer
  ;

pointer: 
    STAR pointer
  | STAR IDENTIFIER
  ;

func_definition: 
    func_prototype LEFT_BRACE func_body RIGHT_BRACE  { printf("A function declaration\n"); }
  ;

func_body: 
    func_body statement
  ;

func_prototype: 
    func_decorators func_return_type IDENTIFIER LEFT_PARENTHESIS argument_list RIGHT_PARENTHESIS { printf("A function prototype %s\n", $3);}
  ;

func_return_type: 
    type 
  | VOID
  ;

func_decorator: 
    INLINE 
  | STATIC
  ;

func_decorators: 
    func_decorators func_decorator
  |
  ;

argument_list: 
    canonical_argument_list COMMA ELLIPSIS { printf("argument list with ..."); }
  | canonical_argument_list { printf("canonical_argument_list\n");}
  | ELLIPSIS
  ;

canonical_argument_list: 
    an_argument
  | canonical_argument_list COMMA an_argument
  |
  ;

an_argument: 
    type variable_or_member
  ;


%%
int yyerror(const char * err)
{
  printf("Syntax error:%s\n", err);
}
 
 
int main()
{
  #ifdef ENABLE_TRACE
  yydebug = 1;
  #endif
  yyparse();
}