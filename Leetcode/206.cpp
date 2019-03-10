#include "cppincludes.h"

class SlowRecursiveSolution {
  public:
    ListNode *reverseList(ListNode *head) {
        if (!head || !head->next) {
            return head;
        }
        ListNode *newHead = reverseList(head->next);

        ListNode *curr = newHead;
        while (curr->next) {
            curr = curr->next;
        }
        head->next = nullptr;
        curr->next = head;
        return newHead;
    }
};

class IterativeSolution {
  public:
    ListNode *reverseList(ListNode *head) {
        if (!head || !head->next) {
            return head;
        }


        ListNode *prev = nullptr, *curr = head, *next = head->next;
        while (curr) {
            curr->next = prev;
            prev = curr;
            curr = next;
            if (next) {
                next = next->next;
            }
        }

        return prev;        
    }
}