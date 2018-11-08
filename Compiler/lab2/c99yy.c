/* A Bison parser, made by GNU Bison 3.0.5.  */

/* Bison implementation for Yacc-like parsers in C

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

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "3.0.5"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* Copy the first part of user declarations.  */
#line 5 "c99.y" /* yacc.c:339  */

  #include <stdio.h>
  #include "symtable.h"

  // #define ENABLE_TRACE


  int yylex(); 
  int yyerror(const char *); 
  void handleTypedef(char*);

#line 78 "c99yy.c" /* yacc.c:339  */

# ifndef YY_NULLPTR
#  if defined __cplusplus && 201103L <= __cplusplus
#   define YY_NULLPTR nullptr
#  else
#   define YY_NULLPTR 0
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 1
#endif

/* In a future release of Bison, this section will be replaced
   by #include "c99yy.h".  */
#ifndef YY_YY_C99YY_H_INCLUDED
# define YY_YY_C99YY_H_INCLUDED
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
    LEFT_PARENTHESIS = 300,
    RIGHT_PARENTHESIS = 301,
    LEFT_BRACKET = 302,
    RIGHT_BRACKET = 303,
    LEFT_BRACE = 304,
    RIGHT_BRACE = 305,
    SEMICOLON = 306,
    COMMA = 307,
    DOT = 308,
    COLON = 309,
    ELLIPSIS = 310,
    STAR = 311,
    AND = 312,
    PLUS = 313,
    MINUS = 314,
    QUESTION_MARK = 315,
    PTR_ACCESS = 316,
    ASSIGN = 317,
    PLUS_ASSIGN = 318,
    MINUS_ASSIGN = 319,
    MUL_ASSIGN = 320,
    DIV_ASSIGN = 321,
    MOD_ASSIGN = 322,
    LEFT_SHIFT_ASSIGN = 323,
    RIGHT_SHIFT_ASSIGN = 324,
    AND_ASSIGN = 325,
    XOR_ASSIGN = 326,
    OR_ASSIGN = 327,
    AND_AND_ASSIGN = 328,
    OR_OR_ASSIGN = 329,
    INCRE = 330,
    DECRE = 331,
    TYPE_NAME = 332,
    DIV = 333,
    MOD = 334,
    LT = 335,
    GT = 336,
    LE = 337,
    GE = 338,
    EQUAL = 339,
    NOT_EQUAL = 340,
    AND_AND = 341,
    OR_OR = 342,
    EXCLAMATION = 343,
    LEFT_SHIFT = 344,
    RIGHT_SHIFT = 345,
    NEGATE = 346,
    OR = 347,
    XOR = 348
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

union YYSTYPE
{
#line 30 "c99.y" /* yacc.c:355  */

  int iv;
  char *sv;
  double fv;

#line 218 "c99yy.c" /* yacc.c:355  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_C99YY_H_INCLUDED  */

/* Copy the second part of user declarations.  */

#line 235 "c99yy.c" /* yacc.c:358  */

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

#ifndef YY_ATTRIBUTE
# if (defined __GNUC__                                               \
      && (2 < __GNUC__ || (__GNUC__ == 2 && 96 <= __GNUC_MINOR__)))  \
     || defined __SUNPRO_C && 0x5110 <= __SUNPRO_C
#  define YY_ATTRIBUTE(Spec) __attribute__(Spec)
# else
#  define YY_ATTRIBUTE(Spec) /* empty */
# endif
#endif

#ifndef YY_ATTRIBUTE_PURE
# define YY_ATTRIBUTE_PURE   YY_ATTRIBUTE ((__pure__))
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# define YY_ATTRIBUTE_UNUSED YY_ATTRIBUTE ((__unused__))
#endif

#if !defined _Noreturn \
     && (!defined __STDC_VERSION__ || __STDC_VERSION__ < 201112)
# if defined _MSC_VER && 1200 <= _MSC_VER
#  define _Noreturn __declspec (noreturn)
# else
#  define _Noreturn YY_ATTRIBUTE ((__noreturn__))
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

#if defined __GNUC__ && 407 <= __GNUC__ * 100 + __GNUC_MINOR__
/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN \
    _Pragma ("GCC diagnostic push") \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")\
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# define YY_IGNORE_MAYBE_UNINITIALIZED_END \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif


#if 1

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
# define YYCOPY_NEEDED 1
#endif


#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYSIZE_T yynewbytes;                                            \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / sizeof (*yyptr);                          \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, (Count) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYSIZE_T yyi;                         \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  31
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   723

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  94
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  54
/* YYNRULES -- Number of rules.  */
#define YYNRULES  154
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  254

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   348

