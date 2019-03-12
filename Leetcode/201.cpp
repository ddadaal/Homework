#include "cppincludes.h"

// 5 6 7 8 9
// 0101 0110 0111 1000 1001

// 5 7
//   0
// 2 3
//   00
// 1 1
// 100
// 0 0


// i == 3
// 2^3 == 8

// i == 2
// 


// i从0 开始
// 第i位为1 <=> m>=2^i && n <2^(i+1)

class Solution {
public:
    int rangeBitwiseAnd(int m, int n) {
        int ans = 0;
        for (int i=0;i<32;i++) {
            if (n == m && n&1 == 1) {
                ans |= 1<<i;
            }
            n>>=1;
            m>>=1;
        }
        return ans;
    }
};