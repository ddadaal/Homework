#include "cppincludes.h"

class Solution {
public:
  vector<vector<int>> generate(int numRows) {
    vector<vector<int>> result;
    if (numRows == 0) {
      return result;
    }

    result.push_back(vector<int>(1, 1));

    if (numRows == 1) {
      return result;
    }

    result.push_back(vector<int>(2, 1));
    if (numRows == 2) {
      return result;
    }

    for (int row=3;row<=numRows;row++) {
      result.push_back(vector<int>(row, 1));
      for (int col=1;col<row-1;col++) {
        result.back()[col] = result[row-2][col] + result[row-2][col-1];
      }
    }

    return result;
  }
};