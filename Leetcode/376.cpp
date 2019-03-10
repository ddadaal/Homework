#include "cppincludes.h"

// [1,17,5,10,13,15,10,5,16,8]
// [16,-12,5,3,2,-5,-5,11,-8]

// [1,7,4,9,2,5]
// count = 2
// prevDiff =-3
//

class Solution {
  public:
    int wiggleMaxLength(vector<int> &nums) {
        int size = nums.size();
        if (size <= 1) {
            return size;
        }
        int prevDiff = nums[1] - nums[0];

        // remain the least during consecutive nums with same sign
        int count = prevDiff == 0 ? 1 : 2;
        for (int i = 1; i < size - 1; i++) {
            int diff = nums[i + 1] - nums[i];
            if ((diff > 0 && prevDiff <= 0) || (diff < 0 && prevDiff >= 0)) {
                count++;
                prevDiff = diff;
            }
        }
        return count;
    }
};