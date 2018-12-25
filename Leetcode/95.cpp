#include "cppincludes.h"

class Solution {
public:
    vector<TreeNode*> generateTrees(int n) {
        if (n == 0) {
            return vector<TreeNode*>();
        }
        return rec(1, n);
    }
    
    vector<TreeNode*> rec(int start, int end) {
        if (start > end) {
            return vector<TreeNode*> { nullptr };
        }
        vector<TreeNode*> result;
        for (int i=start;i<=end;i++) {
            
            // construct left trees
            auto leftTrees = rec(start, i-1);
            auto rightTrees = rec(i+1, end);
            
            // connect left and right trees
            for (auto leftNode: leftTrees) {
                for (auto rightNode: rightTrees) {
                    TreeNode* root = new TreeNode(i);
                    root->left = leftNode;
                    root->right = rightNode;
                    result.push_back(root);
                }

            }
            
        }
        return result;
    }
};