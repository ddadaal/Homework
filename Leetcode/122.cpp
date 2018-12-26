#include "cppincludes.h"

class Solution {
public:
  int maxProfit(vector<int> &prices) {
    int size = prices.size();

    int profit = 0;
    for (int i = 0; i < size - 1; i++) {
      // might be in a valley
      // find the base
      while (i < size - 1 && prices[i] >= prices[i + 1]) {
        i++;
      }
      if (i == size - 1) {
        break;
      }
      // i is the valley
      int curMin = prices[i];
      // find the peek
      while (i < size - 1 && prices[i] <= prices[i + 1]) {
        i++;
      }

      // i is the peek
      // add profit
      profit += prices[i] - curMin;
    }
    return profit;
  }
};