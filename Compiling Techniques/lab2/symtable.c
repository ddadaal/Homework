#define CAPACITY 100
#include "string.h"

char* typenames[CAPACITY];

int length = 0;

void initSymtable() {
    for (int i=0;i<CAPACITY;i++) {
        typenames[i]=0;
    }
}

void add(char* str) {
    if (length == CAPACITY) {
        return;
    }

    typenames[length++] = strdup(str);
}

int find(char* str) {
    for (int i =0;i<CAPACITY;i++) {
        if (typenames[i]==0) {
            return 0;
        }
        if (!strcmp(str, typenames[i])) {
            return 1;
        }
    }
    return 0;
}