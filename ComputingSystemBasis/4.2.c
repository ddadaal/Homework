#include <stdio.h>

int main(){
    int i;
    printf("Input an integer:");
    scanf("%d",&i);
    printf("\n");
    if (!(i%7))
        printf("True\n");
    else
        printf("False\n");
    getchar();
    return 0;
}
