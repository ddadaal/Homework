#include "cppincludes.h"

class Solution {
public:
    void flatten(TreeNode* root) {
      if (root == nullptr) {
        return;
      }
      if (root->left != nullptr) {

        flatten(root->left);
        auto leftMost = root->left;
        while (leftMost->right != nullptr) {
          leftMost = leftMost->right;
        }

        auto right = root->right;
        if (right != nullptr) {
          flatten(root->right);
          leftMost->right = root->right;
        }
        root->right = root->left;
        root->left = nullptr;
      } else {
          flatten(root->right);
      }
    }
};