#include "cppincludes.h"

class Solution {
  public:
    vector<string> binaryTreePaths(TreeNode *root) {
        vector<string> result;
        if (!root) {
            return result;
        }
        vector<int> curr;
        go(root, curr, result);
        return result;
    }

    void go(TreeNode *root, vector<int> &curr, vector<string> &result) {
        if (!root->left && !root->right) {
            string s;
            for (int i : curr) {
                s += to_string(i);
                s += "->";
            }
            s += to_string(root->val);
            result.push_back(s);
            return;
        }

        curr.push_back(root->val);

        if (root->left) {
            go(root->left, curr, result);
        }
        if (root->right) {
            go(root->right, curr, result);
        }

        curr.pop_back();
    }
};