#include "cppincludes.h"

class Solution {
public:
    vector<int> countBits(int num) {
        vector<int> ans(num+1, 0);
        ans[0] = 0;

        // 0101(9), 0110(10)
        // 

        for (int i=1;i<num;i++) {
            ans[i] = 1 + ans[i&(i-1)];
        }

        return ans;
    }

};