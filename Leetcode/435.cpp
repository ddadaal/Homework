#include "cppincludes.h"

struct Interval {
  int start;
  int end;
  Interval() : start(0), end(0) {}
  Interval(int s, int e) : start(s), end(e) {}
};

class Solution {
public:
  int eraseOverlapIntervals(vector<Interval> &intervals) {
    if (intervals.size() <= 1) {
      return 0;
    }

    sort(intervals.begin(), intervals.end(),
         [](Interval &a, Interval &b) { return a.start < b.start; });

    int ans = 0;
    int pre = 0;
    for (int i = 1; i < intervals.size(); i++) {
      if (intervals[i].start<intervals[pre].end) {
        ans++;
        if (intervals[i].end>intervals[pre].end) {
          pre = i;
        }
      } else {
        pre = i;
      }
    }
    return ans;
  }
};