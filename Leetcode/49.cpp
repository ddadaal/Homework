#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

class Solution {
public:
	vector<vector<string>> groupAnagrams(vector<string>& strs) {
		map<string, vector<string>*> map;
		for (auto s : strs) {
			string sorted = s;
			sort(sorted.begin(), sorted.end());
			if (map.find(sorted) == map.end()) {
				vector<string>* l = new vector<string>();
				l->push_back(s);
				map[sorted] = l;
			}
			else {
				auto v = map[sorted];
				v->push_back(s);
			}
		}
		vector<vector<string>> result;
		for (auto& entry : map) {
			result.push_back(*entry.second);
		}
		return result;
	}
};