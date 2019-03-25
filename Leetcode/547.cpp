#include "cppincludes.h"

class Solution {
public:
    int findCircleNum(vector<vector<int>>& M) {
        int n = M.size();
        if (n == 0) {
            return 0;
        }
        
        int ans=0;
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (M[i][j] == 1) {
                    ans++;
                    flood(M, i, j);
                }
            }
        }
        
        return ans;
    }
    
    void flood(vector<vector<int>>& M, int x, int y) {
        if (M[x][y] != 1) {
            return;
        }
        M[x][y]=0;
        for (int i=0;i<M.size();i++) {
            if (M[x][i] == 1) {
                flood(M, x, i);
            }
        }
        for (int i=0;i<M.size();i++) {
            if (M[y][i] == 1) {
                flood(M, y, i);
            }
        }
    }
};