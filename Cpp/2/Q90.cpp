#include <iostream>
#include <string>

using namespace std;

class Node{
public:
	int value;
	Node* next;
	Node(int value = 0) : value(value), next(nullptr) {}
};

class List {
public:
	Node* start;
	Node* end;
	List() : start(new Node(0)){
		end = start;
	}
	void add(int value){
		end->next = new Node(value);
		end = end->next;
		start->value++;
	}
	void reverse(){
		if (this->start->value <= 1){ return; }
		Node* current, *previous, *next;
		current = start->next;
		end = current;
		next = current->next;
		previous = start->next;

		current->next = nullptr;
		current = next;
		next = next->next;

		while (next != nullptr){
			current->next = previous;
			previous = current;
			current = next;
			next = next->next;
		}

		current->next = previous;
		this->start->next = current;
	}
	void remove(int value){
		Node* previous = start, *current = start->next;
		while (current != nullptr){
			if (current->value == value){
				previous->next = current->next;
				current = current->next;
				start->value--;
			}
			else{
				previous = current;
				current = current->next;
			}
		}

	}

	void removeRepeat(int value){
		Node* current = start->next;
		int count = 0;
		while (current != nullptr){
			if (current->value == value) count++;
			current = current->next;
		}
		current = start->next;
		Node* previous = start;
		while (count > 1){
			if (current->value == value){
				previous->next = current->next;
				current = current->next;
				start->value--;
				count--;
			}
			else{
				previous = current;
				current = current->next;
			}
		}
	}

};

void executeCommand(List* list, string command){
	if (command == "ADD"){
		int n;
		cin >> n;
		list->add(n);
	}
	else if (command == "SIZE"){
		cout << list->start->value << endl;
	}
	else if (command == "PRINT"){
		Node* c = list->start->next;
		if (list->start->value == 0) {
			cout << "NULL" << endl;
			return;
		}
		for (int i = 0; i < list->start->value; i++){
			cout << c->value;
			if (i == list->start->value - 1){
				cout << endl;
			}
			else{
				cout << " ";
			}
			c = c->next;
		}
	}
	else if (command == "REVERSE"){
		list->reverse();
	}
	else if (command == "REMOVEREPEAT"){
		int value;
		cin >> value;
		list->removeRepeat(value);
	}
	else if (command == "DEL"){
		int value;
		cin >> value;
		list->remove(value);
	}
}


int main() {
	List list;
	int n;
	cin >> n;
	string command;
	for (int i = 0; i < n; i++){
		cin >> command;
		executeCommand(&list, command);
	}
}