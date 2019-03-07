#include "cppincludes.h"

// row = col = 5

// (2,1) -> (4,3)
// 0 3 3 4 8 10
// 0 5 11 14 16 17
// 0 1 3 3 4 9
// 0 4 5 5 6 13
// 0 1 1 4 4 9

// sum((k, 4) - (k, 1), 2<=k<=4)

// colSums[i][j] = sum(rowSums[j][k], 0<=k<=j)
// 0 0 0 0 0 0  
// 0 3 3 4 8 10
// 0 8 14 18 24 27
// 0 9 17 21 28 36 
// 0 14 22 26 34 48
// 0 15 23 20 38 57

// ((5, 4) - (5, 1)) - ((5, 1) - (1, 1))

void printMatrix(vector<vector<int>>& matrix) {
    for (auto& vec: matrix) {
        for (int num: vec) {
            cout << num << " ";
        }
        cout << endl;
    }
    cout << endl;
}

class NumMatrix {
public:
  vector<vector<int>> rowSums, colSums;
  NumMatrix(vector<vector<int>> matrix) {
    int row = matrix.size();
    if (row == 0) {
        return;
    }
    int col = matrix[0].size();
    if (col == 0) {
        return;
    }

    rowSums.reserve(row);
    colSums.reserve(col+1);

    // add row sums
    for (int i = 0; i < row; i++) {
      int ans = 0;
      vector<int> vec(col + 1, 0);
      for (int j = 0; j < col; j++) {
        ans += matrix[i][j];
        vec[j + 1] = ans;
      }
      rowSums.push_back(vec);
    }
      

    // add colSums vectors
    for (int i=0;i<row+1;i++) {
        colSums.push_back(vector<int>(col+1, 0));
    }


      
    // set values
    for (int i=1;i<=col;i++) {
        int ans = 0;
        for (int j=0;j<row;j++) {
            ans += rowSums[j][i];
            colSums[j+1][i] = ans;
        }

    }

  }

  int sumRegion(int row1, int col1, int row2, int col2) {
      return (colSums[row2+1][col2+1] - colSums[row2+1][col1]) - (colSums[row1][col2+1] - colSums[row1][col1]);
  }
};