#include<stdio.h>

int main(){
    int i;
    int sum=0;
    for(i=1;i<=50;i+=2){
        if (i%7==0){
            sum+=i;
        }
    }
    printf("%d",sum);


    getchar();
    return 0;
}
