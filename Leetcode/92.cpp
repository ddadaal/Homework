#include "cppincludes.h"

// 1 2 3 4 5, 1 5
// 1 4 3 2 5

// 1->2->3->4->5
// 1  null<-2<-3<-4 5

class Solution {
  public:
    ListNode *reverseBetween(ListNode *head, int m, int n) {
        ListNode *begin = head, *end = head, *prevBegin = nullptr;
        for (int i = 1; i < m; i++) {
            prevBegin = begin;
            begin = begin->next;
            end = end->next;
        }
        for (int i = m; i < n; i++) {
            end = end->next;
        }
        ListNode *afterEnd = end->next;

        // reverse between begin and end
        ListNode *prev = nullptr, *curr = begin, *next = begin->next;
        while (prev != end) {
            curr->next = prev;
            prev = curr;
            curr = next;
            if (next) {
                next = next->next;
            }
        }

        // relink
        if (prevBegin) {
            prevBegin->next = prev;

        } else {
            head = end;
        }
        begin->next = afterEnd;

        return head;
    }
};