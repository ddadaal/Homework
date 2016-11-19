#include <stdio.h>

int main(){
    printf("Input the string:");
    char nextChar;
    do{
        scanf("%c",&nextChar);
        if (nextChar!=32)
            printf("%c",nextChar);
    } while (nextChar != '\n');


    getchar();
    return 0;
}
