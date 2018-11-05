win_bison -d -oc99yy.c c99.y
win_flex -oc99ll.c c99.l
gcc c99*.c -o c99