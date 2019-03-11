#include "cppincludes.h"

class Solution {
  public:
    TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q) {
        // make sure p < q
        if (p->val > q->val) {
            swap(p, q);
        }

        if ((root->val - p->val) * (root->val - q->val) <= 0) {
            return root;
        }

        if (root->val < p->val) {
            return lowestCommonAncestor(root->right, p, q);
        }

        if (root->val > q->val) {
            return lowestCommonAncestor(root->left, p, q);
        }

        return nullptr;
    }
};