#define YYTRANSLATE(YYX)                                                \
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, without out-of-bounds checking.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    81,    82,    83,    84,
      85,    86,    87,    88,    89,    90,    91,    92,    93
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,    39,    39,    40,    44,    45,    49,    50,    51,    55,
      59,    60,    64,    65,    66,    67,    68,    69,    70,    71,
      72,    73,    74,    78,    79,    83,    87,    88,    92,    96,
      97,   102,   103,   104,   108,   109,   113,   114,   118,   123,
     124,   128,   132,   133,   137,   138,   142,   143,   147,   148,
     149,   150,   151,   152,   153,   154,   155,   156,   157,   158,
     159,   163,   164,   168,   169,   170,   174,   175,   176,   180,
     181,   182,   183,   184,   188,   189,   190,   194,   195,   196,
     200,   201,   202,   203,   207,   208,   212,   213,   217,   218,
     219,   220,   221,   222,   223,   224,   228,   229,   230,   231,
     232,   233,   237,   238,   239,   240,   241,   242,   243,   244,
     248,   249,   253,   254,   255,   259,   260,   261,   262,   266,
     270,   271,   272,   276,   280,   281,   285,   286,   290,   291,
     295,   296,   300,   301,   305,   306,   307,   308,   309,   313,
     314,   318,   322,   326,   327,   331,   332,   336,   337,   341,
     342,   343,   344,   348,   349
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 1
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "IDENTIFIER", "INT_CONST", "CHAR_CONST",
  "FLOAT_CONST", "STR_CONST", "AUTO", "BREAK", "CASE", "CHAR", "CONST",
  "CONTINUE", "DEFAULT", "DO", "DOUBLE", "ELSE", "ENUM", "EXTERN", "FLOAT",
  "FOR", "GOTO", "IF", "INT", "LONG", "REGISTER", "RETURN", "SHORT",
  "SIGNED", "SIZEOF", "STATIC", "STRUCT", "SWITCH", "TYPEDEF", "UNION",
  "UNSIGNED", "VOID", "VOLATILE", "WHILE", "INLINE", "_BOOL", "_COMPLEX",
  "_IMAGINARY", "RESTRICT", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS",
  "LEFT_BRACKET", "RIGHT_BRACKET", "LEFT_BRACE", "RIGHT_BRACE",
  "SEMICOLON", "COMMA", "DOT", "COLON", "ELLIPSIS", "STAR", "AND", "PLUS",
  "MINUS", "QUESTION_MARK", "PTR_ACCESS", "ASSIGN", "PLUS_ASSIGN",
  "MINUS_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN",
  "LEFT_SHIFT_ASSIGN", "RIGHT_SHIFT_ASSIGN", "AND_ASSIGN", "XOR_ASSIGN",
  "OR_ASSIGN", "AND_AND_ASSIGN", "OR_OR_ASSIGN", "INCRE", "DECRE",
  "TYPE_NAME", "DIV", "MOD", "LT", "GT", "LE", "GE", "EQUAL", "NOT_EQUAL",
  "AND_AND", "OR_OR", "EXCLAMATION", "LEFT_SHIFT", "RIGHT_SHIFT", "NEGATE",
  "OR", "XOR", "$accept", "start", "root_declaration", "declaration",
  "type_name_declaration", "variable_declaration", "statement",
  "continue_and_break_statement", "goto_statement", "return_statement",
  "while_statement", "for_statement", "labeled_statement",
  "braced_statement", "braced_statement_items", "braced_statement_item",
  "if_statement", "switch_statement", "expression_statement", "expression",
  "assignment_expression", "assignment_operator", "conditional_expression",
  "logical_expression", "equality_expression", "comparison_expression",
  "shift_expression", "add_minus_expression", "mul_div_expression",
  "cast_expression", "cast_type", "unary_expression", "unary_operator",
  "access_expression", "func_call_parameters", "atomic_expression",
  "constant", "typedef_declaration", "typedef_target",
  "struct_or_union_declaration", "struct_or_union",
  "struct_or_union_members", "struct_or_union_member",
  "struct_or_union_type_definition_head", "type", "basic_type",
  "variable_or_member", "func_definition", "func_prototype",
  "function_specifier", "func_decorator", "func_decorators",
  "argument_list", "canonical_argument_list", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305,   306,   307,   308,   309,   310,   311,   312,   313,   314,
     315,   316,   317,   318,   319,   320,   321,   322,   323,   324,
     325,   326,   327,   328,   329,   330,   331,   332,   333,   334,
     335,   336,   337,   338,   339,   340,   341,   342,   343,   344,
     345,   346,   347,   348
};
# endif

