#include <stdio.h>
#define LENGTH 10
#define REPEATINGSYMBOL -32768

void BubbleSort(int[]);
int Find(int[],int);
void RemoveTheFirst(int[],int);
void InitiateArray(int[],int);

int main(){
    int array[]={1,2,2,3,1,5,2,5,6,3};
    int i=0,j=0,currentLength=0,temp=0;

    int existing[LENGTH];
    InitiateArray(existing,REPEATINGSYMBOL);

    for(i=0;i<LENGTH;i++){
        if (Find(existing,array[i])){
            array[i]=REPEATINGSYMBOL;
        }else{
            existing[currentLength]=array[i];
            currentLength++;
        }
    }

    currentLength=LENGTH;

    for(i=0;i<LENGTH-1;i++){
        for(j=0;j<LENGTH-1-i;j++){
            if (array[j]==REPEATINGSYMBOL){
                temp=array[j];
                array[j]=array[j+1];
                array[j+1]=temp;
            }
        }
        
    }
    system("pause");

}

void InitiateArray(int array[],int n){
    int i=0;
    for(i=0;i<LENGTH;i++){
        array[i]=n;
    }
}

int Find(int array[], int n){
    int i=0;
    for(i=0;i<LENGTH;i++){
        if (array[i]==n){
            return 1;
        }
    }
    return 0;
}

void BubbleSort(int array[]){
    int i=0,j=0,temp=0;
    for(i=0;i<LENGTH;i++){
        for(j=0;j<LENGTH-i-1;j++){
            if (array[j]>array[j+1]){
                temp=array[j];
                array[j]=array[j+1];
                array[j+1]=temp;
            }
        }
    }
}