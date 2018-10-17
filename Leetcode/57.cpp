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

	vector<Interval> merge(vector<Interval>& intervals) {
		vector<Interval> merged;
		int index = 0;
		for (auto interval : intervals) {
			if (merged.size() == 0) {
				merged.push_back(interval);
				continue;
			}
			if (interval.start <= merged[index].end) {
				merged[index].end = max(merged[index].end, interval.end);
			}
			else {
				merged.push_back(interval);
				index++;
			}

		}
		return merged;
	}

public:
	// 1,2,4,5,7 3 8
	vector<Interval> insert(vector<Interval>& intervals, Interval newInterval) {
		int len = intervals.size();

		if (len == 0) {
			intervals.push_back(newInterval);
			return intervals;
		}

		int left = 0, right = len - 1;
		int midIndex = (left + right) / 2;
		Interval mid = intervals[midIndex];

		while (left < right) {
			midIndex = (left + right) / 2;
			mid = intervals[midIndex];
			if (mid.start == newInterval.start) {
				break;
			}
			else if (mid.start < newInterval.start) {
				left = midIndex + 1;
			}
			else {
				right = midIndex - 1;
			}
		}
		if (newInterval.start >= mid.start) {
			midIndex++;
		}
		intervals.insert(intervals.begin() + midIndex, newInterval);
		return merge(intervals);
	}
};