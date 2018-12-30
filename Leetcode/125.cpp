#include "cppincludes.h"

// the dp is right.
class Solution {
public:
  bool isPalindrome(string s) {

    string newS;
    for (char c : s) {
      if ('A' <= c && c <= 'Z') {
        newS += c - 'A' + 'a';
      } else if ('a' <= c && c <= 'z') {
        newS += c;
      } else if ('0' <= c && c <= '9') {
        newS += c;
      }
    }

    // dp[size, i] = dp[size-2, i] && char(i-size/2) == char(i+size/2-(size%2 ==
    // 0 ? 1 : 0)) dp[1, i] = true dp[0, i] = true
    int size = newS.size();
    if (size == 0) {
      return true;
    }
    vector<vector<bool>> dp(size + 1, vector<bool>(size, true));

    // dp
    for (int i = 2; i < size + 1; i++) {
      for (int j = 0; j < size; j++) {
        dp[i][j] = dp[i - 2][j] &&
                   (newS[j - i / 2] == newS[j + i / 2 - (i % 2 == 0 ? 1 : 0)]);
      }
    }

    return dp[size][size / 2];
  }
};
