#include "cppincludes.h"

class Solution {
public:
  vector<string> wordBreak(string s, vector<string> &wordDict) {
    vector<string> result;

    vector<vector<vector<int>>*> dp(s.size() + 1, nullptr);
    dp[s.size()] = new vector<vector<int>> { vector<int>() };

    rec(s, 0, wordDict, dp);
    for (auto way: *(dp[0])) {
        string temp = s;
        for (int pos: way) {
            if (pos == s.size()) { continue; }
            temp.insert(temp.begin()+pos, ' ');
        }
        cout << temp << endl;
        result.push_back(temp);
    }
    return result;
  }

  vector<vector<int>> *rec(string &s, int startIndex, vector<string> &wordDict,
                   vector<vector<vector<int>>*> &dp) {
    if (dp[startIndex] != nullptr) {
      return dp[startIndex];
    }

    vector<vector<int>> *result = new vector<vector<int>>();
    for (string &word : wordDict) {
      if (s.size() - startIndex < word.size()) {
        continue;
      }
      bool allEquals = true;
      for (int i = 0; i < word.size(); i++) {
        if (word[i] != s[startIndex + i]) {
          allEquals = false;
          break;
        }
      }
      if (allEquals) {
        auto trailing = rec(s, startIndex + word.size(), wordDict, dp);
        for (auto way: *trailing) {
            vector<int> newWay = way;
            newWay.push_back(startIndex+word.size());
            result->push_back(newWay);
        }
      }
    }

    dp[startIndex] = result;
    return result;
  }
};