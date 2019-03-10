#include "cppincludes.h"

class Solution {
  public:
    int countNodes(TreeNode *root) {
        if (!root) {
            return 0;
        }
        int ans = 1;
        ans += countNodes(root->left);
        ans += countNodes(root->right);
        return ans;
    }
};