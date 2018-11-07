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

1. Variable initialization list is not allowed.
   
```c
// not allowed
int a=1, b=2;
int a, b;
int a=1, b;

// allowed
int b =2;
```

2. struct and union must be typedefed to be used.

```c
// not allowed

struct A { int a; };
struct A a;

// allowed
typedef struct { int a;} A;
A a;

```

3. function pointer is not allowed.

4. Label and goto is not allowed.

```c
// not allowed
a:
    int a =0;
    goto a;
```