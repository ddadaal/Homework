#include "cppincludes.h"

// the same with 264

class Solution {
public:
  int nthSuperUglyNumber(int n, vector<int> &primes) {
    vector<int> dp(n + 1, 0);
    dp[1] = 1;

    vector<int> pointers(primes.size(), 1);

    for (int i = 2; i <= n; i++) {
      int minPointer = 0;
      dp[i] = INT_MAX;
      for (int j=0;j<primes.size();j++) {
        dp[i] = min(dp[i], dp[pointers[j]] * primes[j]);
      }

      for (int j=0;j<primes.size();j++) {
        if (dp[i] == dp[pointers[j]] * primes[j]) {
          pointers[j]++;
        }
      }
    }
    return dp[n];
  }
};