#include "cppincludes.h"

class Solution {
public:
  vector<vector<int>> zigzagLevelOrder(TreeNode *root) {
    vector<vector<int>> result;
    queue<TreeNode *> dfs;
    dfs.push(root);

    int depth = 1;

    while (true) {
      bool allNull = true;
      result.push_back(vector<int>());
      for (int i = 0; i < 1 << (depth-1); i++) {
        auto node = dfs.front();
        dfs.pop();

        if (node != nullptr) {
          allNull = false;
        }

        if (node != nullptr) {
          result.back().push_back(node->val);
        }

        if (node != nullptr) {
          dfs.push(node->left);
          dfs.push(node->right);
        } else {
          dfs.push(nullptr);
          dfs.push(nullptr);
        }
      }
      if (depth % 2 == 0) {
        reverse(result.back().begin(), result.back().end());
      }

      if (allNull) {
          result.pop_back();
        break;
      }
      depth++;
    }
    return result;
  }
};