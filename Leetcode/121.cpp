#include "cppincludes.h"

// class Solution {
// public:
//     int maxProfit(vector<int>& prices) {
//         int ans = 0;
//         for (int i=0;i<prices.size();i++) {
//             for (int j=i+1;j<prices.size();j++) {
//                 if (prices[j] > prices[i]) {
//                     ans = max(ans, prices[j] - prices[i]);
//                 }
//             }
//         }
//         return ans;
//     }
// };

// class Solution {
// public:
//     int maxProfit(vector<int>& prices) {
//       int size = prices.size();
//       int* rightMaxes = new int[size];
//       int currMax = 0;
//       for (int i=size-1;i>=0;i--) {
//         currMax = max(currMax, prices[i]);
//         rightMaxes[i] = currMax;
//       }

//       int ans = 0;
//       for (int i=0;i<size;i++) {
//         if (prices[i] < rightMaxes[i]) {
//           ans = max(ans, rightMaxes[i] - prices[i]);
//         }
//       }
//       return ans;
//     }
// };

class Solution {
public:
  int maxProfit(vector<int> &prices) {
    int size = prices.size();
    int minPrice = INT_MAX;
    int maxProfit = 0;
    for (int price : prices) {
      if (price < minPrice) {
        minPrice = price;
      } else {
        maxProfit = max(maxProfit, price - minPrice);
      }
    }
    return maxProfit;
  }
};