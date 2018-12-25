#include "cppincludes.h"

class Solution {
public:
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        vector<vector<int>> result;
        bool nonnull = false;

        vector<int>* curr = new vector<int>();

        vector<TreeNode*> stackQueue;

        stackQueue.push_back(root);
        stackQueue.push_back(nullptr);

        int sqp = 0;
        bool leftToRight = true;

        while (true) {
            auto node = stackQueue[sqp];
            if (leftToRight) {
                if (node == nullptr) {
                    leftToRight = false;
                } else {
                }
            }    
        }

    }
};