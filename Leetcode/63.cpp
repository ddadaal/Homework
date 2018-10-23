#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

class Solution {
	typedef map<pair<int, int>, int> Cache;

public:
	int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
		Cache cache;

		int n = obstacleGrid.size();
		int m = obstacleGrid[0].size();

		cache[pair<int, int>(m, n)] = 1;

		return recur(1, 1, m,n,obstacleGrid, cache);
	}

	int recur(int cm, int cn, int m, int n, vector<vector<int>>& grid, Cache& cache) {
		if (cn <= n && cm <= m && grid[cn - 1][cm - 1] == 1) {
			return 0;
		}
		pair<int, int> p(cm, cn);
		if (cache.find(p) != cache.end()) {
			return cache[p];
		}

		int result = 0;

		if (cm <= m) {
			result += recur(cm + 1, cn, m, n, grid, cache);
		}
		if (cn <= n) {
			result += recur(cm, cn + 1, m, n, grid, cache);
		}
		cache[p] = result;
		return result;
	}
};