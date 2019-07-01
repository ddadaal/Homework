#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <climits>
#include <cmath>

using namespace std;

int main() {
	int num;
	cin >> num;

	int count = 0;
	int i = 2;

	while (num > 0 && i <= num) {
		if (num % i == 0) {
			count++;
			num /= i;
		}
		else {
			if (count != 0) {
				cout << i;
				if (count > 1) {
					cout << '^' << count;
				}
				cout << "*";
			}
			count = 0;
			i++;

		}
	}

	if (count != 0) {
		cout << i;
		if (count > 1) {
			cout << '^' << count;
		}
	}


}