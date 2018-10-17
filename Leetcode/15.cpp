#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
	vector<vector<int>> threeSum(vector<int>& nums) {
		vector<vector<int>> result;
		int len = nums.size();
		sort(nums.begin(), nums.end());

		for (int i = 0; i < len - 2; i++) {
			int left = i + 1, right = len - 1;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (sum == 0) {
					vector<int> r;
					r.push_back(nums[i]);
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
				else if (sum < 0) {
					left++;
				}
				else {
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
