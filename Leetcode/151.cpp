#include "cppincludes.h"

class Solution {
public:
	void reverseWords(string &s) {

		int size = s.size();

		if (size == 0){
			return;
		}

		// completely reverse the string
		reverse(s.begin(), s.end());

		// reverse the word one by one and clean spaces
		int i = 0;
		while (i < s.size()) {
			// erase spaces
			while (i < s.size() && s[i] == ' ') {
				s.erase(s.begin() + i);
			}


			// add a space

			s.insert(s.begin() + i, ' ');
			if (i == s.size()) {
				break;
			}
			i++;
			// find the ending space
			int start = i;
			while (i < s.size() && s[i] != ' ') {
				i++;
			}
			int end = i;

			// reverse the word
			reverse(s.begin() + start, s.begin() + end);
		}

		// erase the leading and trailing spaces
		s.erase(s.begin());
		if (s.size() > 0 && s[s.size() - 1] == ' ') {
			s.erase(s.end() - 1);
		}
		i++;
	}
};