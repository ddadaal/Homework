#include <stdio.h>

int main(){
    int year, month,day;
    printf("Input year:");
    scanf("%d",&year);
    printf("\nInput month:");
    scanf("%d",&month);

    if (month==2)
        if ((year%4==0&&year%100!=0)||(year%400==0))
            day=29;
        else
            day=28;
    else
        if ((month==1)||(month==3)||(month==5)||(month==7)||(month==8)||(month==10)||(month==12))
            day=31;
        else
            day=30;
    printf("\nThere are %d days in that month. \n",day);

    getchar();
    return 0;
}
