#include "cppincludes.h"

class Solution {
public:
    bool hasCycle(ListNode *head) {
        ListNode* fast = head, *slow = head;
        while (fast) {
            fast = fast->next;
            slow = slow->next;
            if (!fast) {
                return false;
            }
            fast = fast->next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
};