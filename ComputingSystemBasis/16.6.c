#include <stdio.h>
#define MAXLENGTH 20

void StrCpy(char *, char *);
void StrCat(char *, char *);

int main(){
    char str1[7]="123";
    char str2[]="456";

    StrCat(str1,str2);
    system("pause");
}

void StrCpy(char *firstStr, char *secondStr){
    int i;
    for(i=0;i<MAXLENGTH;i++){
        if (secondStr[i]=='\0'){
            return;
        }
        firstStr[i]=secondStr[i];
    }
}

void StrCat(char *firstStr, char *secondStr){
    int i=0;
    while (firstStr[i]!='\0'){
        i++;
    }
    StrCpy(firstStr+i,secondStr);
}