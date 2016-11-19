#include <stdio.h>

int main(){
    int a,b;
    printf("Input a please:");
    scanf("%d",&a);
    printf("\nInput b please:");
    scanf("%d",&b);
    printf("\nThe bigger question is %c which is %d\n.",a<b?'b':'a',a<b?b:a );

    getchar();
    return 0;
}
