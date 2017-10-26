#include <iostream>
#include <string>
using namespace std;

void executeCommand(string command, int*& array, int length){
	if (command == "print"){
		for (int i = 0; i < length; i++){
			cout << array[i];
			if (i != length - 1){
				cout << " ";
			}
		}
		cout << endl;
	}
	else if (command == "replace"){
		int src, replacement;
		cin >> src >> replacement;
		for (int i = 0; i < length; i++){
			if (array[i] == src) {
				array[i] = replacement;
			}
		}
	}
	else if (command == "move"){
		string direction;
		int src = 0;
		cin >> direction >> src;
		int* newArray = new int[length];
		int count = 0, np = 0;

		if (direction == "tail"){
			for (int i = 0; i < length; i++){
				if (array[i] != src){
					newArray[np] = array[i];
					np++;
				}
			}
			while (np < length){
				newArray[np] = src;
				np++;
			}
		}
		else{
			int np = length-1;
			for (int i = length-1; i >= 0; i--){
				if (array[i] != src) {
					newArray[np] = array[i];
					np--;
				}
			}
			while (np >= 0){
				newArray[np] = src;
				np--;
			}
		}
		array = newArray;
	}
	else if (command == "translate"){
		string direction;
		int num;
		cin >> direction >> num;
		int* newArray = new int[length];
		if (direction != "tail"){
			int np = 0, op = num;
			for (; np < length; np++, op++){
				newArray[np] = array[op%length];
			}
		}
		else{
			int np = num, op = 0;
			for (; op < length; np++, op++){
				newArray[np%length] = array[op];
			}
		}

		array = newArray;
	}

}

int main(){
	int length;
	cin >> length;
	int * a = new int[length];
	int num;
	for (int i = 0; i < length; i++){
		cin >> num;
		a[i] = num;
	}
	int count;
	cin >> count;
	string command;
	for (int i = 0; i < count; i++){
		cin >> command;
		executeCommand(command, a, length);
	}
	return 0;
}