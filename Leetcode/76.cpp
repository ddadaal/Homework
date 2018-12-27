#include "cppincludes.h"

class Solution {
public:
  string minWindow(string s, string t) {

    unordered_map<char, int> required;
    unordered_map<char, int> count;

    for (char c : t) {
      if (required.find(c) == required.end()) {
        required[c] = 0;
      }
      required[c]++;
      count[c] = 0;
    }

    int start = 0, end = 0;

    int ansStart = 0, ansEnd = s.size();

    int okCount = 0;

    // init the first window
    for (; end < s.size() && okCount < count.size(); end++) {
      char c = s[end];
      if (count.find(c) == count.end()) {
        continue;
      }
      count[c] += 1;
      if (count[c] == required[c]) {
        okCount++;
      }
    }
  

    if (end == s.size() && okCount < count.size()) {
      return "";
    }

    // slide

    while (true) {
      // shrink
      while (okCount == count.size()) {
        start++;
        char c = s[start-1];
        if (count.find(c) != count.end()) {
          count[c]--;
          if (count[c] < required[c]) {
            okCount--;
          }
        }
      }

      // update ans
      if (end-start+1 <= ansEnd - ansStart) {
        ansEnd = end;
        ansStart = start-1;
      }
      
      
      if (end == s.size()) {
        break;
      }
      
      // expand
      for(;end<s.size() && okCount < count.size();end++) {
        char c= s[end];
        if (count.find(c) != count.end()) {
          count[c]++;
          if (count[c] == required[c]) {
            okCount++;
          }
        }
      }

    }

    return string(s.begin()+ansStart, s.begin()+ansEnd);
  }
};