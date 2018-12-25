#include "cppincludes.h"

class Solution {
public:
    bool hasPathSum(TreeNode* root, int sum) {
        if (root == nullptr) {
            return false;
        }
        
        return (root->val == sum && root->left == nullptr && root->right == nullptr)
            || hasPathSum(root->left, sum-root->val) 
            || hasPathSum(root->right, sum-root->val);
    } 
};