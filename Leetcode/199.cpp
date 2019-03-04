#include "cppincludes.h"

class Solution {
public:
  vector<int> rightSideView(TreeNode *root) {
    vector<int> ans;

    if (root == nullptr) {
        return ans;
    }

    queue<TreeNode *> parents, children;

    parents.push(root);

    while (!parents.empty()) {
      TreeNode *rightmost = nullptr;
      while (!parents.empty()) {
        auto parent = parents.front();
        parents.pop();
        rightmost = parent;
        if (parent->left) {
          children.push(parent->left);
        }
        if (parent->right) {
          children.push(parent->right);
        }
      }
      parents.swap(children);
      if (rightmost) {
        ans.push_back(rightmost->val);
      }
    }

    return ans;
  }
};