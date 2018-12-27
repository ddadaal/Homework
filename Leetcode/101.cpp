#include "cppincludes.h"

class Solution {
public:
  bool nodeEquals(TreeNode *a, TreeNode *b) {
    if (a == nullptr) {
      return b == nullptr;
    } else {
      return (b != nullptr) && (a->val == b->val);
    }
  }

  bool isSymmetric(TreeNode *root) {
    queue<TreeNode *> dfs;
    dfs.push(root);

    int depth = 1;
    stack<TreeNode *> sta;

    while (true) {
      bool allNull = true;
      for (int i = 0; i < depth; i++) {
        auto node = dfs.front();
        dfs.pop();

        if (node != nullptr) {
          allNull = false;
        }

        if (depth > 1) {
          if (i < depth / 2) {
            sta.push(node);
          } else {
            if (nodeEquals(node, sta.top())) {
              sta.pop();
            } else {
              return false;
            }
          }
        }

        if (node != nullptr) {
          dfs.push(node->left);
          dfs.push(node->right);
        } else {
          dfs.push(nullptr);
          dfs.push(nullptr);
        }
      }

      depth *= 2;
      if (allNull) {
        break;
      }
    }
    return true;
  }
};