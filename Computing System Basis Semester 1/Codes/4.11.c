#include <stdio.h>

int main(){
    int n=1;
    int i,j;
    printf("n=");
    scanf("%d",&n);
    printf("\n\n");

    for(i=1;i<=n;i++){
        for(j=0;j<i;j++){
            printf("%d ",i);
        }
        printf("\n");
    }
    system("pause");
    return 0;
}