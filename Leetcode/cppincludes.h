#include <vector>
#include <algorithm>
#include <cmath>
#include <string>
#include <queue>
#include <iostream>
#include <unordered_map>
#include <stack>
#include <unordered_set>

using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(NULL) {}
};

struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(nullptr) {}
	
};
