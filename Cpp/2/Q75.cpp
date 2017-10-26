#include <iostream>
#include <string>
#include <queue>
using namespace std;

class TreeNode {
public:
	string value;
	TreeNode* left;
	TreeNode* right;
	TreeNode(string value = nullptr, TreeNode* left = nullptr, TreeNode* right = nullptr) : value(value), left(left), right(right) {}
	void inorder(TreeNode* node){
		if (node == nullptr){
			return;
		}
		inorder(node->left);
		cout << node->value << " ";
		inorder(node->right);
	}
};

int main(){
	queue<TreeNode*> q;

	string s1,s2;
	cin >> s1;
	TreeNode *  root = new TreeNode(s1);
	q.push(root);
	int i = 0;
	TreeNode * node1 = nullptr, * node2 = nullptr;
	while (cin >> s1, cin>>s2){
		node1 = s1 == "null"? nullptr: new TreeNode(s1);
		node2 = s2 == "null" ? nullptr :new TreeNode(s2);
		TreeNode * r = q.front();
		if (r != nullptr){
			r->left = node1;
			r->right = node2;
		}
		q.pop();
		q.push(node1);
		q.push(node2);
	}
	root->inorder(root);
	return 0;
}