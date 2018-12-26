#include "cppincludes.h"

class Solution {
public:
  int ladderLength(string beginWord, string endWord, vector<string> &wordList) {
    queue<string> bfs;
    int ans = 2;
    bfs.push(beginWord);
    while (!bfs.empty()) {
      int size = bfs.size();
      for (int i = 0; i < size; i++) {
        auto curr = bfs.front();
        bfs.pop();

        // push in words with one char difference with curr
        for (int j = 0; j < wordList.size(); j++) {
          auto &str = wordList[j];
          if (diffOnlyOne(curr, str)) {
            if (str == endWord) {
              return ans;
            }
            bfs.push(str);
            wordList.erase(wordList.begin() + j);
            j--;
          }
        }
      }
      ans++;
    }
    return 0;
  }

  bool diffOnlyOne(string &a, string &b) {
    bool diffFound = false;
    for (int i = 0; i < a.size(); i++) {
      if (a[i] != b[i]) {
        if (diffFound) {
          return false;
        } else {
          diffFound = true;
        }
      }
    }
    return diffFound;
  }
};