#define YYPACT_NINF -148

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-148)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
      53,  -148,  -148,  -148,  -148,  -148,  -148,   140,  -148,  -148,
    -148,  -148,     6,  -148,  -148,   -27,  -148,  -148,  -148,     8,
     -30,     4,  -148,  -148,   -17,   -29,  -148,    41,    12,  -148,
    -148,  -148,  -148,  -148,   627,  -148,  -148,  -148,     4,  -148,
     186,  -148,   104,  -148,  -148,    23,  -148,  -148,  -148,  -148,
    -148,  -148,   462,   506,  -148,  -148,  -148,  -148,   632,   632,
    -148,  -148,    -4,  -148,  -148,    92,   127,   158,   139,   174,
      54,  -148,   260,   632,   110,  -148,  -148,    42,  -148,    22,
      38,   298,    58,    60,    72,   132,   100,   528,   108,   113,
    -148,  -148,  -148,   -27,  -148,  -148,  -148,  -148,  -148,  -148,
    -148,  -148,   262,  -148,  -148,  -148,  -148,   208,  -148,  -148,
      91,    97,  -148,   506,  -148,  -148,   -34,    -7,  -148,   627,
    -148,  -148,  -148,   627,   627,   627,   627,   627,   627,   627,
     627,   627,   627,   627,   627,   627,   627,   627,   627,   627,
    -148,  -148,  -148,  -148,  -148,  -148,  -148,  -148,  -148,  -148,
    -148,  -148,  -148,   627,  -148,   565,   627,   164,   166,  -148,
    -148,     4,  -148,  -148,     4,   338,  -148,   111,  -148,   338,
     400,   122,   627,  -148,   228,   627,   627,  -148,  -148,  -148,
    -148,   150,   184,   130,  -148,   627,  -148,  -148,   -21,   127,
    -148,   127,   158,   158,   139,   139,   139,   139,   174,   174,
      54,    54,  -148,  -148,  -148,  -148,  -148,  -148,    70,     7,
    -148,  -148,   129,   131,  -148,   338,  -148,   622,   622,  -148,
      75,  -148,    79,    88,  -148,  -148,  -148,  -148,  -148,   627,
    -148,   627,  -148,  -148,  -148,  -148,   627,   627,   338,   -17,
     338,  -148,  -148,    98,   116,   167,  -148,  -148,   338,   338,
     338,  -148,  -148,  -148
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,   137,   136,   135,   134,   146,   124,     0,   125,   138,
     145,   133,     0,     3,     5,   143,     8,     6,     7,   131,
       0,     0,   132,     4,     0,     0,   148,     0,     0,   121,
     120,     1,     2,    10,     0,   130,   127,   139,     0,     9,
       0,   141,   152,   144,   147,     0,   122,   112,   115,   118,
     116,   117,     0,     0,    99,    98,    96,    97,     0,     0,
     101,   100,     0,    44,    47,    61,    63,    66,    69,    74,
      77,    80,    84,     0,    88,   102,   113,     0,   140,   112,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      35,    43,    22,     0,    38,    21,    19,    20,    17,    18,
      16,    12,     0,    37,    14,    15,    13,     0,   151,   153,
       0,   150,   119,     0,    92,    94,     0,     0,    86,     0,
      90,    91,    11,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      48,    49,    50,    51,    52,    53,    54,    55,    56,    58,
      57,    59,    60,     0,    89,     0,     0,     0,     0,   108,
     109,     0,   123,   126,     0,     0,    24,     0,    23,     0,
       0,     0,     0,    26,     0,     0,     0,    34,    36,    42,
     142,     0,    84,     0,   114,     0,    87,    45,     0,    65,
      84,    64,    67,    68,    70,    71,    72,    73,    75,    76,
      78,    79,    81,    82,    83,    46,   104,   110,     0,     0,
     106,   107,     0,     0,    31,     0,    33,     0,     0,    25,
       0,    27,     0,     0,   149,   154,    93,    95,    85,     0,
     105,     0,   103,   129,   128,    32,     0,     0,     0,     0,
       0,    62,   111,     0,     0,    39,    41,    28,     0,     0,
       0,    30,    29,    40
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -148,  -148,   176,    13,     2,    24,  -102,  -148,  -148,  -148,
    -148,  -148,  -148,   -24,  -148,    99,  -148,  -148,  -147,   -33,
    -119,  -148,   -15,  -148,   162,   163,   185,   175,   200,  -129,
    -148,   -31,  -148,  -148,  -148,  -148,   141,  -148,  -148,   217,
    -148,  -148,  -148,  -148,    -2,  -148,   -35,  -148,  -148,  -148,
     199,  -148,  -148,  -148
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,    12,    13,    92,    93,    16,    94,    95,    96,    97,
      98,    99,   100,   101,   102,   103,   104,   105,   106,   107,
      63,   153,    64,    65,    66,    67,    68,    69,    70,    71,
     117,    72,    73,    74,   208,    75,    76,    17,    28,    18,
      19,    77,   163,    20,    21,    22,    39,    23,    24,    25,
      26,    27,   110,   111
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint8 yytable[] =
{
      41,    62,    15,    78,   187,    30,    31,    37,   202,   203,
     204,    35,   184,    14,    15,    45,    42,     1,   123,    36,
     116,   114,     2,   218,    33,    14,     3,   120,   121,    43,
       4,   123,    40,   229,   205,    34,   207,     5,     6,   185,
       7,     8,   154,     9,   109,   161,    10,   122,   123,   186,
     115,   118,     1,     1,   174,   232,   228,     2,     2,   123,
      38,     3,     3,   214,     1,     4,     4,   216,    46,     2,
     236,   237,     5,     3,   112,   164,   165,     4,     9,     9,
     116,    10,   182,    11,     5,     6,   116,     7,     8,   166,
       9,   188,   162,    10,   190,   190,   190,   190,   190,   190,
     190,   190,   190,   190,   190,   190,   190,   190,   190,   168,
     137,   183,   242,   235,   169,     1,   230,   170,    11,    11,
       2,   238,   231,   209,     3,   239,   212,   123,     4,   213,
      11,   123,   138,   139,   240,   171,   245,   180,   247,   220,
     123,     9,   222,   223,   248,   172,   251,   252,   253,   181,
     123,     1,   124,   175,   190,   155,     2,   156,   176,   108,
       3,     1,   249,   157,     4,   215,     2,   210,   123,   211,
       3,   158,     6,   219,     4,     8,   227,     9,   125,   126,
     233,    11,   234,   225,   250,   159,   160,     9,    32,    79,
      48,    49,    50,    51,   217,    80,    81,     1,   190,    82,
      83,   178,     2,   243,   244,   224,     3,    84,    85,    86,
       4,   127,   128,    87,   241,   246,    52,    11,     6,    88,
       7,     8,   167,     9,    29,    89,    44,    11,   133,   134,
     226,    53,   135,   136,     0,    40,    90,    91,   129,   130,
     131,   132,    54,    55,    56,    57,   140,   141,   142,   143,
     144,   145,   146,   147,   148,   149,   150,   151,   152,   179,
     123,    58,    59,    11,     0,    79,    48,    49,    50,    51,
       0,    80,    81,     1,    60,    82,    83,    61,     2,   221,
     123,     0,     3,    84,    85,    86,     4,   189,   191,    87,
     192,   193,    52,     0,     6,    88,     7,     8,     0,     9,
       0,    89,    48,    49,    50,    51,     0,    53,   198,   199,
       0,    40,   177,    91,   194,   195,   196,   197,    54,    55,
      56,    57,   140,   141,   142,   143,   144,   145,   146,   147,
     148,   149,   150,   151,   152,   200,   201,    58,    59,    11,
       0,    79,    48,    49,    50,    51,     0,    80,    81,     1,
      60,    82,    83,    61,     2,     0,     0,     0,     3,    84,
      85,    86,     4,     0,     0,    87,     0,     0,    52,     0,
       6,    88,     7,     8,     0,     9,     0,    89,     0,     0,
       0,     0,     0,    53,     0,     0,     0,    40,     0,    91,
       0,     0,     0,     0,    54,    55,    56,    57,     0,     0,
       0,     0,     0,    47,    48,    49,    50,    51,     0,     0,
       0,     1,     0,    58,    59,    11,     2,     0,     0,     0,
       3,     0,     0,     0,     4,     0,    60,     0,     0,    61,
      52,     0,     0,     0,     0,     0,     0,     9,     0,     0,
       0,     0,     0,     0,     0,    53,     0,     0,     0,     0,
       0,    91,     0,     0,     0,     0,    54,    55,    56,    57,
       0,     0,     0,     0,     0,    47,    48,    49,    50,    51,
       0,     0,     0,     1,     0,    58,    59,    11,     2,     0,
       0,     0,     3,     0,     0,     0,     4,     0,    60,     0,
       0,    61,    52,     0,     0,     0,     0,     0,     0,     9,
       0,     0,     0,     0,     0,     0,     0,   113,     0,    47,
      48,    49,    50,    51,     0,     0,     0,     1,    54,    55,
      56,    57,     2,     0,     0,     0,     3,     0,     0,     0,
       4,    47,    48,    49,    50,    51,    52,    58,    59,    11,
       0,     0,     0,     9,     0,     0,     0,     0,     0,     0,
      60,    53,     0,    61,     0,     0,     0,     0,    52,     0,
       0,     0,    54,    55,    56,    57,     0,     0,    47,    48,
      49,    50,    51,    53,     0,     0,     0,     0,     0,   173,
       0,    58,    59,    11,    54,    55,    56,    57,     0,     0,
       0,     0,     0,     0,    60,    52,     0,    61,     0,     0,
       0,     0,     0,    58,    59,     0,     0,     0,     0,     0,
      53,   206,     0,     0,     0,     0,    60,     0,     0,    61,
       0,    54,    55,    56,    57,    47,    48,    49,    50,    51,
      47,    48,    49,    50,    51,    47,    48,    49,    50,    51,
      58,    59,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    52,    60,     0,     0,    61,    52,     0,     0,
       0,     0,    52,     0,     0,     0,     0,    53,     0,     0,
       0,     0,    53,    91,     0,     0,     0,   119,    54,    55,
      56,    57,     0,    54,    55,    56,    57,     0,    54,    55,
      56,    57,     0,     0,     0,     0,     0,    58,    59,     0,
       0,     0,    58,    59,     0,     0,     0,    58,    59,     0,
      60,     0,     0,    61,     0,    60,     0,     0,    61,     0,
      60,     0,     0,    61
};

