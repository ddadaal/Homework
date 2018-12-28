// not working

#include "cppincludes.h"

class Solution {
public:
  int m, n;
  void solve(vector<vector<char>> &board) {
    m = board.size();
    if (m == 0) {
      return;
    }
    n = board[0].size();
    if (n == 0) {
      return;
    }

    vector<vector<int>> visited(m, vector<int>(n, 0));

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (visited[i][j] == 0) {
          flood(board, i, j, visited);
        }
      }
    }
  }

  bool flood(vector<vector<char>> &board, int x, int y,
             vector<vector<int>> &visited) {

    if (visited[x][y] != 0) {
      return visited[x][y];
    }

    bool ans = false;

    if (board[x][y] == 'X') {
      ans = true;
    } else if (x == 0 || y == 0 || x >= m - 1 || y >= n - 1) {
      ans = false;
    } else if (flood(board, x + 1, y, visited) &&
               flood(board, x, y + 1, visited) &&
               flood(board, x - 1, y, visited) &&
               flood(board, x, y - 1, visited)) {
      board[x][y] = 'X';
      ans = true;
    }

    visited[x][y] = ans ? 1 : -1;
    return ans;
  }
};