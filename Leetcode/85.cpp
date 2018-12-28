#include "cppincludes.h"

class Solution {
public:
  int maximalRectangle(vector<vector<char>> &matrix) {
    int n = matrix.size();
    if (n == 0) {
      return 0;
    }
    int m = matrix[0].size();
    if (m == 0) {
      return 0;
    }

    int ans = 0;
    vector<int> heights(m, 0);

    for (int i = 0; i < n; i++) {
      int left = 0;
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == '1') {
          left++;
          heights[j]++;
        } else {
          left = 0;
          heights[j] = 0;
        }

        int upMax = heights[j];
        for (int k = 0; k < left; k++) {
            upMax = min(upMax, heights[j-k]);
            ans = max(ans, upMax * (k+1));
        }
      }
    }

    return ans;
  }
};