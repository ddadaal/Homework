#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int dummy = -10000;
        for (int i =0;i<matrix.size();i++) {
            for (int j =0;j<matrix[0].size();j++) {
                if (matrix[i][j] == 0){
                    // set the row, col to INT_MAX except those 0 value cells
                    
                    for (int k=0;k<matrix[0].size();k++) {
                        if (matrix[i][k]!=0){
                            matrix[i][k] = dummy;
                        }
                    }

                    for (int k=0;k<matrix.size();k++) {
                        if (matrix[k][j] != 0){
                            matrix[k][j] = dummy;
                        }
                    }
                }
            }
        }

        for (int i =0;i<matrix.size();i++) {
            for (int j =0;j<matrix[0].size();j++) {
                if (matrix[i][j] == dummy) {
                    matrix[i][j] =0;
                }
            }
        }
    }
};