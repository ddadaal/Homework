#include "cppincludes.h"

class Solution {

    bool findPath(TreeNode *root, TreeNode *target, vector<TreeNode *> &path) {
        if (root == nullptr) { // nullptr到target肯定没有道路
            return false;
        }

        // 先加进去
        path.push_back(root);

        if (root ==
            target) { // root == target，找到了这条道路，并对父节点告知找到道路
            return true;
        }

        if (findPath(
                root->left, target,
                path)) { // 从左边走可以找到道路，对父节点告知可以从这一节点找到道路
            return true;
        }

        if (findPath(
                root->right, target,
                path)) { // 从右边走可以找到道路，对父节点告知可以从这一节点找到道路
            return true;
        }

        // 从左从右都不能找到道路，删除这个点
        path.pop_back();
        return false; // 找不到道路
    }

  public:
    TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *a, TreeNode *b) {
        vector<TreeNode *> pathToA{}, pathToB{};

        findPath(root, a, pathToA); // 找到从root到a的道路
        findPath(root, b, pathToB); // 找到从root到b的道路

        int aSize = pathToA.size(), bSize = pathToB.size();

        if (aSize == 0 || bSize == 0) { // 有一个点无法到达
            return nullptr;
        }

        for (int i = 1; i < aSize && i < bSize; i++) {
            if (pathToA[i] != pathToB[i]) {
                return pathToA[i - 1]; // 返回最后一个一样的节点
            }
        }

        // 到了这里说明一个path完全覆盖另外一个路径，返回短路径的最后一个元素
        return aSize > bSize ? pathToB.back() : pathToA.back();
    }
};