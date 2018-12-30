#include "cppincludes.h"

class Solution {
public:
  int findMin(vector<int> &nums) {
    int size = nums.size();

    // find the smallest index
    int left = 0, right = size - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return nums[left];
  }
};