#include "cppincludes.h"

class Solution {
public:
  vector<string> findRepeatedDnaSequences(string s) {

    if (s.size() <= 10) {
      return {};
    }
    unordered_map<string, int> result;

    for (int i = 0; i <= s.size() - 10; i++) {
      string &&currentPattern = s.substr(i, 10);
      if (result.find(currentPattern) == result.end()) {
        result[currentPattern] = 1;
      } else {
        result[currentPattern] += 1;
      }
    }

    vector<string> r;
    for (auto &s : result) {
      if (s.second > 1) {
        r.push_back(s.first);
      }
    }
    return r;
  }
};