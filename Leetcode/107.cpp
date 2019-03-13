#include "cppincludes.h"

class Solution {
  public:
    vector<vector<int>> levelOrderBottom(TreeNode *root) {
        vector<vector<int>> result;
        vector<int> level;

        queue<TreeNode *> q;
        q.push(root);

        while (!q.empty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                auto node = q.front();
                q.pop();
                if (node) {
                    level.push_back(node->val);
                    q.push(node->left);
                    q.push(node->right);
                }
            }
            if (level.size() > 0) {
                result.push_back(level);
                level.clear();
            }
        }

        reverse(result.begin(), result.end());
        return result;
    }
};