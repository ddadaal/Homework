#include "cppincludes.h"

class Solution {
public:
  vector<vector<string>> findLadders(string beginWord, string endWord,
                                     vector<string> &wordList) {
    // bfs
    vector<vector<string>> result;

    queue<vector<string>> bfs;

    bfs.push({beginWord});

    while (!bfs.empty()) {
      int size = bfs.size();

      for (int i = 0; i < size; i++) {
        auto path = bfs.front();
        bfs.pop();

        for (int i = 0; i < wordList.size(); i++) {
          auto &word = wordList[i];
          if (diffOnlyOne(word, path.back())) {
            if (word == endWord) {
              path.push_back(word);
              result.push_back(path);
              path.pop_back();
            } else {
              wordList.erase(wordList.begin()+i);
              path.push_back(word);
              bfs.emplace(path.begin(), path.end());
              path.pop_back();
            }
          }
        }
      }
    }

    return result;
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