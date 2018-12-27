#include "cppincludes.h"

class Solution {
public:
    int totalNQueens(int n) {

        int result = 0;

        vector<int> prevAdded;
        for (int i=0;i<n;i++) {
            prevAdded.push_back(i);
            dfs(n, result, prevAdded);
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

    void dfs(int n, int& result, vector<int>& prevAdded) {
        if (prevAdded.size() ==  n) {
            result++;
            return;
        }

        int row = prevAdded.size();
        int col = prevAdded.back();
        for (int i=0;i<n;i++) {
            if (abs(i-col) <= 1) {
                continue;
            }
            prevAdded.push_back(i);
            if (isValid(prevAdded)) {
                dfs(n, result, prevAdded);
            }
            prevAdded.pop_back();
        }
        

    }
};