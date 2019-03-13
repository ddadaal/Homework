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
    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        return rec(nums, 0, nums.size()-1);
    }
    
    TreeNode* rec(vector<int>& nums, int left, int right) {
        
        if (left > right) {
            return nullptr;
        }
        
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        
        int maxIndex = left;
        for (int i=left+1;i<=right;i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        auto root = new TreeNode(nums[maxIndex]);
        root->left = rec(nums, left, maxIndex-1);
        root->right = rec(nums, maxIndex+1, right);
        return root;
        
    }
};