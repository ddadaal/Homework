#include <stdio.h>

double three(double,double,int);

int main(){
    double n=5, x=256;
    printf("%lf",three(x,1.0,n));
    system("pause");
    return 0;
}

int one(double x, int y){
    int i=0, result=1;
    for(i=0;i<y;i++){
        result*=x;
    }
    return result;
}

double three(double x,double y,int n){
    if (n==0){
        return y;
    }
    else{
        return three(x,(y+x/y)/2,n-1);
    }
}
