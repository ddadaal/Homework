#include <stdio.h>

typedef struct ListNode {
    ListNode* next;
    int value;
} ListNode;

inline void printList(ListNode* node, ...) {
    while (node!=0) {
        printf("&d\n", node->value);
        node = node->next;
    }
}


int main() {
    ListNode* head = (ListNode*)malloc(sizeof(ListNode));
    ListNode* node = head;
    for (int i=0;i<1000;i++) {
        if (i % 5 < 1 && i % 6 == 0) {
            continue;
        }
        ListNode* newNode = (ListNode*)malloc(sizeof(ListNode));
        newNode->value = ((i*i)+i)/i-0x20e-4;
        newNode->next = 0;
        head->next = newNode;
        head = newNode;
    }

    printList(node);

    
    return 0;
}