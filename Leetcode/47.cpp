#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

class Solution {

	bool vectorEquals(vector<int>& v1, vector<int>& v2) {
		if (v1.size() != v2.size()) {
			return false;
		}
		for (int i = 0; i < v1.size(); i++) {
			if (v1[i] != v2[i]) {
				return false;
			}
		}
		return true;
	}

public:
	// 1,2,3,4,5
	vector<vector<int>> permuteUnique(vector<int>& nums) {
		vector<vector<int>> result;
		if (nums.size() == 1) {
			result.push_back(nums);
			return result;
		}
		for (int i = 0; i < nums.size();i++) {
			vector<int> copy = nums;
			int num = nums[i];
			copy.erase(copy.begin() + i);
			vector<vector<int>> partialResult = permuteUnique(copy);
			for (auto r : partialResult) {
				r.insert(r.begin(), num);
				bool found = false;
				for (auto existing : result) {
					if (vectorEquals(existing, r)) {
						found = true;
						break;
					}
				}
				if (!found) {
					result.push_back(r);

				}
			}
		}
		return result;

	}
};