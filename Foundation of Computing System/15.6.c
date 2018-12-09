#include <stdio.h>
char firstLetter(int);

int main(){

    

    int n=27;
    printf("Input n:");
    scanf("%d",&n);



    int ten=n/10;
    int one=n%10;

    if ((ten<2)||(ten>9)||(one<2)||(one>9)){
        printf("Invalid n=%d",n);
        system("pause");
        return 0;
    }

    int x=0,y=0;
    for(x=firstLetter(ten);x<firstLetter(ten+1);x++){
        for(y=firstLetter(one);y<firstLetter(one+1);y++){
            printf("%c%c  ",x,y);
        }
    }
    system("pause");
    return 0;

}


char firstLetter(int n){
    switch (n){
        case 2:
            return 'a';
        case 3:
            return 'd';
        case 4:
            return 'g';
        case 5:
            return 'j';
        case 6:
            return 'm';
        case 7:
            return 'p';
        case 8:
            return 't';
        case 9:
            return 'w';
        case 10:
            return 'w'+4;
        default:
            return 0;
    }
}
