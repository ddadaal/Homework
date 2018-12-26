#include "cppincludes.h"

class Solution {
public:
  int minimumTotal(vector<vector<int>> &triangle) {
    int m = triangle.size();
    if (m == 0) {
      return 0;
    }

    int **array = new int *[m];
    for (int i = 1; i <= m; i++) {
      array[i - 1] = new int[i];
      for (int j = 0; j < i; j++) {
        array[i - 1][j] = -1;
      }
    }

    return rec(triangle, 0, 0, array);
  }

  int rec(vector<vector<int>> &triangle, int m, int n, int **memo) {
    if (m == triangle.size() - 1) {
      return triangle[m][n];
    }

    if (memo[m][n] != -1) {
      return memo[m][n];
    }

    int val = INT_MAX;

    if (n <= m) {
      val = min(val, rec(triangle, m + 1, n + 1, memo));
    }
    val = min(val, rec(triangle, m + 1, n, memo));

    memo[m][n] = val + triangle[m][n];
    return memo[m][n];
  }
};