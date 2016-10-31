#include <stdio.h>

int main(){
    int input,i;
    printf("Input the number:");
    scanf("%d",&input);
    printf("\n");

    for (i=31;i>=0;i--){
        printf("%d",1 & (input>>i));
    }

    printf("\n");
    getchar();
    return 0;
}
