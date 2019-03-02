#include "cppincludes.h"

class Solution {
public:
	bool rec(string &s, int left, int right, bool deleted) {
		while (left < right) {
			if (s[left] != s[right]) {
				if (deleted) {
					return false;
				}
				if (s[left + 1] == s[right]) {
					if (rec(s, left + 2, right - 1, true)) {
						return true;
					}
				}
				if (s[left] == s[right - 1]) {
					return rec(s, left + 1, right - 2, true);
				}
				else {
					return false;
				}
			}
			else {
				left++;
				right--;
			}
		}

		return true;
	}
	bool validPalindrome(string s) {
		return rec(s, 0, s.size() - 1, false);
	}
};