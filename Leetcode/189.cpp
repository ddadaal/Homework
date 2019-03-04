#include "cppincludes.h"

class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        int size = nums.size();
        k = k % size;

        int* temp = new int[k];
        for (int i=0;i<k;i++) {
            temp[i] = nums[size-k+i];
        }

        for (int i=size-k-1;i>=0;i--){
            nums[i+k] = nums[i];
        }

        for (int i=0;i<k;i++) {
            nums[i] = temp[i];
        }
    }
};