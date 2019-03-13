/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

#include "cppincludes.h"
class Solution {
public:
    vector<vector<int>> levelOrder(Node* root) {
        vector<vector<int>> result;
        vector<int> level;
        
        queue<Node*> q;
        q.push(root);
        
        while (!q.empty()) {
            int len = q.size();
            for (int i=0;i<len;i++) {
                auto node = q.front();
                q.pop();
                
                if (node) {
                    level.push_back(node->val);
                    for(auto child: node->children) {
                        q.push(child);
                    }
                }
            }
            if (level.size() > 0) {
                       result.push_back(level);
            level.clear();
            }
     
        }
        
        return result;
    }
};