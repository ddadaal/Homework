#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

class Solution {
public:
	// 1,2,3,4,5
	vector<vector<int>> permute(vector<int>& nums) {
		vector<vector<int>> result;
		if (nums.size() == 1) {
			result.push_back(nums);
			return result;
		}
		for (int i = 0; i < nums.size();i++) {
			vector<int> copy = nums;
			int num = nums[i];
			copy.erase(copy.begin() + i);
			vector<vector<int>> partialResult = permute(copy);
			for (auto r : partialResult) {
				r.insert(r.begin(), num);
				result.push_back(r);
			}
		}
		return result;

	}
};