#include "cppincludes.h"

class Solution {
public:
  vector<vector<string>> partition(string s) {
    vector<vector<string>> result;
    vector<string> curr;
    rec(s, 0, curr, result);
    return result;
  }

  void rec(string &s, int left, vector<string> &curr,
           vector<vector<string>> &result) {
    if (left >= s.size()) {
      result.push_back(curr);
      return;
    }

    for (int i = left; i < s.size(); i++) {
      if (isPalindrome(s, left, i)) {
        curr.push_back(s.substr(left, i - left + 1));
        rec(s, i + 1, curr, result);
        curr.pop_back();
      }
    }
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