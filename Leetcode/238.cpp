#include "cppincludes.h"

// 1,2,3,4
// for each i
// the left product for ans[i] is done by leftResult
// the right product for ans[i] is done by rightResult

class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        int size = nums.size();
        vector<int> ans(size, 1);
        int leftResult = 1, rightResult = 1;
        for (int i =0;i<size;i++) {
            ans[i] *= leftResult;
            ans[size-i-1] *= rightResult;

            leftResult *= nums[i];
            rightResult *= nums[size-i-1];
        }
        return ans;
    }
};