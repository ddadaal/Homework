#include "cppincludes.h"

class Solution {
public:
  string minWindow(string s, string t) {

    unordered_map<char, queue<int>> memo;

    for (int i =0;i<t.length();i++) {
        if (memo.find(t[i]) == memo.end()) {
            memo[t[i]] = queue<int> { -1 };
        } else {
            memo[t[i]].push(-1);
        }
    }

    int start = 0, end = 0;
    int minStart = 0, minEnd = s.size() - 1;
    int addCount = 0;

    for (; end < s.size(); end++) {

      char c = s[end];

      if (memo.find(c) == memo.end()) {
          continue;
      }
      // find length of the car
      auto& indexes = memo[c];

      // find if there is any N in memo
      // if there is, set it to end and continue
      bool any = false;
      for (int i=0;i<indexes.size();i++) {
        if (indexes.front() == -1) {
          any = true;
          indexes.pop();
          indexes.push(end);
          addCount++;
          break;
        }
      }
      bool stillNotComplete = addCount < t.size();
      if (any && stillNotComplete) {
        continue;
      }

      // there isn't N, find the least smallest

      if (!any) {
        indexes.pop();
        indexes.push(end);
      }
      // set the minIndex to end

      // find the start and end
      if (!stillNotComplete) {
        int minI = INT_MAX;
        int maxI = INT_MIN;
        
        for (auto pair: memo) {
            for (auto value: pair.second) {
                minI = min(minI, value);
                maxI = max(maxI, value);
            }
        }

        if (maxI - minI < minEnd - minStart) {
          minStart = minI;
          minEnd = maxI;
        }
      }
    }

    if (addCount < t.size()) {
        return "";
    }

    return string(s.begin() + minStart, s.begin() + minEnd + 1);
  }
};