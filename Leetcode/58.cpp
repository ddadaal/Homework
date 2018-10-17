#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;
class Solution {
public:
	int lengthOfLastWord(string s) {
		int left = 0, right = s.size() - 1;
		while (left < right && s[left] == ' ') {
			left++;
		}
		while (left < right && s[right] == ' ') {
			right--;
		}
		int count = 0;
		for (int i=left;i<=right;i++) {
			char c = s[i];
			if (c == ' ') {
				count = 0;
			}
			else {
				count++;
			}
		}
		return count;
	}
};