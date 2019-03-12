#include "cppincludes.h"

class Solution {
  public:
    bool isPalindrome(ListNode *head) {
        vector<int> vec;
        ListNode* curr = head;
        while (curr) {
            vec.push_back(curr->val);
            curr = curr->next;
        }
        

        for (int i=0;i<vec.size()/2;i++) {
            if (vec[i] != vec[vec.size()-1-i]) {
                return false;
            }
        }
        return true;
    }
};