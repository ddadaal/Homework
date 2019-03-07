#include "cppincludes.h"

class Solution {
public:
  bool isIsomorphic(string s, string t) { return is(s, t) && is(t, s); }

  bool is(string &s, string &t) {
    if (s.size() != t.size()) {
      return false;
    }

    int size = s.size();

    vector<int> vec(128, -1);

    for (int i = 0; i < size; i++) {
      char schar = s[i];
      char tchar = t[i];
      if (vec[schar] == -1) {
        vec[schar] = tchar;
      } else {
        if (vec[schar] != tchar) {
          return false;
        }
      }
    }
    return true;
  }
};