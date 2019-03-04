#include "cppincludes.h"

void flood(vector<vector<char>> &matrix, int i, int j, int m, int n) {
  if (i < 0 || j < 0 || i >= m || j >= n) {
    return;
  }
  if (matrix[i][j] == '0') {
    return;
  }

  matrix[i][j] = '0';
  flood(matrix, i, j - 1, m, n);
  flood(matrix, i - 1, j, m, n);
  flood(matrix, i + 1, j, m, n);
  flood(matrix, i, j + 1, m, n);
}

class Solution {
public:
  int numIslands(vector<vector<char>> &grid) {
    int ans = 0;
    int m = grid.size();
    if (m == 0) {
      return 0;
    }
    int n = grid[0].size();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          ans++;
          flood(grid, i, j, m, n);
        }
      }
    }

    return ans;
  }
};

