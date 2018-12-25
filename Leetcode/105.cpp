#include "cppincludes.h"

class Solution {
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        return buildTree(
            preorder, 0,
            inorder, 0,
            preorder.size()
        );
    }

    TreeNode* buildTree(vector<int>& preorder, int pos, vector<int>& inorder, int ios, int length) {
        if (length == 0) {
            return nullptr;
        }
        auto root = new TreeNode(preorder[pos]);

        if (length == 1) {
            return root;
        }

        int leftLength = 0;
        while (leftLength < length) {
            if (inorder[leftLength + ios] == root->val) {
                break;
            } else {
                leftLength++;
            }
        }

        auto left = buildTree(
            preorder, pos+1,
            inorder, ios,
            leftLength
        );

        auto right = buildTree(
            preorder, pos+1+leftLength, 
            inorder, ios+1+leftLength,
            length - leftLength - 1
        );

        root->left = left;
        root->right = right;

        return root;
    }
};