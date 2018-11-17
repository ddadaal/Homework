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

#ifndef YY_YY_SUBCYY_H_INCLUDED
# define YY_YY_SUBCYY_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 1
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
    VOID = 263,
    ELLIPSIS = 264,
    INT = 265,
    RETURN = 266,
    IF = 267,
    ELSE = 268,
    WHILE = 269,
    OR_OR = 270,
    EQUAL = 271,
    MINUS = 272,
    PLUS = 273,
    STAR = 274,
    LT = 275,
    LG = 276,
    LEFT_PARENTHESIS = 277,
    RIGHT_PARENTHESIS = 278,
    LEFT_BRACE = 279,
    RIGHT_BRACE = 280,
    INCRE = 281,
    SEMICOLON = 282,
    ASSIGN = 283,
    COMMA = 284,
    DIV = 285,
    INC = 286,
    LE = 287,
    NOT_EQUAL = 288
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

union YYSTYPE
{
#line 22 "subc.y" /* yacc.c:1910  */

  int iv;
  char *sv;
  double fv;

#line 94 "subcyy.h" /* yacc.c:1910  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_SUBCYY_H_INCLUDED  */
