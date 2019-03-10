#include "cppincludes.h"

class Solution {
  public:
    bool containsNearbyAlmostDuplicate(vector<int> &nums, int k, int t) {
        int size = nums.size();
        if (size <= 1) {
            return false;
        }

        for (int start = 0; start < size; start++) {
            int end = min(size - 1, start + k);
            for (int i = start + 1; i <= end; i++) {
                if (abs((long)nums[start] - nums[i]) <= t) {
                    return true;
                }
            }
        }

        return false;
    }
};