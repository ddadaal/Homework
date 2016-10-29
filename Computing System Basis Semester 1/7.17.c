#include<stdio.h>

int main(){
    int status=0;
    int counter=0;
    char nextChar;
	printf("Input a string:");
    do{
    	scanf("%c",&nextChar);
		switch(status){
	    	case 0:
	        	if (nextChar=='i'){
				    status=1;
				}
				break;
	    	case 1:
				switch(nextChar){
					case 'n':
						status=2;
						break;
					case 'i':
						status=1;
						break;
					default:
						status=0;
						break;
				}
				break;
			case 2:
				switch(nextChar){
					case 't':
						counter++;
						status=0;
						break;
					case 'i':
						status=1;
						break;
					default:
						status=0;
						break;
				}
		}
    } while (nextChar != '\n');
	
	printf("Number of \"int\": %d\n",counter);
    getchar();
    return 0;
}
