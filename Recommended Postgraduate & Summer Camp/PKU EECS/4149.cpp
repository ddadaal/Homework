#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <climits>

// TLE

using namespace std;

struct Course {
	string name;
	int ddl;
	int time;
	bool completed = false;
};

pair<int, vector<int>> solve(vector<Course>& courses, vector<int>& completeSeq, int now, int score) {
	if (courses.size() == completeSeq.size()) {
		return make_pair(score, vector<int>(completeSeq));
	}


	int minResult = INT_MAX;
	vector<int> minSeq;

	for (int i = 0; i < courses.size(); i++) {
		if (courses[i].completed) {
			continue;
		}

		// try complete this one
		courses[i].completed = true;
		int nowNow = now + courses[i].time;
		int nowScore = score + max(0, nowNow - courses[i].ddl);
		completeSeq.push_back(i);

		// continue
		auto r = solve(courses, completeSeq, nowNow, nowScore);
		nowScore = r.first;

		// record if min
		if (nowScore < minResult) {
			minResult = nowScore;
			minSeq = r.second;
		}

		// restore
		courses[i].completed = false;
		completeSeq.pop_back();
		

	}	

	return make_pair(minResult, minSeq);

	

	
}

int main() {
	int num;
	cin >> num;

	while (num-- > 0) {
		int courseNum;
		cin >> courseNum;
		vector<Course> courses;

		for (int i = 0; i < courseNum; i++) {
			Course c;
			cin >> c.name >> c.ddl >> c.time;
			courses.push_back(c);
		}

		vector<int> seq;

		auto r = solve(courses, seq, 0, 0);

		cout << r.first << endl;
		for (int i : r.second) {
			cout << courses[i].name << endl;
		}
	}
}