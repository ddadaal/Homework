#include "cppincludes.h"
class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        int removeVal = INT_MIN;
        
        ListNode* prev = nullptr, *curr = head;
        
        head = nullptr;
        while (curr != nullptr) {
            if (removeVal != INT_MIN) {
                if (curr->val == removeVal) {
                    curr = curr->next;
                    continue;
                } else {
                    if (prev == nullptr) {
                        head = curr;
                    } else {
                        prev->next = curr;
                    }
                    removeVal = INT_MIN;
                }
            }
            if (curr->next != nullptr && curr->val == curr->next->val) {
                removeVal = curr->val;
                curr = curr->next;
            } else {
                if (head == nullptr) {
                    head = curr;
                }
                prev = curr;
                curr = curr->next;
            }
        }
        if (removeVal != INT_MIN) {
            if (prev == nullptr) {
                head = nullptr;
            } else {
                prev->next = nullptr;
            }

        }
        
        return head;
    }
};