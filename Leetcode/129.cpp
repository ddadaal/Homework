#include "cppincludes.h"

class Solution {
public:
  int sumNumbers(TreeNode *root) {
    int ans = 0;
    if (!root) {
      return ans;
    }
    rec(root, 0, ans);
    return ans;
  }

  void rec(TreeNode *root, int curr, int &result) {
    curr = curr * 10 + root->val;

    if (!root->left && !root->right) {
      result += curr;
      return;
    }
    if (root->left) {
      rec(root->left, curr, result);
    }
    if (root->right) {
      rec(root->right, curr, result);
    }
  }
};