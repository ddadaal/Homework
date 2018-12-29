#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;

struct Interval {
	int start;
	int end;
	Interval() : start(0), end(0) {}
	Interval(int s, int e) : start(s), end(e) {}
	
};

class Solution {
public:
	vector<Interval> merge(vector<Interval>& intervals) {
		sort(intervals.begin(), intervals.end(), [](Interval a, Interval b) {
			return a.start < b.start;
		});
		vector<Interval> merged;
		for (auto interval : intervals) {
			if (merged.size() == 0) {
				merged.push_back(interval);
				continue;
			}
			if (interval.start <= merged.back().end) {
				merged.back().end = max(merged.back().end, interval.end);
			}
			else {
				merged.push_back(interval);
			}
			
		}
		return merged;
	}
};