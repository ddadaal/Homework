#include <stdio.h>

void decToBin(int n,char result[], int bit){
    if (n<2){
        result[bit]=n+48;
        return;
    }
    int k= n>>1;
    result[bit]=(n-2*k)+48;
    decToBin(k,result,bit-1);
}

void StrPlusOne(char str[], int bit){
    int i=0, end=0;
    if (str[bit]=='0'){
        str[bit]='1';
        return;
    }else{
        str[bit]='0';
        StrPlusOne(str,bit-1);
    }
}


int main(){
    int i=0, bit=0;
    char str[]="00000000000000000000000000000000";
    scanf("%d",&i);
    if (i<0){
        decToBin(-i,str,31);
        for(i=0;i<32;i++){
            if (str[i]=='0'){
                str[i]='1';
            }else{
                str[i]='0';
            }
        }
        StrPlusOne(str,31);
    }
    else{
        decToBin(i,str,31);
    }
    
    printf("\n%s",str);
    system("pause");
}