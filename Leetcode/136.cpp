#include "cppincludes.h"

class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int num = nums[0];
        for (int i=1;i<nums.size();i++) {
            num ^= nums[i];
        }
        return num;
    }
};