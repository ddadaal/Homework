win_bison -d -o c99yy.c c99.y
win_flex -o c99ll.c c99.l
gcc c99*.c symtable.c -o c99
