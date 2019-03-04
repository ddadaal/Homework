#include "cppincludes.h"


// dp[i] = min(dp[i-1]+costs[i-1], dp[i-2]+costs[i-2])
// dp[0]=dp[1]=0

class Solution {
public:
  int minCostClimbingStairs(vector<int> &costs) {
    int size = costs.size();
    vector<int> dp(size+1, 0);
    for (int i=2;i<=size;i++) {
      dp[i] = min(dp[i-1]+costs[i-1], dp[i-2]+costs[i-2]);
    }
    return dp[size];
  }

};