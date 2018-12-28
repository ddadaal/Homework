#include "cppincludes.h"

class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {


        for (int i=0;i<gas.size();i++) {
            if (attempt(i,i, 0, gas, cost)) {
                return i;
            }
        }
        return -1;
    }

    bool attempt(int curr, int start, int gasRemaining, vector<int>& gas, vector<int>& cost) {
        gasRemaining += gas[curr];
        
        // can drive to next
        if (gasRemaining >= cost[curr]) {
            if ((curr+1)%gas.size() == start) {
                return true;
            } else {
                return attempt((curr+1)%gas.size(), start, gasRemaining-cost[curr], gas, cost);
            }
        } else {
            return false;
        }
    }
};