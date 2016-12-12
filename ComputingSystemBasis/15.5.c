#include <stdio.h>
#include <math.h>

int isPrime(int);

int main(){
    int n=2;
    for(n=2;n<=100;n++){
        if (isPrime(n))
            printf("%d ",n);
    }
    system("pause");
    return 0;
}

int isPrime(int n){
    if (n<2) return 0;
    if (n==2) return 1;
    int i=2;
    for (i=2;i<=sqrt(n);i++){
        if (n%i==0) 
            return 0;
    }
    return 1;

}