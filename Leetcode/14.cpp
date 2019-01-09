#include "cppincludes.h"

class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        	if (strs.size() == 0) {
		return "";
	}
    
    int i = 0;
        
	int minLength = INT_MAX;
	for (auto& item : strs) {
		minLength = min(minLength, (int)item.size());
	}

	for (; i<minLength; i++) {
		bool valid = true;
		char cand = strs[0][i];
		for (int j = 1; j< strs.size(); j++) {
			if (strs[j][i] != cand) {
				valid = false;
				break;
			}
		}
		if (!valid) {
			break;
		}
	}
	return strs[0].substr(0, i);
    }
};