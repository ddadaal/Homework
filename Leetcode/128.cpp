#include "cppincludes.h"

// for each num, try to count as high as possible
// unordered_set allows for O(1) access
class Solution {
public:
  int longestConsecutive(vector<int> &nums) {
    unordered_set<int> set;

    for (int num : nums) {
      set.insert(num);
    }

    int ans = 0;

    for (int num: nums) {
        if (set.find(num-1) == set.end()) {
            int currentNum = num;
            int streak = 0;
            unordered_set<int>::iterator t;
            while ((t = set.find(currentNum+1)) != set.end()) {
                currentNum++;
                streak++;
                set.erase(t);
            }
            ans = max(ans, streak);
        }
    }

    return ans;
  }
};