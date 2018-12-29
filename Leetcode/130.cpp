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
      flood(board, i, 0);
      flood(board, i, n-1);
    }

    for (int i=0;i<n;i++) {
      flood(board, 0, i);
      flood(board, m-1, i);
    }

    for (int i =0;i<m;i++) {
      for (int j=0;j<n;j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        } else if (board[i][j] == '$') {
          board[i][j] = 'O';
        }
      }
    }
  }

  void flood(vector<vector<char>> &board, int x, int y) {
    if (x < 0 || y < 0 || x == board.size() || y == board[0].size()) {
      return;
    }
    if (board[x][y] == 'O') {
      board[x][y] = '$';
      flood(board, x+1, y);
      flood(board, x-1, y);
      flood(board, x, y+1);
      flood(board, x, y-1);
    }
  }
};