# myl和myy格式描述

By 161250010 陈俊达

# 1. 简介

本文档描述了词法定义文件myl和语法定义文件myy文件格式。

# 2. TokenType列表

在产生式中出现Token类型列表所包含的字符串，将被认为是对应类型的终结符。字面量仅用于调试和错误报告中。

| Token类型 | 字面量 | 备注 |
| -- | -- | -- |
| VOID | void | |
| INT | int | | 
| RETURN | return | |
| WHILE | while | |
| IF | if | | 
| ELSE | else | |
| ELLIPSIS | ... | |
| EQUAL | == | |
| ASSIGN | = | |
| SEMICOLON | ; | |
| STAR | * | |
| OR_OR | || | |
| LEFT_BRACE | { | |
| RIGHT_BRACE | } | |
| LEFT_PARENTHESIS | ) | |
| RIGHT_PARENTHESIS | ) | |
| COMMA | , | |
| PLUS | + | |
| MINUS | - | |
| DIV | / | |
| INC | ++ | |
| LT | < | |
| LE | <= | |
| IDENTIFIER | id | |
| INT_CONST | int_const | |
| STR_CONST | str_const | |
| IGNORED |  | IGNORED类型的token将不会传送给语法分析器 |
| DOLLAR_R | $R | 当词法分析器结束了所有的读取时，语法分析器无法获得下一个Token，则语法分析器会认为下一个Token是$R | 
| UNKNOWN | # | 若词法分析器分析到UNKNOWN类型的Token，将会抛出LexicalParseException | | 
| EOF | $eof | 词法分析器结束了所有的读取时，将会返回$eof类型的Token |

# 3. 词法定义文件格式myl

一个myl包含数个以下格式的词法定义，每个定义之间可接受任意个数的空行。

```
第一行：正则表达式的字符串
第二行：对应的TokenType的字符串
```

其中，正则表达式支持以下元素：

- 字符字面量
- 闭包（*）
- 并集（|）
- 覆写优先级的括号（(, )）
- escaped字符(\n, \t, \b，\", \ (空格，表示一个空格), \\+, \\*, \\(, \\), \r)

扩展支持：

- **方括号([])**

方括号内部出现的元素之间会通过并集|连接，可与字符类配套使用。

例如：[ac\n]等价于(a|c|\n)

- **字符类（-），只能出现在方括号中**

在ASCII表中，-前面的符号和后面的符号之间的所有符号会通过并集|连接。

例如：[a-d]等价于(a|b|c|d)

例如：[\n\ba-d_]等价于(\n|\b|(a|b|c|d)|_)

示例：

```
\+
PLUS （定义一个正则表达式为\+，对应到PLUS类型的Token的匹配规则，）

\*
STAR（定义一个正则表达式为\*，对应到STAR类型的token的匹配规则）

\(
LEFT_PARENTHESIS

\)
RIGHT_PARENTHESIS

[a-zA-Z]([0-9a-zA-Z_])*
IDENTIFIER （定义一个正则表达式为[a-zA-Z]([0-9a-zA-Z_])*（即以字母开头，后面跟任意个数的数字、字母或下划线），对应到IDENTIFIER类型的token转换规则）

[\ \n\r]
IGNORED （定义空格、空行或\r字符都被匹配到IGNORED类型的token的转换规则）
```


# 4. 语法分析文件格式myy

一个myy文件以一个字符串（代表此产生式列表的开始符，这个开始符可多次出现在产生式的左边）占一行开始，接下来由数个以下格式的语法定义组成，每个语法定义代表**一系列具有相同左侧符号的产生式的集合**，每个定义之间可接受任意个数的空行。

```
第一行：一个字符串，代表本产生式集合左边的符号

第二行开始：每一行代表一个产生式的右侧的符号列表。每个符号之间用任意个数的空格隔开。若一行为空，则代表这是一个epsilon产生式。若一个符号包含在TokenType列表里，则这个符号会被认为是一个终结符

本集合最后一个产生式后一行：符号"%"
```

示例：

```
E （代表这个产生式列表的开始符为E）


E （定义这个产生式集合的左侧符号为E）
E PLUS T （定义一个E -> E PLUS T的产生式，PLUS是一个类型为PLUS的终结符）
T （定义一个E -> T的产生式）
% （以E为左侧的产生式集合定义完毕）


T （定义这个产生式集合的左侧符号为T）
T STAR F （定义一个F -> T STAR F的产生式，STAR是一个类型为STAR的终结符）
F （定义一个T -> F的产生式）
% （以T为左侧的产生式集合定义完毕）

F （定义这个产生式集合的左侧符号为F）
LEFT_PARENTHESIS E RIGHT_PARENTHESIS （定义一个F -> LEFT_PARENTHESIS E RIGHT_PARENTHESIS的产生式，LEFT_PARENTHESIS和RIGHT_PARENTHESIS是终结符）
IDENTIFIER （定义一个F -> IDENTIFIER的产生式）
    （定义一个F -> epsilon的epsilon产生式）
% （以F为左侧的产生式集合定义完毕）
```