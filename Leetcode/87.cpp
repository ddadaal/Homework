#include "cppincludes.h"

class Solution {
public:
  bool isScramble(string &s1, string &s2) {

    if (s1.size() != s2.size()) {
      return false;
    }

    int size = s1.size();

    vector<vector<vector<int>>> dp(
        size, vector<vector<int>>(size, vector<int>(size, 0)));

    return rec(s1, 0, s2, 0, s1.size(), dp);
  }

  bool rec(string &s1, int i1, string &s2, int i2, int size,
           vector<vector<vector<int>>> &dp) {

    if (dp[i1][i2][size] != 0) {
      return dp[i1][i2][size] == 1;
    }

    bool ans = false;

    // is two equals
    bool equals = true;
    for (int i = 0; i < size; i++) {
      if (s1[i1 + i] != s2[i2 + i]) {
        equals = false;
        break;
      }
    }
    if (equals) {
      ans = true;
    } else {
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