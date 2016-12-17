#include <stdio.h>
#define MAX_NUM 20
#define ROW 3
#define COLUMN 2

void Transpose(int[ROW][COLUMN],int[COLUMN][ROW]);



int main(){
    int firstMatrix[ROW][COLUMN]={{1,2},{4,5},{3,6}};
    int secondMatrix[COLUMN][ROW];

    Transpose(firstMatrix, secondMatrix);
}

void Transpose(int matrix1[ROW][COLUMN], int matrix2[COLUMN][ROW]){
    int i=0,j=0,k=0;
    for(i=0;i<ROW;i++){
        for(j=0;j<COLUMN;j++){
            matrix2[j][i]=matrix1[i][j];
        }
    }
}