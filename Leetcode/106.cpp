#include "cppincludes.h"

class Solution {
public:
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        return buildTree(
            inorder, 0,
            postorder, 0,
            inorder.size()
        );
    }

    TreeNode* buildTree(vector<int>& inorder, int ios, vector<int>& postorder, int pos, int length) {
        if (length == 0) {
            return nullptr;
        }
        auto root = new TreeNode(postorder[pos+length-1]);

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
            inorder, ios,
            postorder, pos,
            leftLength
        );

        auto right = buildTree(
            inorder, ios+1+leftLength, 
            postorder, pos+leftLength,
            length - leftLength - 1
        );

        root->left = left;
        root->right = right;

        return root;
    }
};