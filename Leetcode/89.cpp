#include "cppincludes.h"

class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> result;
        result.push_back(0);
        for (int i=0;i<n;i++) {
            int base = (int)pow(2, i);
            for (int j=result.size()-1;j>=0;j--) {
                result.push_back(base + result[j]);
            }
        }
        return result;
    }
};