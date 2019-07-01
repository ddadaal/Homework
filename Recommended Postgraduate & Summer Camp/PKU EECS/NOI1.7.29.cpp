#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <climits>
#include <cmath>

using namespace std;

int getNumAt(string& s, int i) {
	return s[i] - '0';
}

int main() {
	string s;
	cin >> s;

	

	int recoNo =
		1 * getNumAt(s, 0) +
		2 * getNumAt(s, 2) +
		3 * getNumAt(s, 3) +
		4 * getNumAt(s, 4) +
		5 * getNumAt(s, 6) +
		6 * getNumAt(s, 7) +
		7 * getNumAt(s, 8) +
		8 * getNumAt(s, 9) +
		9 * getNumAt(s, 10);

	recoNo %= 11;

	char recoChar = recoNo == 10 ? 'X' : recoNo + '0';

	if (s[12] == recoChar) {
		cout << "Right";
	}
	else {
		s[12] = recoChar;
		cout << s;
	}


}