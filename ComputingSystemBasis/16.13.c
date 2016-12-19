#include <stdio.h>
#define MAXLENGTH 20
#define NUM 5

int Compare(char[], char[]);
int Find(char[][MAXLENGTH], char[]);
void StrCpy(char[],char[]);


int main(){
    char strings[NUM][MAXLENGTH]={"asd","asdd","asdddd","asd","asdddddd"};
    char existing[NUM][MAXLENGTH]={};

    int i, currentIndexOfExisting=0,j=0;
    int numberOfDistinctElements=NUM;
    for(i=0;i<NUM;i++){
        if (!Find(existing,strings[i])){
            StrCpy(existing[currentIndexOfExisting++],strings[i]);
        }else{
            for(j=i;j<NUM-1;j++){
                StrCpy(strings[j],strings[j+1]);
            }
            numberOfDistinctElements--;
        }
    }

    for(i=0;i<numberOfDistinctElements;i++){
        printf("%s\n",strings[i]);
    }

    system("pause");
    return 0;
}

void StrCpy(char firstStr[], char secondStr[]){
    int i;
    for(i=0;i<MAXLENGTH;i++){
        firstStr[i]=secondStr[i];
    }
}

int Find(char array[][MAXLENGTH],char str[]){
    int i=0;
    for(i=0;i<NUM;i++){
        if (Compare(array[i],str)==0){
            return 1;
         }
    }
    return 0;
}

int Compare(char str1[], char str2[]){
    int i=0;
    for(i=0;i<MAXLENGTH;i++){
        if (str1[i]<str2[i]){
            return -1;
        }
        if (str1[i]>str2[i]){
            return 1;
        }
    }
    return 0;
}
