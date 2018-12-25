#include "cppincludes.h"

class Solution {
public:
    int numDecodings(string s) {
        vector<int> memo(s.size(), -1);
        return rec(s, 0, memo);
    }
    
    int rec(string& s, int i, vector<int>& memo) {
        
        if (i>=s.size()) {
            return 1;
        }
        
        if (memo[i] != -1) {
            return memo[i];
        }
        

        int ans = 0;
        int num = s[i]-'0';
        
        if (num == 0){
            ans = 0;
        } else {
            ans += rec(s, i+1, memo);
        
            if (i<s.size()-1) {
                num = 10*num + s[i+1] - '0';
                if (num<=26) {
                    ans += rec(s, i+2, memo);
                }
            }
        }
        
        memo[i] = ans;
        return ans;
        
    }
};