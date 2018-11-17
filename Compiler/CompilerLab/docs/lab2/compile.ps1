win_bison -d -o subcyy.c subc.y
win_flex -o subcll.c subc.l
gcc subc*.c -o subc
