#include "cppincludes.h"

class Solution {
public:
  vector<int> preorderTraversal(TreeNode *root) {
    vector<int> result;
    stack<TreeNode *> nodeStack;
    nodeStack.push(root);

    while (!nodeStack.empty()) {
      auto node = nodeStack.top();
      nodeStack.pop();
      if (node) {
        result.push_back(node->val);
        nodeStack.push(node->right);
        nodeStack.push(node->left);
      }
    }

    return result;
  }
};