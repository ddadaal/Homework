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

};

TreeNode* find(TreeNode* node, string value){
	if (node == nullptr){
		return nullptr;
	}
	else if (node->value == value){
		return node;
	}
	else{
		auto left = find(node->left, value);
		if (left == nullptr){
			return find(node->right, value);
		}
		else{
			return left;
		}
	}
}

int findAndCount(TreeNode* node, string value){
	if (node == nullptr){
		return -1;
	}
	else if (node->value == value){
		return 0;
	}
	else{
		auto left = findAndCount(node->left, value);
		if (left == -1){
			auto right = findAndCount(node->right, value);
			if (right == -1){
				return -1;
			}
			return 1 + right;
		}
		else{
			return 1 + left;
		}
	}
}

int main(){
	int num;
	cin >> num;
	TreeNode** nodes = new TreeNode*[num];
	string s;
	

	for (int i = 0; i < num; i++){
		cin >> s;
		nodes[i] = 
			s == "NULL" ? nullptr : new TreeNode(s);
	}

	int i = 0, j = 1;

	TreeNode* root = nodes[0];
	while (j < num-1){
		if (nodes[i] != nullptr){
			nodes[i]->left = nodes[j];
			nodes[i]->right = nodes[j+1];
		}
		i++;
		j += 2;
	}
	

	string node1, node2;
	cin >> node1, cin >> node2;
	TreeNode* a = find(root, node1);
	if (a == nullptr){
		cout << -1;
		return 0;
	}

	int result = findAndCount(a, node2);
	if (result != -1){
		cout << result;
		return 0;
	}

	TreeNode* b = find(root, node2);
	cout << findAndCount(b, node1);



}