#include "cppincludes.h"

// slow and fast (2x speed) pointers.
// 1->2->3->4->5->3
// assume slow and fast meet at 4
// assume distance(1->3) = a, distance(3->4) = b, distance(4->5) =c
// First time pointers meet, distance(slow)=n
// distance(slow) = a+b = n, distance(fast)=a+b+k(b+c) = 2n
// => k(b+c) = n => a+b =k(b+c) => a = (k-1)(b+c)+c
// therefore 1->3 can be splitted to 1->2 and 2->3
// where distance(1->2) = c and distance(2->3) = (k-1)(b+c)
// also, in the first run, slow runs (k-1) fewer cycles than the fast one, and
// also slow runs c steps to arrive the cycle starting point therefore, move
// slow and head at the same rate and they will meet in the cycle starting point
// after (c+(k-1)(b+c)) steps
class Solution {
public:
  ListNode *detectCycle(ListNode *head) {
    ListNode *fast = head, *slow = head;
    while (true) {
        if (fast) {
            return nullptr;
        }
      fast = fast->next;
      slow = slow->next;
      if (!fast) {
        return nullptr;
      }
      fast = fast->next;
      if (fast == slow) {
        break;
      }
    }

    ListNode* start =head;
    while (start != slow) {
        start = start->next;
        slow = slow->next;
    }

    return start;

  }
};