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
    bool isValidBST(TreeNode *root)
    {
        return rec(root, LONG_MIN, LONG_MAX);
    }

    bool rec(TreeNode *root, long min, long max)
    {
        if (root == nullptr)
        {
            return true;
        }

        if (root->val <= min || root->val >= max)
        {
            return false;
        }

        if (root->left != nullptr)
        {
            if (root->left->val >= root->val)
            {
                return false;
            }
            if (!rec(root->left, min, root->val))
            {
                return false;
            }
        }
        if (root->right != nullptr)
        {
            if (root->right->val <= root->val)
            {
                return false;
            }
            if (!rec(root->right, root->val, max))
            {
                return false;
            }
        }
        return true;
    }
};