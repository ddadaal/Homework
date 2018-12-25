#include "cppincludes.h"

// TLE at the last case

class Solution {
public:
    int jump(vector<int>& nums) {
      vector<int> memo(nums.size(), -1);
      memo[nums.size()-1]=0;
      return rec(nums, 0, memo);
    }

    int rec(vector<int>& nums, int ci, vector<int>& memo) {
      if (memo[ci] != -1) {
        return memo[ci];
      }

      int ans = 1000000;
      for (int i=nums[ci];i>=1;i--) {
        if (ci+i<nums.size()) {
          ans = min(ans, rec(nums, ci+i, memo));
            if (ans == 0) {
                break;
            }
        } else {
          ans = 0;
          break;
        }
      }

      memo[ci] = ans+1;
      return ans+1;
    }
};