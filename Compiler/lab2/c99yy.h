/* A Bison parser, made by GNU Bison 3.0.5.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_C99YY_H_INCLUDED
# define YY_YY_C99YY_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    IDENTIFIER = 258,
    INT_CONST = 259,
    CHAR_CONST = 260,
    FLOAT_CONST = 261,
    STR_CONST = 262,
    AUTO = 263,
    BREAK = 264,
    CASE = 265,
    CHAR = 266,
    CONST = 267,
    CONTINUE = 268,
    DEFAULT = 269,
    DO = 270,
    DOUBLE = 271,
    ELSE = 272,
    ENUM = 273,
    EXTERN = 274,
    FLOAT = 275,
    FOR = 276,
    GOTO = 277,
    IF = 278,
    INT = 279,
    LONG = 280,
    REGISTER = 281,
    RETURN = 282,
    SHORT = 283,
    SIGNED = 284,
    SIZEOF = 285,
    STATIC = 286,
    STRUCT = 287,
    SWITCH = 288,
    TYPEDEF = 289,
    UNION = 290,
    UNSIGNED = 291,
    VOID = 292,
    VOLATILE = 293,
    WHILE = 294,
    INLINE = 295,
    _BOOL = 296,
    _COMPLEX = 297,
    _IMAGINARY = 298,
    RESTRICT = 299,
    ASSIGN_OP = 300,
    INCRE_DECRE_OP = 301,
    ARITHMETIC_OP = 302,
    COMPARISON_OP = 303,
    LOGICAL_OP = 304,
    BIT_OP = 305,
    POINTER_OP = 306,
    QUESTION_MARK = 307,
    LEFT_PARENTHESIS = 308,
    RIGHT_PARENTHESIS = 309,
    LEFT_BRACKET = 310,
    RIGHT_BRACKET = 311,
    LEFT_BRACE = 312,
    RIGHT_BRACE = 313,
    SEMICOLON = 314,
    COMMA = 315,
    DOT = 316,
    COLON = 317,
    ELLIPSIS = 318,
    STAR = 319,
    AND = 320,
    PLUS = 321,
    MINUS = 322
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

union YYSTYPE
{
#line 13 "c99.y" /* yacc.c:1910  */

  int iv;
  char *sv;
  double fv;

#line 128 "c99yy.h" /* yacc.c:1910  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_C99YY_H_INCLUDED  */
