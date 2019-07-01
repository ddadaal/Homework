#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <climits>

using namespace std;


int main() {
	double c;
	cin >> c;

	while (c) {
		double current = 0.0;
		for (int i = 1;; i++) {
			current += 1.0 / (i + 1);
			if (current >= c) {
				cout << i << " card(s)" << endl;
				break;
			}
		}
		cin >> c;
	}
}