# Compiler Lab 2

A C99 compatible syntax analyzer

# References

[GNU C Yacc](http://www.quut.com/c/ANSI-C-grammar-y-2011.html)

[GNU C Manual](https://www.gnu.org/software/gnu-c-manual/gnu-c-manual.pdf)

# Pitfalls

## 1. Preprocessing not included

preprocessing isn't included in syntax analysis. So there is no need to parse preprocessing directive during syntax analysis.

## 2. Associative and precedence

Precedence level

==, != is higher than <, >, <=, >=

+, - is higher than <<, >>

## 3. Limitations

1. struct and union must be typedefed to be used.

```c
// not allowed

struct A { int a; };
struct A a;

// allowed
typedef struct { int a;} A;
A a;

```

2. function pointer is not allowed.

3. Identify lvalue and rvalue during syntax analysis results in numerous S/R or R/R conflicts.

4. Allow void a;

Incomplete type check should happen in the semantics analysis.

5. A optional Conflict

The following will lead to a conflict and can't parse a function definition.

```bison
start:
    variable 
  | function definition
  ;

variable:
    type IDNETIFIER SEMICOLON;
  ;

function_definition:
    function_decorators type IDENTIFIER LEFT_PARENTHESIS RIGHT_PARENTHESIS
  ;

function_decorators:
  
  | function_decorators function_decorator
  ;

function_decorator:
    INLINE
  | STATIC
  ;

```

The following would work instead

```bison
start:
    variable 
  | function definition
  ;


variable:
    type IDNETIFIER SEMICOLON;
  ;

function_definition:
    type IDENTIFIER LEFT_PARENTHESIS RIGHT_PARENTHESIS
  | function_decorators type IDENTIFIER LEFT PARENTHESIS RIGHT_PARENTHESIS
  ;

function_decorators:
  function_decorator
  | function_decorators function_decorator
  ;

function_decorator:
    INLINE
  | STATIC
  ;
```

6. switch case and default are just labeled statements

7. List* a is considered multiplication instead variable declaration. Can only be solved using a global symbol table.