static const yytype_int16 yycheck[] =
{
      24,    34,     0,    38,   123,     7,     0,     3,   137,   138,
     139,     3,    46,     0,    12,     3,    45,    11,    52,    49,
      53,    52,    16,   170,    51,    12,    20,    58,    59,    27,
      24,    52,    49,    54,   153,    62,   155,    31,    32,    46,
      34,    35,    73,    37,    42,     3,    40,    51,    52,    56,
      52,    53,    11,    11,    87,    48,   185,    16,    16,    52,
      56,    20,    20,   165,    11,    24,    24,   169,    56,    16,
     217,   218,    31,    20,    51,    77,    54,    24,    37,    37,
     113,    40,   113,    77,    31,    32,   119,    34,    35,    51,
      37,   124,    50,    40,   125,   126,   127,   128,   129,   130,
     131,   132,   133,   134,   135,   136,   137,   138,   139,    51,
      56,   113,   231,   215,    54,    11,    46,    45,    77,    77,
      16,    46,    52,   156,    20,    46,   161,    52,    24,   164,
      77,    52,    78,    79,    46,     3,   238,    46,   240,   172,
      52,    37,   175,   176,    46,    45,   248,   249,   250,    52,
      52,    11,    60,    45,   185,    45,    16,    47,    45,    55,
      20,    11,    46,    53,    24,    54,    16,     3,    52,     3,
      20,    61,    32,    51,    24,    35,    46,    37,    86,    87,
      51,    77,    51,   181,    17,    75,    76,    37,    12,     3,
       4,     5,     6,     7,   170,     9,    10,    11,   229,    13,
      14,   102,    16,   236,   237,    55,    20,    21,    22,    23,
      24,    84,    85,    27,   229,   239,    30,    77,    32,    33,
      34,    35,    81,    37,     7,    39,    27,    77,    89,    90,
      46,    45,    58,    59,    -1,    49,    50,    51,    80,    81,
      82,    83,    56,    57,    58,    59,    62,    63,    64,    65,
      66,    67,    68,    69,    70,    71,    72,    73,    74,    51,
      52,    75,    76,    77,    -1,     3,     4,     5,     6,     7,
      -1,     9,    10,    11,    88,    13,    14,    91,    16,    51,
      52,    -1,    20,    21,    22,    23,    24,   125,   126,    27,
     127,   128,    30,    -1,    32,    33,    34,    35,    -1,    37,
      -1,    39,     4,     5,     6,     7,    -1,    45,   133,   134,
      -1,    49,    50,    51,   129,   130,   131,   132,    56,    57,
      58,    59,    62,    63,    64,    65,    66,    67,    68,    69,
      70,    71,    72,    73,    74,   135,   136,    75,    76,    77,
      -1,     3,     4,     5,     6,     7,    -1,     9,    10,    11,
      88,    13,    14,    91,    16,    -1,    -1,    -1,    20,    21,
      22,    23,    24,    -1,    -1,    27,    -1,    -1,    30,    -1,
      32,    33,    34,    35,    -1,    37,    -1,    39,    -1,    -1,
      -1,    -1,    -1,    45,    -1,    -1,    -1,    49,    -1,    51,
      -1,    -1,    -1,    -1,    56,    57,    58,    59,    -1,    -1,
      -1,    -1,    -1,     3,     4,     5,     6,     7,    -1,    -1,
      -1,    11,    -1,    75,    76,    77,    16,    -1,    -1,    -1,
      20,    -1,    -1,    -1,    24,    -1,    88,    -1,    -1,    91,
      30,    -1,    -1,    -1,    -1,    -1,    -1,    37,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    45,    -1,    -1,    -1,    -1,
      -1,    51,    -1,    -1,    -1,    -1,    56,    57,    58,    59,
      -1,    -1,    -1,    -1,    -1,     3,     4,     5,     6,     7,
      -1,    -1,    -1,    11,    -1,    75,    76,    77,    16,    -1,
      -1,    -1,    20,    -1,    -1,    -1,    24,    -1,    88,    -1,
      -1,    91,    30,    -1,    -1,    -1,    -1,    -1,    -1,    37,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    45,    -1,     3,
       4,     5,     6,     7,    -1,    -1,    -1,    11,    56,    57,
      58,    59,    16,    -1,    -1,    -1,    20,    -1,    -1,    -1,
      24,     3,     4,     5,     6,     7,    30,    75,    76,    77,
      -1,    -1,    -1,    37,    -1,    -1,    -1,    -1,    -1,    -1,
      88,    45,    -1,    91,    -1,    -1,    -1,    -1,    30,    -1,
      -1,    -1,    56,    57,    58,    59,    -1,    -1,     3,     4,
       5,     6,     7,    45,    -1,    -1,    -1,    -1,    -1,    51,
      -1,    75,    76,    77,    56,    57,    58,    59,    -1,    -1,
      -1,    -1,    -1,    -1,    88,    30,    -1,    91,    -1,    -1,
      -1,    -1,    -1,    75,    76,    -1,    -1,    -1,    -1,    -1,
      45,    46,    -1,    -1,    -1,    -1,    88,    -1,    -1,    91,
      -1,    56,    57,    58,    59,     3,     4,     5,     6,     7,
       3,     4,     5,     6,     7,     3,     4,     5,     6,     7,
      75,    76,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    30,    88,    -1,    -1,    91,    30,    -1,    -1,
      -1,    -1,    30,    -1,    -1,    -1,    -1,    45,    -1,    -1,
      -1,    -1,    45,    51,    -1,    -1,    -1,    45,    56,    57,
      58,    59,    -1,    56,    57,    58,    59,    -1,    56,    57,
      58,    59,    -1,    -1,    -1,    -1,    -1,    75,    76,    -1,
      -1,    -1,    75,    76,    -1,    -1,    -1,    75,    76,    -1,
      88,    -1,    -1,    91,    -1,    88,    -1,    -1,    91,    -1,
      88,    -1,    -1,    91
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,    11,    16,    20,    24,    31,    32,    34,    35,    37,
      40,    77,    95,    96,    97,    98,    99,   131,   133,   134,
     137,   138,   139,   141,   142,   143,   144,   145,   132,   133,
     138,     0,    96,    51,    62,     3,    49,     3,    56,   140,
      49,   107,    45,    98,   144,     3,    56,     3,     4,     5,
       6,     7,    30,    45,    56,    57,    58,    59,    75,    76,
      88,    91,   113,   114,   116,   117,   118,   119,   120,   121,
     122,   123,   125,   126,   127,   129,   130,   135,   140,     3,
       9,    10,    13,    14,    21,    22,    23,    27,    33,    39,
      50,    51,    97,    98,   100,   101,   102,   103,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,    55,    98,
     146,   147,    51,    45,   125,   138,   113,   124,   138,    45,
     125,   125,    51,    52,    60,    86,    87,    84,    85,    80,
      81,    82,    83,    89,    90,    58,    59,    56,    78,    79,
      62,    63,    64,    65,    66,    67,    68,    69,    70,    71,
      72,    73,    74,   115,   125,    45,    47,    53,    61,    75,
      76,     3,    50,   136,   138,    54,    51,   130,    51,    54,
      45,     3,    45,    51,   113,    45,    45,    50,   109,    51,
      46,    52,   125,   138,    46,    46,    56,   114,   113,   118,
     125,   118,   119,   119,   120,   120,   120,   120,   121,   121,
     122,   122,   123,   123,   123,   114,    46,   114,   128,   113,
       3,     3,   140,   140,   100,    54,   100,    99,   112,    51,
     113,    51,   113,   113,    55,    98,    46,    46,   123,    54,
      46,    52,    48,    51,    51,   100,   112,   112,    46,    46,
      46,   116,   114,   113,   113,   100,   107,   100,    46,    46,
      17,   100,   100,   100
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    94,    95,    95,    96,    96,    97,    97,    97,    98,
      99,    99,   100,   100,   100,   100,   100,   100,   100,   100,
     100,   100,   100,   101,   101,   102,   103,   103,   104,   105,
     105,   106,   106,   106,   107,   107,   108,   108,   109,   110,
     110,   111,   112,   112,   113,   113,   114,   114,   115,   115,
     115,   115,   115,   115,   115,   115,   115,   115,   115,   115,
     115,   116,   116,   117,   117,   117,   118,   118,   118,   119,
     119,   119,   119,   119,   120,   120,   120,   121,   121,   121,
     122,   122,   122,   122,   123,   123,   124,   124,   125,   125,
     125,   125,   125,   125,   125,   125,   126,   126,   126,   126,
     126,   126,   127,   127,   127,   127,   127,   127,   127,   127,
     128,   128,   129,   129,   129,   130,   130,   130,   130,   131,
     132,   132,   132,   133,   134,   134,   135,   135,   136,   136,
     137,   137,   138,   138,   139,   139,   139,   139,   139,   140,
     140,   141,   142,   143,   143,   144,   144,   145,   145,   146,
     146,   146,   146,   147,   147
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     2,     1,     1,     1,     1,     1,     1,     2,
       2,     4,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     2,     2,     3,     2,     3,     5,     7,
       7,     3,     4,     3,     3,     2,     2,     1,     1,     5,
       7,     5,     2,     1,     1,     3,     3,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     5,     1,     3,     3,     1,     3,     3,     1,
       3,     3,     3,     3,     1,     3,     3,     1,     3,     3,
       1,     3,     3,     3,     1,     4,     1,     2,     1,     2,
       2,     2,     2,     4,     2,     4,     1,     1,     1,     1,
       1,     1,     1,     4,     3,     4,     3,     3,     2,     2,
       1,     3,     1,     1,     3,     1,     1,     1,     1,     4,
       1,     1,     2,     4,     1,     1,     2,     0,     3,     3,
       2,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       2,     2,     4,     1,     2,     1,     1,     2,     1,     3,
       1,     1,     0,     1,     3
};


