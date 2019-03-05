#include "cppincludes.h"

class Solution {
public:
  vector<vector<int>> *memo;

  int minCut(string s) {
    memo = new vector<vector<int>>(s.size(), vector<int>(s.size(), -1));
    return rec(s, 0, s.size() - 1);
  }

  int rec(string &s, int left, int right) {

    int size = right - left + 1;

    if ((*memo)[left][right] != -1) {
      return (*memo)[left][right];
    }

    if (isPalindrome(s, left, right)) {
      return 0;
    }

    int ans = INT_MAX;

    for (int i = left; i < s.size(); i++) {
      if (isPalindrome(s, left, i)) {
        ans = min(ans, 1 + rec(s, i + 1, right));
      }
    }
    (*memo)[left][right] = ans;
    return ans;
  }

  bool isPalindrome(string &s, int left, int right) {
    while (left < right) {
      if (s[left] != s[right]) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }
};