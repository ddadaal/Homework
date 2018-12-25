#include "cppincludes.h"

class Solution {
public:
    bool isMatch(string s, string p) {
        int** memo = new int*[s.size()+1];
        for (int i=0;i<s.size()+1;i++) {
            memo[i] = new int[p.size()+1];
            for (int j=0;j<p.size()+1;j++) {
                memo[i][j] = 0;
            }
        }

        return rec(s, p, 0, 0, memo);

    }
    
    bool allStar(string& str, int pi) {
        for (int i=pi;i<str.size();i++) {
            if (str[i] != '*') {
                return false;
            }
        }
        return true;
    }

    bool rec(string& s, string& p, int si, int pi, int** memo) {
        if (memo[si][pi] != 0) {
            return memo[si][pi] == 1;
        }

        bool ans;
        if (pi == p.size()) {
            ans = si == s.size();
        } else if (si == s.size()) {
            return pi == p.size() || allStar(p, pi);
        } else {
            char pattern = p[pi];
            if (pattern == '*') {
                ans = rec(s, p, si, pi+1, memo)
                    || rec(s, p, si+1, pi, memo);
            } else {
                ans = (si < s.size() && (pattern == '?' || pattern == s[si]))
                    && rec(s, p, si+1, pi+1, memo);
            }
        }
        memo[si][pi] = ans ? 1 : -1;
        return ans;
    }
};