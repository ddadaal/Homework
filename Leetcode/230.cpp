#include "cppincludes.h"

class Solution {
  public:
    int kthSmallest(TreeNode *root, int k) {
        
        stack<TreeNode*> s;
        s.push(root);

        int curr = 0;
        TreeNode* node = root;
        while (curr < k){
            while (node) {
                s.push(node);
                node = node->left;
            }
            
            node = s.top();
            s.pop();
            curr++;
            if (curr == k) {
                return node->val;
            } else {
                node = node->right;
            }
        }

        return 0;
    }
};