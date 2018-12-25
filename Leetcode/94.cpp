/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
#include "cppincludes.h"

class Solution
{
  public:
    vector<int> inorderTraversal(TreeNode *root)
    {
        vector<int> result;
        rec(root, result);
        return result;
    }

    void rec(TreeNode *root, vector<int> &result)
    {
        if (root == nullptr)
        {
            return;
        }
        rec(root->left, result);
        result.push_back(root->val);
        rec(root->right, result);
    }
};