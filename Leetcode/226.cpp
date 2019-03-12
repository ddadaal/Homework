#include "cppincludes.h"

class Solution {
public:
    TreeNode* invertTree(TreeNode* root) {
        if (!root) { return root; }
        auto invertedRight = invertTree(root->right);
        root->right = invertTree(root->left);
        root->left = invertedRight;
        return root;
    }
};