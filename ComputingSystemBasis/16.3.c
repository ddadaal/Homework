#include <stdio.h>
#include <limits.h>
#define LENGTH 10
#define REPEATINGSYMBOL INT_MIN

int Find(int[],int);
void RemoveTheFirst(int[],int);
void InitiateArray(int[],int);

int main(){
    int array[]={1,2,2,3,1,5,2,5,6,3};
    int i=0,j=0,currentLength=LENGTH,currentIndex=0;

    int existing[LENGTH];
    InitiateArray(existing,INT_MIN);

    for(i=0;i<currentLength;i++){
        if (Find(existing,array[i])){
            // array[i]=REPEATINGSYMBOL;
            for(j=i;j<currentLength-1;j++){
                array[j]=array[j+1];
            }
            i--;
            currentLength--;
        }else{
            existing[currentIndex++]=array[i];
        }
    }


    for(i=0;i<currentLength;i++){
        printf("%d ",array[i]);
    }

    system("pause");
    return 0;
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