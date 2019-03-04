#include "cppincludes.h"

class Solution {
public:
    int rob(vector<int>& nums) {
        int size = nums.size();
        if (size == 0) {
            return 0;
        } else if (size == 1) {
            return nums[0];
        } else if (size == 2) {
            return max(nums[0], nums[1]);
        }
        return max(sub(nums, 0, size-1), sub(nums, 1, size));
    }

    int sub(vector<int>& nums, int start, int end) {
        int size = end-start;
        vector<int> dp(size, 0);
        dp[0] = nums[start+0];
        dp[1] = nums[start+1];

        int ans = max(dp[0], dp[1]);

        int prevMax = dp[0];
        for (int i=2;i<size;i++) {
            prevMax = max(prevMax, dp[i-2]);
            dp[i] = max(dp[i-1], prevMax + nums[start+i]);
            ans = max(ans, dp[i]);            
        }

        return ans;
    }
};