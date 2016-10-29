#include <stdio.h>

int main(){
    int i;
    printf("Input an integer:");
    scanf("%d",&i);
    printf("\n");
    if (!(i%7))
        printf("%d can divide 7.",i);
    else
        printf("%d can not divide 7.",i);
    getchar();
    return 0;
}