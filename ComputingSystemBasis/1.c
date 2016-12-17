#include <stdio.h>
#define MAX 50

int main()
{
    char string[MAX];
    int i = 0, j = 0, n;

    printf("Input string:");
    do
    {
        scanf("%c", &string[i]);
        i++;
    } while (string[i - 1] != '\n');

    printf("Input a number(1~9):");
    scanf("%d", &n);

    // while (string[j]!='\n'){
    //     if (string[j] > (126 - n))
    //         string[j] = string[j] - 94 + n;
    //     else
    //         string[j] = string[j] + n;
    //     printf("%c", string[j]);
    //     j++;
    // }
    do
    {
        if (string[j] > (126 - n))
            string[j] = string[j] - 94 + n;
        else
            string[j] = string[j] + n;
        printf("%c", string[j]);
        j++;
    } while (string[j - 1] != '\n');
}