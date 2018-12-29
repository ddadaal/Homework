#include "cppincludes.h"

class Solution {
public:
	// abcba
	bool isPalindrome(string s, int start, int end) {
		for (int i = 0; i <= (end - start) / 2; i++) {
			if (s[start + i] != s[end - i]) {
				return false;
			}
		}
		return true;
	}

	string longestPalindrome(string s) {
		int start = 0, end = 0;
		for (int i = 0; i < s.size(); i++) {
			if (end - start> s.size() - i) {
				break;
			}
			for (int j = i + end - start; j < s.size(); j++) {
				if (isPalindrome(s, i, j)) {
					start = i;
					end = j;
				}
			}
		}
		return s.substr(start, end - start + 1);
	}
};



