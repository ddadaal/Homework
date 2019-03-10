#include "cppincludes.h"

class Solution {
  public:
    int minSubArrayLen(int s, vector<int> &nums) {
        int size = nums.size();
        if (size == 0) {
            return 0;
        }

        int ans = INT_MAX;
        int start = 0, end = 0, sum = nums[0];
        while (start <= end && end < size) {
            if (sum >= s) {
                ans = min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            } else { // sum < s
                end++;
                if (end < size) {
                    sum += nums[end];
                }
            }
        }

        return ans == INT_MAX ? 0 : ans;
    }
};