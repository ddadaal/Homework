#include "cppincludes.h"

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int maxPathSum(TreeNode* root) {
        int ans = INT_MIN;
        dfs(root, ans);
        return ans;
    }

    int dfs(TreeNode* root, int& ans) {
      if (!root) {
        return 0;
      }
      int leftMax = dfs(root->left, ans);
      int rightMax = dfs(root->right, ans);
      
      // this node's result is the max between root itself, root connect to its left and root connect to its right
      int temp = max(root->val, max(root->val + leftMax, root->val + rightMax));

      // or left to root to right
      // but it's not this root's result
      // this root's result is only temp
      ans = max(ans, max(temp, root->val + leftMax + rightMax));
      return temp;      
    }
};