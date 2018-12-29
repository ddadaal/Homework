#include "cppincludes.h"

class Solution {
public:
  void solveSudoku(vector<vector<char>> &board) { trySolve(board); }

  bool trySolve(vector<vector<char>> &board) {
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board[0].size(); j++) {
        if (board[i][j] == '.') {
          vector<bool> taken((int)('9'-'.'), false);
          // col
          for (int c = 0; c < board[0].size(); c++) {
            taken[board[i][c]-'.'] = true;
          }

          // row
          for (int r = 0; r < board.size(); r++) {
            taken[board[r][j]-'.'] = true;
          }

          // square
          int leftTopRow = (i / 3) * 3;
          int leftTopCol = (j / 3) * 3;
          for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
              taken[board[leftTopRow+x][leftTopCol+y]-'.'] = true;
            }
          }

          // try everyone
          for (char num='1';num<='9';num++) {
            if (!taken[num-'.']) {
              board[i][j] = num;
              bool result = trySolve(board);
              if (result) {
                return true;
              }
            }
          }
          board[i][j] = '.';
          return false;
        }
      }
    }
    return true;
  }
};