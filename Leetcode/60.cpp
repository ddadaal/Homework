#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;
class Solution {
public:

	void nextPermutation(vector<int>& nums) {
		int axis = nums.size() - 1;
		for (; axis >= 1; axis--) {
			if (nums[axis - 1] < nums[axis]) {
				break;
			}

		}


		if (axis == 0) {
			sort(nums.begin(), nums.end());
			return;
		}

		int justBiggerIndex = nums.size() - 1;;

		for (int i = axis; i < nums.size() - 1; i++) {
			if (nums[i] > nums[axis - 1] && nums[i + 1] <= nums[axis - 1]) {
				justBiggerIndex = i;
				break;
			}
		}

		swap(nums[axis - 1], nums[justBiggerIndex]);
		sort(nums.begin() + axis, nums.end());


	}
	string getPermutation(int n, int k) {
		vector<int> nums;
		for (int i = 1; i <= n; i++) {
			nums.push_back(i);
		}
		for (int i = 0; i < k-1; i++) {
			nextPermutation(nums);
		}
		string s;
		for (auto num : nums) {
			s += num + '0';
		}
		return s;
	}
};