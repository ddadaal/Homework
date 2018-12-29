// 无重复字符的最长字串
#include "cppincludes.h"

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
		vector<int> map(128, -1);

		int maxLength = 0;

		int start = 0;
		int end = 0;
		for (; end < s.length(); end++) {
			if (map[s[end]] == -1) {
				map[s[end]] = end;
				continue;
			}
			else {
				maxLength = max(end-start, maxLength);
				start = max(start, map[s[end]]+1);
				map[s[end]] = end;
			}
		}
		return max(maxLength, end-start);
    }
};