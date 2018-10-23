#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

class Solution {
	typedef map<pair<int, int>, int> Cache;
public:
	int minPathSum(vector<vector<int>>& grid) {
		int n = grid.size();
		int m = grid[0].size();

		Cache cache;
		cache[pair<int, int>(m, n)] = grid[n - 1][m - 1];
		return rec(1, 1, m, n, grid, cache);
		
	}
	int rec(int cm, int cn, int m, int n, vector<vector<int>>& grid, Cache& cache) {
		pair<int, int> p(cm, cn);
		if (cache.find(p) != cache.end()) {
			return cache[p];
		}

		int currentMin = 10000;
		if (cm <= m && cn <= n) {
			int value = grid[cn - 1][cm - 1];
			if (cm <= m) {
				currentMin = min(currentMin, value + rec(cm + 1, cn, m, n, grid, cache));
			}
			if (cn <= n) {
				currentMin = min(currentMin, value + rec(cm, cn + 1, m, n, grid, cache));
			}
		}

		cache[p] = currentMin;
		return currentMin;
	}
};