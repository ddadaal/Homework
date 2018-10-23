#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;
class Solution {
public:
	int uniquePaths(int m, int n) {

		map<pair<int, int>, int> map;
		map[pair<int, int>(m, n)] = 1;

		return recur(1, 1, m, n, map);

	}

	int recur(int cm, int cn, int m, int n, map<pair<int, int>, int>& map) {
		pair<int, int> p(cm, cn);
		if (map.find(p) != map.end()) {
			return map[p];
		}

		int result = 0;
		if (cm <= m) {
			result += recur(cm + 1, cn, m, n, map);
		}
		if (cn <= n) {
			result += recur(cm, cn + 1, m, n, map);
		}
		map[p] = result;
		return result;
	}
};