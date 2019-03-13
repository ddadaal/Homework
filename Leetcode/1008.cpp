#include "cppincludes.h"

class Solution {
public:
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        return build(preorder,0, preorder.size()-1);
    }
    
    TreeNode* build(vector<int>& preorder, int start, int end) {
        
        if (start >= preorder.size()) {
            return nullptr;
        }

        
        TreeNode* root = new TreeNode(preorder[start]);
         
        if (start == end) {
            return root;   
        }
        
        int mid = start+1;
        // find left
        for (;mid<=end;mid++) {
            if (preorder[mid]>root->val) {
                break;
            }
        }
        
        root->left = mid == start + 1 ? nullptr : build(preorder, start+1, mid-1);
        root->right = mid == end+1 ? nullptr : build(preorder, mid, end);
        return root;
    }
};