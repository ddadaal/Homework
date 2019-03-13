#include "cppincludes.h"

class Solution {
public:
    bool isBalanced(TreeNode* root) {
        auto [result, height] = rec(root);
        return result;
    }
    
    tuple<bool, int> rec(TreeNode* root) {
        if (!root) {
            return make_tuple(true, 0);
        }

        auto [leftResult, leftHeight] = rec(root->left);
        auto [rightResult, rightHeight] = rec(root->right);
        
        return make_tuple(
            leftResult && rightResult && abs(leftHeight-rightHeight)<=1,
            1+max(leftHeight, rightHeight)
        );
    }
};