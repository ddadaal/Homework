#include <stdio.h>

int main(){
    int a,b;
    printf("Input first integer:");
    scanf("%d",&a);
    printf("\n");
    printf("Input another integer:");
    scanf("%d",&b);
    printf("\n\n");
    if (a>b)
        printf("The bigger one is a which is %d\n",a);
    else if(a==b)
        printf("a=b=%d",a);
    else if(a<b)
        printf("The bigger one is b which is %d\n",b);
    getchar();
    return 0;
}
