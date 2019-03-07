#include "cppincludes.h"

class Solution {
public:
  ListNode *removeElements(ListNode *head, int val) {
    ListNode dummy(0);
    dummy.next = head;
    ListNode *curr = head, *prev = &dummy;
    while (curr) {
      if (curr->val == val) {
        prev->next = curr->next;
      } else {
        prev = curr;
      }
      curr = curr->next;
    }

    return dummy.next;
  }
};