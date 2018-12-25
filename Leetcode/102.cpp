#include "cppincludes.h"

class Solution
{
  public:
    vector<vector<int>> levelOrder(TreeNode *root)
    {

        vector<vector<int>> result;

        vector<int> *curr = new vector<int>();

        queue<TreeNode *> queue;
        bool nonnull = false;

        queue.push(root);
        queue.push(nullptr);
        while (true)
        {
            auto node = queue.front();
            queue.pop();

            if (node != nullptr)
            {
                nonnull = true;
                if (node->left)
                {
                    queue.push(node->left);
                }
                if (node->right)
                {
                    queue.push(node->right);
                }
                curr->push_back(node->val);
            }
            else
            {
                if (!nonnull)
                {
                    break;
                }
                queue.push(nullptr);
                result.push_back(*curr);
                curr = new vector<int>();
                nonnull = false;
            }
        }

        return result;
    }
};