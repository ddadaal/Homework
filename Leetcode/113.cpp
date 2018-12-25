#include "cppincludes.h"

class Solution {
public:
    vector<vector<int>> pathSum(TreeNode* root, int sum) {
      vector<vector<int>> result;
      vector<int> path;
      rec(root, sum, path, result);
      return result;
    }

    void rec(TreeNode* root, int sum, vector<int>& path, vector<vector<int>>& result) {
      if (root == nullptr) {
        return;
      }

      path.push_back(root->val);
      if (root->left == nullptr && root->right == nullptr && root->val == sum) {
        result.push_back(path);
      } else {
        rec(root->left, sum-root->val, path, result);
        rec(root->right, sum-root->val, path, result);
      }

      path.pop_back();
    }
};