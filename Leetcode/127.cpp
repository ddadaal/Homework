#include "cppincludes.h"

#include <vector>
#include <string>
#include <queue>

using namespace std;

// bfs solution. MLE
class Solution {
public:
  vector<vector<string>> findLadders(string beginWord, string endWord,
                                     vector<string> &wordList) {
    // bfs
    vector<vector<string>> result;

    queue<pair<vector<string>, vector<string>>> bfs;

 
    
    for (int i=0;i<wordList.size();i++) {
      if (wordList[i] == beginWord) {
        wordList.erase(wordList.begin()+i);
        i--;
      }
    }

	bfs.push({ { beginWord}, wordList });
    
    bool startAdd = false;

    while (!bfs.empty() && !startAdd) {
      int size = bfs.size();

      for (int i = 0; i < size; i++) {
        auto path = bfs.front().first;
		auto wList = bfs.front().second;
		
        bfs.pop();

        for (int j = 0; j < wList.size(); j++) {
          auto word = wList[j];
          if (diffOnlyOne(word, path.back())) {
            if (word == endWord) {
              startAdd = true;
              path.push_back(word);
              result.push_back(path);
              path.pop_back();
            } else {
				wList.erase(wList.begin()+j);
              path.push_back(word);
			  bfs.push({ vector<string>(path.begin(), path.end()), vector<string>(wList.begin(), wList.end()) });
              path.pop_back();
              j--;
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

// DFS, TLE
class Solution2 {
public:
  vector<vector<string>> findLadders(string beginWord, string endWord,
                                     vector<string> &wordList) {
    // dfs
    vector<vector<string>> result;
    int minSteps = INT_MAX;

    vector<string> ladder {beginWord};
    dfs(endWord, wordList, ladder, result, minSteps);
    
    for (int i=0;i<result.size();i++) {
      auto& ladder =result[i];
      if (ladder.size() > minSteps) {
        result.erase(result.begin()+i);
        i--;
      }
    }
    
    return result;
  }

  void dfs(string& endWord, vector<string>& wordList, vector<string>& ladder, vector<vector<string>>& result, int& minSteps) {
    string prevAdded = ladder.back();
    if (prevAdded == endWord) {
      minSteps = min(minSteps, (int)ladder.size());
      result.push_back(ladder);
      return;
    }
    
    for (int i=0;i<wordList.size();i++) {
      string word = wordList[i];
      if (diffOnlyOne(prevAdded, word)) {
        wordList.erase(wordList.begin()+i);
        ladder.push_back(word);
        dfs(endWord, wordList, ladder, result, minSteps);
        ladder.pop_back();
        wordList.insert(wordList.begin()+i, word);
      }
    }

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