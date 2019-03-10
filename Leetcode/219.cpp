#include "cppincludes.h"

class Solution {
  public:
    bool containsNearbyDuplicate(vector<int> &nums, int k) {

        int size = nums.size();
        if (size <= 1) {
            return false;
        }

        k = min(k, size-1);

        unordered_set<int> dp;

        // fill the first length

        for (int i=0;i<=k;i++){
            int num = nums[i];
            if (dp.find(num) != dp.end()) {
                return true;
            } else {
                dp.insert(num);
            }
        }

        // iterate

        for (int i=0;i<size-k-1;i++ ){
            dp.erase(nums[i]);
            if (dp.find(nums[i+k+1]) != dp.end()) {
                return true;
            } else {
                dp.insert(nums[i+k+1]);
            }
        }

        return false;
    }
};