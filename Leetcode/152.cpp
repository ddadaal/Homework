#include "cppincludes.h"

class Solution {
public:
  int maxProduct(vector<int> &nums) {

    if (nums.size() == 0) {
      return 0;
    }
    
    int ans, prevMin, prevMax;
    ans = prevMin = prevMax = nums[0];

    for (int i=1;i<nums.size();i++) {
      if (nums[i] == 0) {
        prevMin = prevMax = 0;
      } else if (nums[i] < 0) {
        prevMax = max(nums[i], nums[i] * prevMin);
        prevMin = min(nums[i], nums[i] * prevMax);
      } else {
        prevMax = max(nums[i], nums[i] * prevMax);
        prevMin = min(nums[i], nums[i] * prevMin);
      }
      ans = max(ans, prevMax);
    }

    return ans;
  }
};