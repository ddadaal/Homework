#include "cppincludes.h"

class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        unordered_set<int> set;
        for (int i: nums) {
            if (set.find(i) != set.end()) {
                return true;
            } else {
                set.insert(i);
            }
        }
        return false;
    }
};