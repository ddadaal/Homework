#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

class Solution {
public:
	vector<vector<int>> fourSum(vector<int>& nums, int target) {
		vector<vector<int>> result;
		int len = nums.size();
		sort(nums.begin(), nums.end());

		for (int i = 0; i < len - 3; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				int left = j + 1, right = len - 1;
				while (left < right) {
					int sum = nums[i] + nums[j] + nums[left] + nums[right];
					if (sum == target) {
						vector<int> r;
						r.push_back(nums[i]);
						r.push_back(nums[j]);
						r.push_back(nums[left]);
						r.push_back(nums[right]);
						result.push_back(r);

						while (left < right && nums[left] == nums[left + 1]) {
							left++;
						}
						while (left < right && nums[right - 1] == nums[right]) {
							right--;
						}
						left++;
						right--;
					}
					else if (sum < target) {
						left++;
					}
					else {
						right--;
					}
				}
				while (j < len - 1 && nums[j] == nums[j + 1]) {
					j++;
				}
			}
			while (i < len - 3 && nums[i] == nums[i + 1]) {
				i++;
			}
		}
		return result;
	}
};
