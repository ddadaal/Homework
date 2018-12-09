#include <stdio.h>


//recursive way
int fib(int n){
    if ((n==0)||(n==1))
        return 1;
    else
        return fib(n-1)+fib(n-2);
}

int main(){
    int n;
    printf("Input n for fibnacci:");
    scanf("%d",&n);
    printf("\n\n");
    printf("f(%d)=%d\n",n,fib(n));

    //Non-recursive method
    int a=1,b=1;
    int i;
    int flag=1; // 0 means a is to be changed, 1 means b is to be changed
    for (i=2;i<=n;i++){
        if (flag==1){
            b=a+b;
        } else {
            a=a+b;
        }
        flag=!flag;
    }
    flag=!flag; // loop has ended, and flag should be point to the variable which has just been changed, not to be changed.
    printf("f(%d)=%d\n",n, flag==0?a:b);

    getchar();
    return 0;
}
