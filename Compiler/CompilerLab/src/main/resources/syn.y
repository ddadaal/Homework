%token VOID ELLIPSIS INT RETURN IF ELSE WHILE
%token INT_CONST STR_CONST
%token OR_OR EQUAL MINUS PLUS STAR
%token LEFT_PARENTHESIS RIGHT_PARENTHESIS LEFT_BRACE RIGHT_BRACE INCRE SEMICOLON
%token IDENTIFIER FUNC_NAME

%%

start:

  | func_definition
  ;



variable_declaration:
    type IDENTIFIER
  | type STAR IDENTIFIER
  ;

func_prototype:
    type IDENTIFIER
  ;

func_definition:
    func_prototype LEFT_PARENTHESIS type IDENTIFIER RIGHT_PARENTHESIS braced_statement
  ;

type:
    TYPE_NAME
  | INT
  ;

statement:
    braced_statement
  | if_statement
  | break_statement
  | while_statement
  | return_statement
  | expression SEMICOLON
  | SEMICOLON
  ;

braced_statement:
    LEFT_BRACE braced_statement_items RIGHT_BRACE
  ;

braced_statement_items:
    statement
  | statement braced_statement_items
  ;

break_statement:
    BREAK SEMICOLON;

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
  | equality_expression
  ;

equality_expression:
    add_minus_expression
  | equality_expression EQUAL comparison_expression
  | equality_expression NOT_EQUAL comparison_expression
  ;

add_minus_expression:
    mul_div_expression
  | add_minus_expression PLUS mul_div_expression
  | add_minus_expression MINUS mul_div_expression
  ;

mul_div_expression:
    unary_expression
  | mul_div_expression STAR cast_expression
  | mul_div_expression DIV cast_expression
  ;

unary_expression:
    atomic_expression
  | SIZEOF LEFT_PARENTHESIS type RIGHT_PARENTHESIS
  | FUNC_NAME LEFT_PARENTHESIS func_call_parameters RIGHT_PARENTHESIS
  | IDENTIFIER PTR_ACCESS IDENTIFIER
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

