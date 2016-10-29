#include <stdio.h>

int main(){
    int originalSecond,second,minute,hour;
    printf("Input seconds:");
    scanf("%d",&originalSecond);
    
    minute=originalSecond/60;
    second=originalSecond%60;
    hour=minute/60;
    minute=minute%60;

    printf("\n%d seconds equal to %d hour %d minute %d second.",originalSecond,hour,minute,second);

    getchar();
    return 0;
}