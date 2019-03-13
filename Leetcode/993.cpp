/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

#include "cppincludes.h"

class Solution {
  public:
    bool isCousins(TreeNode *root, int x, int y) {

        vector<TreeNode *> pathToX, pathToY;

        if (!findPath(root, x, pathToX) || !findPath(root, y, pathToY)) {
            return false;
        }

        if (pathToX.size() <= 1 || pathToY.size() <= 1) {
            return false;
        }

        return pathToX.size() == pathToY.size() &&
               pathToX[pathToX.size() - 2] != pathToY[pathToY.size() - 2];
    }

    bool findPath(TreeNode *root, int target, vector<TreeNode *> &path) {
        if (!root) {
            return false;
        }

        path.push_back(root);

        if (root->val == target) {
            return true;
        }

        if (findPath(root->left, target, path)) {
            return true;
        }

        if (findPath(root->right, target, path)) {
            return true;
        }

        path.pop_back();

        return false;
    }
};