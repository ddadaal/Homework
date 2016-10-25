#include <stdio.h>

int main(){
    int input,i;
    scanf("%d",&input);

    for (i=32;i>=0;i--){
        printf("%d",1 & (input>>i));
    }

    system("pause");
    return 0;
}