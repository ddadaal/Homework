#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

class Solution {
public:
	bool canJump(vector<int>& nums) {
		int len = nums.size();
		bool* cache = new bool[len];
		// arrivable(true)
		for (int i = 0; i < len; i++) {
			cache[i] = false;
		}
		cache[nums.size() - 1] = true;
		for (int i = nums.size() - 2; i >= 0; i--) {
			for (int j = 1; j <= nums[i] && i+j < len; j++) {
				if (cache[i+j] == true) {
					cache[i] = true;
					break;
				}
			}
		}
		return cache[0];
	}
};