#include <stdio.h>

int main(){
    int result=1;
    int i=9;
    while (i>1){
        result*=i;
        i--;
    }
    printf("9!=%d\n",result);
    getchar();
    return 0;
}

