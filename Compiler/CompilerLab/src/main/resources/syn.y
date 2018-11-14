%token VOID ELLIPSIS INT RETURN IF ELSE WHILE
%token INT_CONST STR_CONST
%token OR_OR EQUAL MINUS PLUS STAR LT LG
%token LEFT_PARENTHESIS RIGHT_PARENTHESIS LEFT_BRACE RIGHT_BRACE INCRE SEMICOLON
%token IDENTIFIER FUNC_NAME

%%

start:
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
    type IDENTIFIER LEFT_PARENTHESIS func_param RIGHT_PARENTHESIS
  ;

func_definition:
    func_prototype braced_statement
  ;

func_param:
    type IDENTIFIER
  | ELLIPSIS
  ;

type:
    TYPE_NAME
  | INT
  | VOID
  ;

statement:
    braced_statement
  | if_statement
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
    add_minus_expression
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
  | mul_div_expression STAR cast_expression
  | mul_div_expression DIV cast_expression
  ;

unary_expression:
    atomic_expression
  | FUNC_NAME LEFT_PARENTHESIS func_call_parameters RIGHT_PARENTHESIS
  | IDENTIFIER INC
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