#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)
#define YYEMPTY         (-2)
#define YYEOF           0

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                  \
do                                                              \
  if (yychar == YYEMPTY)                                        \
    {                                                           \
      yychar = (Token);                                         \
      yylval = (Value);                                         \
      YYPOPSTACK (yylen);                                       \
      yystate = *yyssp;                                         \
      YY_LAC_DISCARD ("YYBACKUP");                              \
      goto yybackup;                                            \
    }                                                           \
  else                                                          \
    {                                                           \
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;                                                  \
    }                                                           \
while (0)

/* Error token number */
#define YYTERROR        1
#define YYERRCODE       256



/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)

/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


# define YY_SYMBOL_PRINT(Title, Type, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Type, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*----------------------------------------.
| Print this symbol's value on YYOUTPUT.  |
`----------------------------------------*/

static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  FILE *yyo = yyoutput;
  YYUSE (yyo);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# endif
  YYUSE (yytype);
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyoutput, "%s %s (",
             yytype < YYNTOKENS ? "token" : "nterm", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yytype_int16 *yyssp, YYSTYPE *yyvsp, int yyrule)
{
  unsigned long int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       yystos[yyssp[yyi + 1 - yynrhs]],
                       &(yyvsp[(yyi + 1) - (yynrhs)])
                                              );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif

/* Given a state stack such that *YYBOTTOM is its bottom, such that
   *YYTOP is either its top or is YYTOP_EMPTY to indicate an empty
   stack, and such that *YYCAPACITY is the maximum number of elements it
   can hold without a reallocation, make sure there is enough room to
   store YYADD more elements.  If not, allocate a new stack using
   YYSTACK_ALLOC, copy the existing elements, and adjust *YYBOTTOM,
   *YYTOP, and *YYCAPACITY to reflect the new capacity and memory
   location.  If *YYBOTTOM != YYBOTTOM_NO_FREE, then free the old stack
   using YYSTACK_FREE.  Return 0 if successful or if no reallocation is
   required.  Return 1 if memory is exhausted.  */
static int
yy_lac_stack_realloc (YYSIZE_T *yycapacity, YYSIZE_T yyadd,
#if YYDEBUG
                      char const *yydebug_prefix,
                      char const *yydebug_suffix,
#endif
                      yytype_int16 **yybottom,
                      yytype_int16 *yybottom_no_free,
                      yytype_int16 **yytop, yytype_int16 *yytop_empty)
{
  YYSIZE_T yysize_old =
    *yytop == yytop_empty ? 0 : *yytop - *yybottom + 1;
  YYSIZE_T yysize_new = yysize_old + yyadd;
  if (*yycapacity < yysize_new)
    {
      YYSIZE_T yyalloc = 2 * yysize_new;
      yytype_int16 *yybottom_new;
      /* Use YYMAXDEPTH for maximum stack size given that the stack
         should never need to grow larger than the main state stack
         needs to grow without LAC.  */
      if (YYMAXDEPTH < yysize_new)
        {
          YYDPRINTF ((stderr, "%smax size exceeded%s", yydebug_prefix,
                      yydebug_suffix));
          return 1;
        }
      if (YYMAXDEPTH < yyalloc)
        yyalloc = YYMAXDEPTH;
      yybottom_new =
        (yytype_int16*) YYSTACK_ALLOC (yyalloc * sizeof *yybottom_new);
      if (!yybottom_new)
        {
          YYDPRINTF ((stderr, "%srealloc failed%s", yydebug_prefix,
                      yydebug_suffix));
          return 1;
        }
      if (*yytop != yytop_empty)
        {
          YYCOPY (yybottom_new, *yybottom, yysize_old);
          *yytop = yybottom_new + (yysize_old - 1);
        }
      if (*yybottom != yybottom_no_free)
        YYSTACK_FREE (*yybottom);
      *yybottom = yybottom_new;
      *yycapacity = yyalloc;
    }
  return 0;
}

/* Establish the initial context for the current lookahead if no initial
   context is currently established.

   We define a context as a snapshot of the parser stacks.  We define
   the initial context for a lookahead as the context in which the
   parser initially examines that lookahead in order to select a
   syntactic action.  Thus, if the lookahead eventually proves
   syntactically unacceptable (possibly in a later context reached via a
   series of reductions), the initial context can be used to determine
   the exact set of tokens that would be syntactically acceptable in the
   lookahead's place.  Moreover, it is the context after which any
   further semantic actions would be erroneous because they would be
   determined by a syntactically unacceptable token.

   YY_LAC_ESTABLISH should be invoked when a reduction is about to be
   performed in an inconsistent state (which, for the purposes of LAC,
   includes consistent states that don't know they're consistent because
   their default reductions have been disabled).  Iff there is a
   lookahead token, it should also be invoked before reporting a syntax
   error.  This latter case is for the sake of the debugging output.

   For parse.lac=full, the implementation of YY_LAC_ESTABLISH is as
   follows.  If no initial context is currently established for the
   current lookahead, then check if that lookahead can eventually be
   shifted if syntactic actions continue from the current context.
   Report a syntax error if it cannot.  */
#define YY_LAC_ESTABLISH                                         \
do {                                                             \
  if (!yy_lac_established)                                       \
    {                                                            \
      YYDPRINTF ((stderr,                                        \
                  "LAC: initial context established for %s\n",   \
                  yytname[yytoken]));                            \
      yy_lac_established = 1;                                    \
      {                                                          \
        int yy_lac_status =                                      \
          yy_lac (yyesa, &yyes, &yyes_capacity, yyssp, yytoken); \
        if (yy_lac_status == 2)                                  \
          goto yyexhaustedlab;                                   \
        if (yy_lac_status == 1)                                  \
          goto yyerrlab;                                         \
      }                                                          \
    }                                                            \
} while (0)

/* Discard any previous initial lookahead context because of Event,
   which may be a lookahead change or an invalidation of the currently
   established initial context for the current lookahead.

   The most common example of a lookahead change is a shift.  An example
   of both cases is syntax error recovery.  That is, a syntax error
   occurs when the lookahead is syntactically erroneous for the
   currently established initial context, so error recovery manipulates
   the parser stacks to try to find a new initial context in which the
   current lookahead is syntactically acceptable.  If it fails to find
   such a context, it discards the lookahead.  */
#if YYDEBUG
# define YY_LAC_DISCARD(Event)                                           \
do {                                                                     \
  if (yy_lac_established)                                                \
    {                                                                    \
      if (yydebug)                                                       \
        YYFPRINTF (stderr, "LAC: initial context discarded due to "      \
                   Event "\n");                                          \
      yy_lac_established = 0;                                            \
    }                                                                    \
} while (0)
#else
# define YY_LAC_DISCARD(Event) yy_lac_established = 0
#endif

/* Given the stack whose top is *YYSSP, return 0 iff YYTOKEN can
   eventually (after perhaps some reductions) be shifted, return 1 if
   not, or return 2 if memory is exhausted.  As preconditions and
   postconditions: *YYES_CAPACITY is the allocated size of the array to
   which *YYES points, and either *YYES = YYESA or *YYES points to an
   array allocated with YYSTACK_ALLOC.  yy_lac may overwrite the
   contents of either array, alter *YYES and *YYES_CAPACITY, and free
   any old *YYES other than YYESA.  */
static int
yy_lac (yytype_int16 *yyesa, yytype_int16 **yyes,
        YYSIZE_T *yyes_capacity, yytype_int16 *yyssp, int yytoken)
{
  yytype_int16 *yyes_prev = yyssp;
  yytype_int16 *yyesp = yyes_prev;
  YYDPRINTF ((stderr, "LAC: checking lookahead %s:", yytname[yytoken]));
  if (yytoken == YYUNDEFTOK)
    {
      YYDPRINTF ((stderr, " Always Err\n"));
      return 1;
    }
  while (1)
    {
      int yyrule = yypact[*yyesp];
      if (yypact_value_is_default (yyrule)
          || (yyrule += yytoken) < 0 || YYLAST < yyrule
          || yycheck[yyrule] != yytoken)
        {
          yyrule = yydefact[*yyesp];
          if (yyrule == 0)
            {
              YYDPRINTF ((stderr, " Err\n"));
              return 1;
            }
        }
      else
        {
          yyrule = yytable[yyrule];
          if (yytable_value_is_error (yyrule))
            {
              YYDPRINTF ((stderr, " Err\n"));
              return 1;
            }
          if (0 < yyrule)
            {
              YYDPRINTF ((stderr, " S%d\n", yyrule));
              return 0;
            }
          yyrule = -yyrule;
        }
      {
        YYSIZE_T yylen = yyr2[yyrule];
        YYDPRINTF ((stderr, " R%d", yyrule - 1));
        if (yyesp != yyes_prev)
          {
            YYSIZE_T yysize = yyesp - *yyes + 1;
            if (yylen < yysize)
              {
                yyesp -= yylen;
                yylen = 0;
              }
            else
              {
                yylen -= yysize;
                yyesp = yyes_prev;
              }
          }
        if (yylen)
          yyesp = yyes_prev -= yylen;
      }
      {
        int yystate;
        {
          int yylhs = yyr1[yyrule] - YYNTOKENS;
          yystate = yypgoto[yylhs] + *yyesp;
          if (yystate < 0 || YYLAST < yystate
              || yycheck[yystate] != *yyesp)
            yystate = yydefgoto[yylhs];
          else
            yystate = yytable[yystate];
        }
        if (yyesp == yyes_prev)
          {
            yyesp = *yyes;
            *yyesp = yystate;
          }
        else
          {
            if (yy_lac_stack_realloc (yyes_capacity, 1,
#if YYDEBUG
                                      " (", ")",
#endif
                                      yyes, yyesa, &yyesp, yyes_prev))
              {
                YYDPRINTF ((stderr, "\n"));
                return 2;
              }
            *++yyesp = yystate;
          }
        YYDPRINTF ((stderr, " G%d", yystate));
      }
    }
}


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
static YYSIZE_T
yystrlen (const char *yystr)
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            /* Fall through.  */
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.  In order to see if a particular token T is a
   valid looakhead, invoke yy_lac (YYESA, YYES, YYES_CAPACITY, YYSSP, T).

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store or if
   yy_lac returned 2.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyesa, yytype_int16 **yyes,
                YYSIZE_T *yyes_capacity, yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (YY_NULLPTR, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
       In the first two cases, it might appear that the current syntax
       error should have been detected in the previous state when yy_lac
       was invoked.  However, at that time, there might have been a
       different syntax error that discarded a different initial context
       during error recovery, leaving behind the current lookahead.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      YYDPRINTF ((stderr, "Constructing syntax error message\n"));
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          int yyx;

          for (yyx = 0; yyx < YYNTOKENS; ++yyx)
            if (yyx != YYTERROR && yyx != YYUNDEFTOK)
              {
                {
                  int yy_lac_status = yy_lac (yyesa, yyes, yyes_capacity,
                                              yyssp, yyx);
                  if (yy_lac_status == 2)
                    return 2;
                  if (yy_lac_status == 1)
                    continue;
                }
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYSIZE_T yysize1 = yysize + yytnamerr (YY_NULLPTR, yytname[yyx]);
                  if (! (yysize <= yysize1
                         && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                    return 2;
                  yysize = yysize1;
                }
              }
        }
# if YYDEBUG
      else if (yydebug)
        YYFPRINTF (stderr, "No expected tokens.\n");
# endif
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
    default: /* Avoid compiler warnings. */
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    YYSIZE_T yysize1 = yysize + yystrlen (yyformat);
    if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
      return 2;
    yysize = yysize1;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
{
  YYUSE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}




/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       'yyss': related to states.
       'yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

    yytype_int16 yyesa[20];
    yytype_int16 *yyes;
    YYSIZE_T yyes_capacity;

  int yy_lac_established = 0;
  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  yyes = yyesa;
  yyes_capacity = sizeof yyesa / sizeof *yyes;
  if (YYMAXDEPTH < yyes_capacity)
    yyes_capacity = YYMAXDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        YYSTYPE *yyvs1 = yyvs;
        yytype_int16 *yyss1 = yyss;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * sizeof (*yyssp),
                    &yyvs1, yysize * sizeof (*yyvsp),
                    &yystacksize);

        yyss = yyss1;
        yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yytype_int16 *yyss1 = yyss;
        union yyalloc *yyptr =
          (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
        if (! yyptr)
          goto yyexhaustedlab;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
                  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = yylex ();
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    {
      YY_LAC_ESTABLISH;
      goto yydefault;
    }
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      YY_LAC_ESTABLISH;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;
  YY_LAC_DISCARD ("shift");

  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  {
    int yychar_backup = yychar;
    switch (yyn)
      {
          case 11:
#line 60 "c99.y" /* yacc.c:1648  */
    { printf("Initlaized variable declaration.\n");}
#line 1857 "c99yy.c" /* yacc.c:1648  */
    break;

  case 104:
#line 239 "c99.y" /* yacc.c:1648  */
    { printf("function call with no parameter.\n"); }
#line 1863 "c99yy.c" /* yacc.c:1648  */
    break;

  case 105:
#line 240 "c99.y" /* yacc.c:1648  */
    { printf("function call with parameters.\n"); }
#line 1869 "c99yy.c" /* yacc.c:1648  */
    break;

  case 115:
#line 259 "c99.y" /* yacc.c:1648  */
    { printf("Integer constant: %d\n", (yyvsp[0].iv)); }
#line 1875 "c99yy.c" /* yacc.c:1648  */
    break;

  case 116:
#line 260 "c99.y" /* yacc.c:1648  */
    { printf("Float constant: %.2f\n", (yyvsp[0].fv)); }
#line 1881 "c99yy.c" /* yacc.c:1648  */
    break;

  case 117:
#line 261 "c99.y" /* yacc.c:1648  */
    { printf("Str constant: %s\n", (yyvsp[0].sv)); }
#line 1887 "c99yy.c" /* yacc.c:1648  */
    break;

  case 118:
#line 262 "c99.y" /* yacc.c:1648  */
    { printf("Char constant: %c\n", (yyvsp[0].iv)); }
#line 1893 "c99yy.c" /* yacc.c:1648  */
    break;

  case 119:
#line 266 "c99.y" /* yacc.c:1648  */
    { handleTypedef((yyvsp[-1].sv)); }
#line 1899 "c99yy.c" /* yacc.c:1648  */
    break;

  case 123:
#line 276 "c99.y" /* yacc.c:1648  */
    { printf("A struct\n"); }
#line 1905 "c99yy.c" /* yacc.c:1648  */
    break;

  case 128:
#line 290 "c99.y" /* yacc.c:1648  */
    { printf("identifier\n");}
#line 1911 "c99yy.c" /* yacc.c:1648  */
    break;

  case 130:
#line 295 "c99.y" /* yacc.c:1648  */
    { printf("A struct name : %s\n", (yyvsp[0].sv)); }
#line 1917 "c99yy.c" /* yacc.c:1648  */
    break;

  case 141:
#line 318 "c99.y" /* yacc.c:1648  */
    { printf("A function declaration\n"); }
#line 1923 "c99yy.c" /* yacc.c:1648  */
    break;

  case 142:
#line 322 "c99.y" /* yacc.c:1648  */
    { printf("A function prototype \n");}
#line 1929 "c99yy.c" /* yacc.c:1648  */
    break;


#line 1933 "c99yy.c" /* yacc.c:1648  */
        default: break;
      }
    if (yychar_backup != yychar)
      YY_LAC_DISCARD ("yychar change");
  }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyesa, &yyes, &yyes_capacity, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        if (yychar != YYEMPTY)
          YY_LAC_ESTABLISH;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYTERROR;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  /* If the stack popping above didn't lose the initial context for the
     current lookahead token, the shift below will for sure.  */
  YY_LAC_DISCARD ("error recovery");

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if 1
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
  if (yyes != yyesa)
    YYSTACK_FREE (yyes);
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  return yyresult;
}
#line 353 "c99.y" /* yacc.c:1907  */


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
