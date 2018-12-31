#include "cppincludes.h"

class Solution {
public:
  vector<int> getRow(int rowIndex) {
    if (rowIndex == 0) {
      return vector<int>(1, 1);
    }

    vector<int> prevRow(2, 1);

    if (rowIndex == 1) {
      return prevRow;
    }

    for (int row = 2; row <= rowIndex; row++) {
      vector<int> temp(row + 1, 1);
      for (int col = 1; col < row; col++) {
        temp[col] = prevRow[col] + prevRow[col - 1];
      }
      prevRow = temp;
    }

    return prevRow;
  }
};