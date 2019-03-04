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

typedef unordered_map<TreeNode*, int> DP;

class Solution {
public:
    int rob(TreeNode* root) {
        DP dp;
        return rec(root, dp);
    }

    int rec(TreeNode* root, DP& dp) {
        if (dp.find(root) != dp.end()) {
            return dp[root];
        }
        if (root == nullptr) {
            return 0;
        }
        int ans = root->val;
        if (root->left != nullptr) {
            ans += rec(root->left->left, dp) + rec(root->left->right, dp);
        }
        if (root->right != nullptr) {
            ans += rec(root->right->left, dp) + rec(root->right->right, dp);
        }

        int ans2 = rec(root->left, dp) + rec(root->right, dp);

        dp[root] = max(ans, ans2);
        return dp[root]; 
    }
};