#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>

const int TIME = 1001;

using namespace std;


int maxTime;

int searchMax(int now, int startIndex, vector<int>& cache, vector<pair<int, int>>& movies) {
	if (cache[now] != -1) {
		return cache[now];
	}

	int res = 0;


	// find the movies that start from this time
	int start = startIndex;
	for (; start < movies.size(); start++) {
		if (movies[start].first >= now) {
			break;
		}
	}
	// find the min end time
	for (; start < movies.size(); start++) {
		res = max(res, 1 + searchMax(movies[start].second, start, cache, movies));
	}

	return cache[now] = res;

}

int main() {

	maxTime = 0;


	int n;
	cin >> n;

	while (n) {

		vector<int> cache(TIME, -1);

		vector<pair<int, int>> movies;

		for (int i = 0; i < n; i++) {
			int s, e;
			cin >> s >> e;
			maxTime = max(e, maxTime);
			movies.push_back(make_pair(s, e));
		}


		sort(movies.begin(), movies.end(), [](pair<int, int>& a, pair<int, int>& b) {
			return a.first < b.first;
			});

		cout << searchMax(0, 0, cache, movies) << endl;
		cin >> n;
		
	}





}

