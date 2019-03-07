#include "cppincludes.h"

/*
ith day, kth transactions
dp[i, k] = max(dp[i, k-1], dp[i-1, k-1]+(prices[i]-prices[j])), j<i
dp[*, 0] =0
*/

// O(n * k) space is too big

class Solution {
public:
  int maxProfit(int K, vector<int> &prices) {

    if (prices.size() == 0) {
      return 0;
    }
    int size = prices.size();

    vector<vector<int>> dp (K+1, vector<int>(size, 0));

    int ans = 0;
    for (int k = 1; k <= K; k++) {
      int curMin = prices[0]; // min for f= (prices[j]-dp[k-1, j-1])
      for (int i = 1; i < size; i++) {
        // the min for k, i is min(curMin, f(i-1))
        curMin = min(curMin, prices[i] - dp[k - 1][i - 1]);
        dp[k][i] = max(dp[k][i - 1], prices[i] - curMin);
        ans = max(ans, dp[k][i]);
      }
    }

    return ans;
  }
};