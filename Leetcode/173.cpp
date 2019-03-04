#include "cppincludes.h"

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class BSTIterator {
public:
    stack<TreeNode*> nodeStack;

    BSTIterator(TreeNode* root) {
        pushToLeftmost(root);
    }
    
    /** @return the next smallest number */
    int next() {
        auto node = nodeStack.top();
        nodeStack.pop();
        pushToLeftmost(node ->right);
        return node->val;
    }

    void pushToLeftmost(TreeNode* root) {
        while (root != nullptr) {
            nodeStack.push(root);
            root = root->left;
        }

    }
    
    /** @return whether we have a next smallest number */
    bool hasNext() {
        return !nodeStack.empty();
    }
};
