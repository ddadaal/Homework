#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <set>
#include <algorithm>

using namespace std;

struct Student {
public:
	string name;
	long long no;
	map<string, double> courses;
};

struct Output{
public:
	long long no;
	double mean;
	bool operator <(const Output& other) const{
		if (mean == other.mean){
			return no > other.no;
		}
		else{
			return mean > other.mean;
		}
	}
};


set<string> courses;
vector<Student*> students;
int courseNum = 0;

Student* findStudent(long long no){
	for (auto student : students){
		if (student->no == no){
			return student;
		}
	}
	return nullptr;
}

double calculateMean(Student* student){
	double totalScore = 0;
	for (auto c : student->courses){
		totalScore += c.second;
	}
	return totalScore / courseNum;
}

int main(){
	int n;
	cin >> n;
	for (int i = 0; i < n; i++){
		string name, courseName;
		long long no;
		double score;
		cin >> name >> no >> courseName >> score;
		courses.insert(courseName);
		auto student = findStudent(no);
		if (student != nullptr){
			map<string, double>::iterator it;
			if ((it = student->courses.find(courseName)) != student->courses.end()){
				it->second = score;
			}
			else{
				student->courses.insert(std::make_pair(courseName, score));
			}
		}
		else{
			Student* s = new Student();
			s->name = name;
			s->no = no;
			s->courses.insert(std::make_pair(courseName, score));
			students.push_back(s);
		}
	}

	courseNum = courses.size();

	double totalScore=0;
	int totalStudentNum = students.size();
	for (auto s : students){
		for (auto c : s->courses){
			totalScore += c.second;
		}
	}

	double totalMean = totalScore / totalStudentNum / courseNum;

	vector<Output> outputVector;


	for (auto s : students){
		double mean = calculateMean(s);
		if (mean <= totalMean){
			outputVector.push_back(Output{ s->no, mean });
		}
	}

	sort(outputVector.begin(), outputVector.end());

	int outputLength = outputVector.size();
	for (int outputIndex=0; outputIndex < outputLength; outputIndex++){
		cout << outputVector[outputIndex].no;
		if (outputIndex != outputLength-1){
			cout << endl;
		}
	}

	return 0;
	


}

