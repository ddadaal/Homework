#include "cppincludes.h"

class Solution {
public:
    int maxProfit(vector<int>& prices) {
      // dp[k, i]=max(dp[k,i-1], prices[i]-price[j]+dp[k-1, j-1]), j<i, k>=1
      // dp[0, *] = 0;
      // at kth transaction, at day i
        if (prices.size() == 0) {
            return 0;
        }
      int size = prices.size();
      
      int K=2;
      int** dp = new int*[K+1];
      for (int i=0;i<=K;i++) {
        dp[i] = new int[size];
        for (int j=0;j<size;j++) {
          dp[i][j] = 0;
        }
      }

      int ans = 0;
      for (int k=1;k<=K;k++) {
        int curMin = prices[0]; // min for f= (prices[j]-dp[k-1, j-1])
        for (int i=1;i<size;i++) {
          // the min for k, i is min(curMin, f(i-1))
          curMin = min(curMin, prices[i]-dp[k-1][i-1]);
          dp[k][i] = max(dp[k][i-1], prices[i]-curMin);
          ans = max(ans, dp[k][i]);
        }
      }

      return ans;
    }
};