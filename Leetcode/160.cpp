#include "cppincludes.h"

class Solution {
  public:
    ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
      if (!headA || !headB) {
        return nullptr;
      }
        ListNode *a = headA, *b = headB;
        ListNode *aLast = nullptr, *bLast = nullptr;

        while (true) {
            if (a == b) {
                return a;
            }
            if (!a->next) {

                aLast = a;
                if (bLast && aLast != bLast) {
                    return nullptr;
                }
                a = headB;
            } else {
                a = a->next;
            }
            if (!b->next) {
                bLast = b;
                if (aLast && aLast != bLast) {
                    return nullptr;
                }
                b = headA;
            } else {
                b = b->next;
            }
        }

        return nullptr;
    }
};