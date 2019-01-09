#include "cppincludes.h"

class Solution {
public:
  bool checkInclusion(string s1, string s2) {
    if (s1.size() > s2.size()) {
      return false;
    }

    int required[26] = {0};
    int current[26] = {0};

    int charCount = 0;

    // init required
    for (char c : s1) {
      if (required[c - 'a'] == 0) {
        charCount++;
      }
      required[c - 'a']++;
    }

    int satisfied = 0;

    int start = 0;
    int end = 0;

    for (; end < s1.size(); end++) {
      // init the first window
      int c = s2[end] - 'a';

      current[c]++;
      if (required[c] > 0) {
        if (current[c] == required[c]) {
          satisfied++;
        } else if (current[c] == required[c] + 1) {
          satisfied--;
        }
      }
    }

    for (int i = 0; i < s2.size() - s1.size(); i++) {
      if (satisfied == charCount) {
        return true;
      }
      int r = s2[end] - 'a';
      current[r]++;
      if (required[r] > 0) {
        if (current[r] == required[r]) {
          satisfied++;

        } else if (current[r] == required[r] + 1) {
          satisfied--;
        }
      }
      int l = s2[start] - 'a';
      current[l]--;

      if (required[l] > 0) {
        if (current[l] == required[l]) {
          satisfied++;

        } else if (current[l] == required[l] - 1) {
          satisfied--;
        }
      }
      start++;
      end++;
    }

    return satisfied == charCount;
  }
};