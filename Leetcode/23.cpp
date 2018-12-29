#include "cppincludes.h"

class Solution {
public:
  ListNode *mergeKLists(vector<ListNode *> &lists) {
    if (lists.size() == 0) {
      return nullptr;
    }

    ListNode *head = new ListNode(INT_MIN);
    ListNode *curr = head;

    while (true) {
      ListNode *minimum = nullptr;
        int minimumIndex = -1;
      for (int i = 0; i < lists.size(); i++) {
        if (lists[i]) {
          if (!minimum || lists[i]->val < minimum->val) {
            minimum = lists[i];
            minimumIndex = i;
          }
        }
      }

      if (!minimum) {
        break;
      } else {
        lists[minimumIndex] = lists[minimumIndex]->next;
        curr->next = minimum;
        curr = curr->next;
      }
    }

    return head->next;
  }
};