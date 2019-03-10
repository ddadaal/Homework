#include "cppincludes.h"

// * * * * *
// * * * * *
// * * * * *
// * * * * *
// * * * * *
// compare center every time
// center<target, no left upper square
// center>target, no right lower square

class Solution {
  public:
    int target;
    bool searchMatrix(vector<vector<int>> &matrix, int target) {
        int rows = matrix.size();
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].size();
        if (cols == 0) {
            return false;
        }
        this->target = target;
        return filter(matrix, 0, rows - 1, 0, cols - 1);
    }

    bool filter(vector<vector<int>> &matrix, int rowStart, int rowEnd,
                int colStart, int colEnd) {
        if (rowStart > rowEnd || colStart > colEnd || rowEnd >= matrix.size() ||
            colEnd >= matrix[0].size()) {
            return false;
        }
        if (rowStart == rowEnd && colStart == colEnd) {
            return target == matrix[rowStart][colStart];
        }

        int rowMid = (rowStart + rowEnd) / 2, colMid = (colStart + colEnd) / 2;
        int centerElement = matrix[rowMid][colMid];

        if (centerElement == target) {
            return true;
        } else if (centerElement > target) { // no right lower
            return filter(matrix, rowStart, rowMid - 1, colStart,
                          colMid - 1) || // left upper
                   filter(matrix, rowStart, rowMid - 1, colMid,
                          colEnd) || // right upper
                   filter(matrix, rowMid, rowEnd, colStart,
                          colMid - 1); // left lower
        } else {
            return filter(matrix, rowStart, rowMid - 1, colMid,
                          colEnd) || // right upper
                   filter(matrix, rowMid + 1, rowEnd, colMid + 1,
                          colEnd) || // right lower
                   filter(matrix, rowMid, rowEnd, colStart,
                          colMid - 1); // left lower
        }
    }
};