#include <stdio.h>

int main(){
    int input,i;
    scanf("%d",&input);

    for (i=31;i>=0;i--){
        printf("%d",1 & (input>>i));
    }

    getchar();
    return 0;
}