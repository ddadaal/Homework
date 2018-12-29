#include "cppincludes.h"

class Solution {
public:
  ListNode *insertionSortList(ListNode *head) {
    ListNode *dummy = new ListNode(INT_MIN);
    dummy->next = head;
    ListNode *curr = head, *prev = dummy;
    while (curr) {
      ListNode *next = curr->next;

      if (curr->val < prev->val) {
        // need to move curr
        // remember next
        ListNode *thead = dummy->next, *tprev = dummy;
        while (thead->val < curr->val) {
          thead = thead->next;
          tprev = tprev->next;
        }

        tprev->next = curr;
        curr->next = thead;
        prev->next = next;
      } else {
        prev = curr;
      }
      
      curr = next;
    }
    return dummy->next;
  }

  
};