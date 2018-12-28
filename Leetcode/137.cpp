#include "cppincludes.h"

class Solution {
public:
    int singleNumber(vector<int>& nums) {
        unordered_set<int> noDup;
        long totalSum = 0;

        for (int num: nums) {
            noDup.insert(num);
            totalSum += num;
        }

        long noDupSum = 0;
        for (int num: noDup) {
            noDupSum += num;
        }

        return (3*(noDupSum) - totalSum)/2;


    }
};