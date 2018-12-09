#include <stdio.h>
#define MAXLENGTH 20

void encrypt(char[], int);

int main(){
    char string[20];
    int n=0;
    printf("Input a string whose length is less than 20:");
    scanf("%s",string);
    printf("Input n:");
    scanf("%d",&n);
    encrypt(string,n);
    printf("%s\n",string);
    system("pause");

}

void encrypt(char string[], int n){
    int i=0;
    for(i=0;i<MAXLENGTH;i++){
        if (string[i]=='\0')
            return;
        if (string[i]>(126-n)){
            string[i]-=94-n;
        }
        else{
            string[i]+=n;
        }
    }
}