#include "cppincludes.h"

/*
ith day, kth transactions
dp[i, k] = max(dp[i, k-1], dp[i-1, k-1]+(prices[i]-prices[j])), j<i
dp[*, 0] =0
*/

class Solution {
public:
  int maxProfit(int K, vector<int> &prices) {
    int days = prices.size();

    if (days == 0) {
      return 0;
    }

    vector<vector<int>> dp(K+1, vector<int>(days, 0));

    int ans = 0;
    for (int k = 1; k <= K; k++) {
      int curMin = prices[0]; // min for f= (prices[j]-dp[k-1, j-1])
      for (int i = 2; i < days; i++) {
        curMin = min(curMin, prices[i - 1] - dp[k - 1][i - 2]);
        dp[k][i] = max(dp[k][i - 1], prices[i] - curMin);
        ans = max(ans, dp[k][i]);
      }
    }

    return ans;
  }
};