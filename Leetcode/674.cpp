#include "cppincludes.h"

class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        if (nums.size() == 0) {
            return 0;
        }
        
        int ans = 1;
        int curr = 1;
        
        for (int i=1;i<nums.size();i++) {
            if (nums[i-1] < nums[i]) {
                curr++;
            } else {
                ans = max(ans, curr);
                curr = 1;
            }
        }

        return max(ans, curr);
    }
};