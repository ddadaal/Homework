#include <stdio.h>
#define MAX 50

int main()
{
    double pi,i;
    int n;
    printf("Input an integer:");
    scanf("%d",&n);
    pi =0;
    for(i=1;i<=n;i++){
        pi = pi+4.0/(2i-1);
        i++;
        if (i<=n){
            pi = pi-4.0/(2i-1);
        }
    }
    printf("%f",pi);
}