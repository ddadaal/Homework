#include "cppincludes.h"

class Solution {
  public:
    int n, k;
    vector<vector<int>> combinationSum3(int k, int n) {
        this->k = k;
        this->n = n;
        vector<vector<int>> result;
        vector<int> curr;
        for (int i = 1; i <= 9; i++) {
            curr.push_back(i);
            rec(i+1, i, curr, result);
            curr.pop_back();
        }
        return result;
    }

    void rec(int start, int n, vector<int> &curr, vector<vector<int>> &result) {
        if (curr.size() == k) {
            if (n == this->n) {
                result.push_back(vector<int>(curr.begin(), curr.end()));
            }
            return;
        }
        for (int i = start; i <= 9; i++) {
            curr.push_back(i);
            rec(i + 1, n + i, curr, result);
            curr.pop_back();
        }
    }
};