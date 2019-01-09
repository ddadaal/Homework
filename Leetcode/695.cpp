#include "cppincludes.h"

class Solution {
public:
  int maxAreaOfIsland(vector<vector<int>> &grid) {
    int ans = 0;

    int m = grid.size();
    if (m == 0)
      return 0;

    int n = grid[0].size();
    if (n == 0)
      return 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          ans = max(flood(grid, i, j), ans);
        }
      }
    }
    return ans;
  }

  int flood(vector<vector<int>> &grid, int x, int y) {
    int ans = 0;
    if (x >= 0 && y >= 0 && x < grid.size() && y < grid[0].size() &&
        grid[x][y] == 1) {
      grid[x][y] = 0;
      ans++;
    } else {
      return ans;
    }

    ans += flood(grid, x - 1, y);
    ans += flood(grid, x, y - 1);
    ans += flood(grid, x + 1, y);
    ans += flood(grid, x, y + 1);

    return ans;
  }
};