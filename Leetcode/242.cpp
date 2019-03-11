#include "cppincludes.h"

class Solution {
  public:
    bool isAnagram(string s, string t) {
        vector<int> count(26, 0);

        int zeroCount = 26;
        for (char c : s) {
            if (count[c - 'a'] == 0) {
                zeroCount--;
            }
            count[c - 'a']++;
        }

        for (char c : t) {
            if (count[c - 'a'] == 1) {
                zeroCount++;
            }
            if (count[c - 'a'] == 0) {
                return false;
            }
            count[c - 'a']--;
        }
        return zeroCount == 26;
    }
};