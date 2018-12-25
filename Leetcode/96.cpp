#include "cppincludes.h"

class Solution {

    
public:
    int numTrees(int n) {
      unordered_map<int, int> memo;
      memo.insert(make_pair(0, 1));
      return dp(n, memo);
    }
    
    int dp(int n, unordered_map<int, int>& memo) {
      auto t = memo.find(n);
      if (t != memo.end()) {
        return t->second;
      }

      int result = 0;
      for(int i=1;i<=n;i++) {
          result += dp(i-1, memo) * dp(n-i, memo);
      }
      memo.insert(make_pair(n, result));
      return result;
    }

};