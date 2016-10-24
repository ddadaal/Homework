#include <stdio.h>

int main(){
    char nextChar;
    do{
        scanf("%c",&nextChar);
        if (nextChar!=32)
            printf("%c",nextChar);
    } while (nextChar != '\n');


    system("pause");
    return 0;
}