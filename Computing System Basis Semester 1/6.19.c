#include <stdio.h>

// a-z 97-122
// minus 32

int main(){
    char nextChar;
    
    do {
        scanf("%c",&nextChar);
        if ((nextChar>=97)&&(nextChar<=122)){
            printf("%c",nextChar-32);
        }
        else{
            printf("%c",nextChar);
        }

    } while (nextChar != '\n');

    getchar();
    return 0;
}