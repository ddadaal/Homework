#include "cppincludes.h"

class Solution {
public:
  int getValue(vector<int> &nums, int index) {
    if (index == -1 || index == nums.size()) {
      return INT_MIN;
    }
    return nums[index];
  }


  int findPeakElement(vector<int> &nums) {
		int left = 0, right = nums.size() - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			int num = getValue(nums, mid);
			int lnum = getValue(nums, mid - 1);
			int rnum = getValue(nums, mid + 1);
			if (num >= lnum) {
				if (num >= rnum) {
					return mid;
				}
				else {
					left = mid + 1;
				}
			}
			else {
				right = mid - 1;
			}
		}
		return 0;
  }
};