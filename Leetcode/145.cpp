#include "cppincludes.h"

class Solution {
public:
    vector<int> postorderTraversal(TreeNode* root) {
        vector<int> result;
        stack<TreeNode*> nodeStack;
        nodeStack.push(root);

        while (!nodeStack.empty()) {
            auto node = nodeStack.top();
            nodeStack.pop();

            if (node) {
                result.push_back(node->val);
                nodeStack.push(node->left);
                nodeStack.push(node->right);
            }
        }

        // the result order is node, node->right, node->left. 
        // reverse it to get postorder traversal
        reverse(result.begin(), result.end());
        return result;
    }
};