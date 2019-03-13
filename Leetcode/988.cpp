#include "cppincludes.h"

class Solution {
public:
    string smallestFromLeaf(TreeNode* root, string s = "") {
        if (!root) {
            return "|";
        }
          s = string(1, 'a' + root->val) + s;

        
        if (root->left == root->right) {
            // both null, is leaf
            return s;
        } else {
            return min(smallestFromLeaf(root->left, s), smallestFromLeaf(root->right, s));
        }
    }
    
};