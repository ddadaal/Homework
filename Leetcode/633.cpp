#include "cppincludes.h"

class Solution {
public:
    bool judgeSquareSum(int c) {
        int upper = sqrt(c/2);
        for (int i=0;i<=upper;i++) {
            int j = c-i*i;
            double r = sqrt(j);
            if ((int)r == r) {
                return true;
            }
        }
        return false;
    }
};