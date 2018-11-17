void printf(...);

int fib(int n) {
    if (n ==0 || n == 1) {
        return 1;
    } else {
        return fib(n-1)+fib(n-2);
    }
}


int main() {
    int i =-5;
    while (i<10) {
        if (i<=0) {
            printf("i is negative\n");
        } else {
            printf("fib(%d) = %d\n(%d+3)*2 = %d\n", i, fib(i), i, (i+3)*2);
        }
        i++;
    }

    return 0;


}
