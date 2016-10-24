#include <stdio.h>
#include <math.h>

int main(){
    int n,i;
    double pi=0.0;
    printf("n=");
    scanf("%d",&n);
    for(i=1;i<=n;i++){
        if (i%2==1)
            pi+=4.0/(2*i-1);
        else
            pi-=4.0/(2*i-1);
    }
    printf("\n pi=%f \n",pi);
    system("pause");
    return 0;
}