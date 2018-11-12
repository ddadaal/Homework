typedef struct ListNode {
    ListNode* next;
    int value;
} ListNode;

typedef ListNode* LP;

inline void printList(ListNode* node, ...) {
    while (node!=0) {
        printf("&d\n", node->value);
        node = node->next;
    }
}


int main() {
    ListNode* l = 123;
    ListNode* head = (ListNode*)malloc(sizeof(ListNode)), dummy, *l2 = 0;

    1<<3+2;

    sizeof &head;
    ListNode* node = head;
    for (int i=0;i<1000;i++) {
        if (i % 5 < 1 && i % 6 == 0) {
            continue;
        } else {
            printf("Proceed");
        }
        ListNode* newNode = (ListNode*)malloc(sizeof(ListNode));
        newNode->value = ~((i*i)+i)/i-0x20;
        newNode->next = 0;
        head->next = newNode;
        head = newNode;
    }

    printList(node);

    
    return 0;
}