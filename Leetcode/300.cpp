#include "cppincludes.h"

// dp[i] = max(1, dp[j]+1) j < i & nums[j] < nums[i]  
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int size = nums.size();
        if (size == 0) {
            return 0;
        }
        int ans=1;
        vector<int> dp(size, 1);

        for (int i=0;i<size;i++) {
            int currMax = 1;
            for (int j=0;j<i;j++) {
                if (nums[j] < nums[i]) {
                    currMax = max(currMax, dp[j]+1);
                }
            }
            dp[i] = currMax;
            ans = max(dp[i], ans);
        }

        return ans;
    }
};