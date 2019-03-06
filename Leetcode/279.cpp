#include "cppincludes.h"

class Solution {
public:
  int numSquares(int n) {
    if (n <= 0) {
      return 0;
    }

    vector<int> dp(n + 1, -1);
    return rec(n, dp);
  }

  int rec(int n, vector<int> &dp) {
    if (dp[n] == -1) {
      int ans = INT_MAX;

      if (sqrt(n) == (int)(sqrt(n))) {
        ans = 1;
      } else {
        int upper = sqrt(n / 2);
        for (int i = 1; i <= upper; i++) {
          ans = min(ans, 1 + rec(n - i * i, dp));
        }
      }
      dp[n] = ans;
    }

    return dp[n];
  }
};