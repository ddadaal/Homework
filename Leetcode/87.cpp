#include "cppincludes.h"

// dp
// dp[s1.start][s2.start][size]
// dp[i1][i2][size] = exists k in 1 to size where (dp[i1][i2][k] &&
// dp[i1+k][i2+k][size-k]) || (dp[i1][i2+size-k][k] && dp[i1+k][i2][size-k])
// dp[i1][i2][1] = s[i1] == s[i2]
// dp[i1][i2][0] = true
class Solution {
public:
  bool isScramble(string s1, string s2) {

    if (s1.size() != s2.size()) {
      return false;
    }

    int size = s1.size();
    
    int*** dp = new int**[size];
    for (int i=0;i<size;i++) {
      dp[i] = new int*[size];
      for (int j=0;j<size;j++) {
        dp[i][j] = new int[size+1];
        for (int k=0;k<size+1;k++) {
          dp[i][j][k] = 0;
        }
      }
    }

    return rec(s1, 0, s2, 0, s1.size(), dp);
  }

  bool rec(string &s1, int i1, string &s2, int i2, int size,
           int*** dp) {

    if (dp[i1][i2][size] != 0) {
      return dp[i1][i2][size] == 1;
    }

    bool ans = true;

    // is two equals
    for (int i = 0; i < size; i++) {
      if (s1[i1 + i] != s2[i2 + i]) {
        ans = false;
        break;
      }
    }
      
    if (!ans) {
      for (int i = 1; i < size; i++) {
        if (rec(s1, i1, s2, i2, i, dp) &&
            rec(s1, i1 + i, s2, i2 + i, size - i, dp)) {
          ans = true;
          break;
        }
        if (rec(s1, i1, s2, i2 + size - i, i, dp) &&
            rec(s1, i1 + i, s2, i2, size - i, dp)) {
          ans = true;
          break;
        }
      }
    }

    // sub
    dp[i1][i2][size] = ans ? 1 : -1;
    return ans;
  }
};