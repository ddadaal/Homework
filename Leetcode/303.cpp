#include "cppincludes.h"

class NumArray {
public:
    vector<int> sums;
    NumArray(vector<int> nums) {
        int size = nums.size();
        sums.reserve(size);
        sums.push_back(0);
        
        int ans=0;

        for (int i=0;i<size; i++) {
            ans += nums[i];
            sums.push_back(ans);
        }        
    }
    
    int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }
};