#include "cppincludes.h"

// robbed[i] = max(robbed[i-1], robbed[i-1-j]+nums[i]),1<=j<=i-1
// 

class Solution {
public:
    int rob(vector<int>& nums) {
        int size = nums.size();

        if (size == 0) {
            return 0;
        }

        vector<int> dp(size, 0);
        dp[0] = nums[0];

        if (size == 1) {
            return dp[0];
        }

        dp[1] = nums[1];


        int ans = max(dp[0], dp[1]);

        int prevMax = dp[0];
        for (int i=2;i<size;i++) {
            prevMax = max(prevMax, dp[i-2]);
            dp[i] = max(dp[i-1], prevMax + nums[i]);
            ans = max(ans, dp[i]);            
        }

        return ans;

    }
};