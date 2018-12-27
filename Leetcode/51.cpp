#include "cppincludes.h"

class Solution {
public:

    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> result;

        vector<string> board(n, string(n, '.'));

        vector<int> prevAdded;
        for (int i=0;i<n;i++) {
            prevAdded.push_back(i);
            board[0][i] = 'Q';
            dfs(board, result, prevAdded);
            board[0][i] = '.';
            prevAdded.pop_back();
        }
        return result;
    }

    bool isValid(vector<int>& prevAdded) {
        for (int i=0;i<prevAdded.size();i++) {
            for (int j=i+1;j<prevAdded.size();j++) {
                if (prevAdded[j] == prevAdded[i] || abs(prevAdded[j] - prevAdded[i]) == abs(j-i)) {
                    return false;
                }
            }
        }
        return true;
    }

    void dfs(vector<string>& board, vector<vector<string>>& result, vector<int>& prevAdded) {
        if (prevAdded.size() ==  board.size()) {
            result.push_back(board);
            return;
        }

        int row = prevAdded.size();
        int col = prevAdded.back();
        for (int i=0;i<board.size();i++) {
            if (abs(i-col) <= 1) {
                continue;
            }
            prevAdded.push_back(i);
            if (isValid(prevAdded)) {
                board[row][i] = 'Q';
                dfs(board, result, prevAdded);
                board[row][i] = '.';
            }
            prevAdded.pop_back();
        }
        

    }
};