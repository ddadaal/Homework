#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <climits>
#include <cmath>

using namespace std;

int main() {
	int N;
	cin >> N;

	vector<vector<int>> v(N, vector<int>(N, 0));


	int area = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> v[i][j];
			if (v[i][j] <= 50) {

				area++;
			}
		}
	}

	int border = 0;

	for (int x = 0; x < N; x++) {
		for (int y = 0; y < N; y++) {
			if (v[x][y]<= 50) {
				if (x == 0 || x == N - 1 || y == 0 || y == N - 1
					|| (x > 0 && v[x - 1][y] > 50)
					|| (x < N - 1 && v[x + 1][y] > 50)
					|| (y > 0 && v[x][y - 1] > 50)
					|| (y < N - 1 && v[x][y + 1] > 50)
					) {
					border += 1;
				}
			}
		}
	}


	cout << area << " " << border;

}