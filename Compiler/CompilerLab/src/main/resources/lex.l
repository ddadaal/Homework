
%%

"void" { return VOID; }
"int" { return INT; }
"return" { return RETURN; }
"while" { return WHILE; }
"if" { return IF; }
"else" { return ELSE; }


"..." { return ELLIPSIS; }
"==" { return EQUAL; }
"=" { return ASSIGN; }
";" { return SEMICOLON; }
"*" { return STAR; }
"||" { return OR_OR; }
"{" { return LEFT_BRACE; }
"}" { return RIGHT_BRACE; }
"(" { return LEFT_PARENTHESIS; }
")" { return RIGHT_PARENTHESIS; }
"," { return COMMA; }
"+" { return PLUS; }
"-" { return MINUS; }
"/" { return DIV; }
"++" { return INC; }
"<" { return LT; }
"<=" { return LE; }


[a-zA-Z]([0-9a-zA-Z_])* { return IDENTIFIER; }

0|[1-9][0-9]* { return INT_CONST; }

\"([^\n\"]|\\\n]*\" { return STR_CONST; }

[ \n] { /* ignored */ }

. { return UNKNOWN; }