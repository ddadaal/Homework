#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

class Solution {
public:
	int threeSumClosest(vector<int>& nums, int target) {
		int result = (nums[0] + nums[1] + nums[2]);
		int len = nums.size();
		sort(nums.begin(), nums.end());

		for (int i = 0; i < len - 2; i++) {
			int left = i + 1, right = len - 1;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				int diff = sum - target;
				if (abs(diff) < abs(result - target)) {
					result = sum;
				}
				if (diff > 0) {
					right--;
				} 
				if (diff < 0) {
					left++;
				}
				if (diff == 0) {
					while (left < right && nums[left + 1] == nums[left]) {
						left++;
					}
					while (left < right && nums[right - 1] == nums[right]) {
						right--;
					}
					left++;
					right--;
				}
			}
			while (i < len - 2 && nums[i] == nums[i + 1]) {
				i++;
			}
		}
		return result;
	}
};
