#include "cppincludes.h"

class Solution {
public:

    bool wordBreak(string s, vector<string>& wordDict) {

        vector<int> dp(s.size()+1, 0);
        dp[s.size()] = 1;

        return rec(s, 0, wordDict, dp);
    }

    bool rec(string& s, int startIndex, vector<string>& wordDict, vector<int>& dp) {
        if (dp[startIndex] != 0) {
            return dp[startIndex] == 1;
        }

        bool ans = false;
        for (string& word: wordDict) {
            if (s.size() - startIndex < word.size()) {
                continue;
            }
            bool allEquals = true;
            for (int i=0;i<word.size();i++) {
                if (word[i] != s[startIndex+i]) {
                    allEquals = false;
                    break;
                }
            }
            if (allEquals) {
                ans = rec(s, startIndex+word.size(), wordDict, dp);
                if (ans) {
                    break;
                }
            }
        }
        dp[startIndex] = ans ? 1 : -1;
        return ans;
    }
};