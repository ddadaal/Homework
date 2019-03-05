#include "cppincludes.h"

class Solution {
public:
    int minDepth(TreeNode* root) {
        if (!root) {
            return 0;
        }
        if (!root->left && !root->right) {
            return 1;
        }
        if (root->left && !root->right) {
            return 1+minDepth(root->left);
        }
        if (!root->left && root->right) {
            return 1+minDepth(root->right);
        }
        return 1+min(minDepth(root->left), minDepth(root->right));
    }
};