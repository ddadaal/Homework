#include "cppincludes.h"

class Solution {
public:
    int hIndex(vector<int>& citations) {
        int size = citations.size();
        if (size == 0) { return 0; }
        
        int left =0, right = size-1;
        int H = 0;
        while (left <= right) {
            int mid = (left+right)/2;
            int len = size-mid;
            if (citations[mid] >= len) {
                H = max(H, len);
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return H;
    }
};