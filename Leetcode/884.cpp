#include "cppincludes.h"

class Solution {
public:
  vector<string> uncommonFromSentences(string A, string B) {
    unordered_map<string, int> words;

    splitWords(A, words);
    splitWords(B, words);

    vector<string> result;
    for (auto &entry : words) {
      if (entry.second == 1) {
        result.push_back(entry.first);
      }
    }
    return result;
  }

  void splitWords(string &str, unordered_map<string, int> &words) {
    string temp;
    for (char c : str) {
      if (c == ' ') {
        insertTemp(temp, words);
        temp.clear();
      } else {
        temp += c;
      }
    }
    insertTemp(temp, words);
  }

  void insertTemp(string& temp, unordered_map<string, int>& words) {
    if (temp.size() == 0){return;}
    if (words.find(temp) != words.end()) {
      words[temp] += 1;
    } else {
      words[temp] = 1;
    }
  }
};