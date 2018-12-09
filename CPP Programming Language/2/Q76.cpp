#include <iostream>
#include <string>
using namespace std;

class Node {
public:
	int value;
	Node* next;
	Node(int value = 0) : value(value), next(next) {}
	void toString(){
		Node* current = this;
		while (current != nullptr){
			cout << current->value << ">";
			current = current->next;
		}
		cout << "null";
	}
};

void normalize(Node*& node){
	while (node->value == 0){
		node = node->next;
	}
}

Node* reverse(Node* node){
	Node* current = node;
	Node* next = node->next;
	if (next == nullptr) return current;
	Node* previous = nullptr;

	current->next = nullptr;
	previous = current;
	current = next;
	next = current->next;

	while (next != nullptr){
		current->next = previous;
		previous = current;
		current = next;
		next = current->next;
	}
	current->next = previous;
	return current;
}

Node* addTwoNumbers(Node* node1, Node* node2){
	Node* r = new Node(0), *re = r;
	int c = 0;
	Node* c1 = node1, *c2 = node2;

	while ((c1 != nullptr) || (c2 != nullptr)){
		int result = c;
		if (c1 != nullptr){
			result += c1->value;
			c1 = c1->next;
		}
		if (c2 != nullptr){
			result += c2->value;
			c2 = c2->next;
		}

		c = result / 10;
		result %= 10;

		r->next = new Node(result);
		r = r->next;
	}
	if (c == 1){
		r->next = new Node(1);
	}
	return re->next;
}

int main(){
	Node* node1 = new Node(), *n1 = node1;

	Node* node2 = new Node(), *n2 = node2;

	char c;
	
	string s;

	getline(cin, s);

	for (int i = 0; i < s.length(); i+=2){
		n1->value = s[i] - '0';
		if (s[i + 2] == 'n'){
			break;
		}
		n1->next = new Node();
		n1 = n1->next;

	}
	getline(cin, s);
	for (int i = 0; i < s.length(); i += 2){
		n2->value = s[i] - '0';
		if (s[i + 2] == 'n'){
			break;
		}
		n2->next = new Node();
		n2 = n2->next;

	}

	normalize(node1);
	normalize(node2);

	node1 = reverse(node1);
	node2 = reverse(node2);

	Node* result = addTwoNumbers(node1, node2);

	result = reverse(result);

	result->toString();







}