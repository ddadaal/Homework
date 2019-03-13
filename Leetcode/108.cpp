#include "cppincludes.h"

class Solution {
public:
    TreeNode* sortedArrayToBST(vector<int>& nums) {
       return rec(nums, 0, nums.size()-1);
    }
    
    TreeNode* rec(vector<int>& nums, int left, int right) {
        if (left > right) {
            return nullptr;
        }
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        int mid = (left+right)/2;
        TreeNode* root = new TreeNode(nums[mid]);
        root->left = rec(nums, left, mid-1);
        root->right = rec(nums, mid+1, right);
        return root;
    }
};