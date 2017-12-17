#include <iostream>
#include <string>
using namespace std;


class Student {
public:
	int id;
	string name;
	int age;
	double weight;
	double calculus;
	double english;
	double cpp;

	double gpa() {
		return (calculus * 4 + english * 4 + cpp * 3) / 11.0 / 20;
	}

	bool operator<(Student& other) {
		return this->gpa() < other.gpa();
	}

	bool operator==(Student& other) {
		return this->gpa() == other.gpa();
	}

	friend istream& operator>>(istream&, Student&);

};

istream& operator>>(istream& in, Student& student) {
	in >> student.id >> student.name >> student.age >> student.weight >> student.calculus >> student.english >> student.cpp;
	return in;
}

bool operator<(string& str1, string& str2) {
	int length1 = str1.length();
	int length2 = str2.length();

	for (int i = 0; i < length1; i++){
		if (i >= length2) {
			return false;
		}
		if (str1[i] < str2[i]) {
			return true;
		}
		else if (str1[i] > str2[i]){
			return false;
		}
	}
	if (length1 == length2) {
		return false;
	}
	return true;

}

template <class T>
int compare(T o1, T o2) {
	if (o1 < o2){
		return -1;
	}
	else if (o1 == o2) {
		return 0;
	}
	else {
		return 1;
	}
}

int main() {
	Student s1, s2;
	cin >> s1 >> s2;

	cout << compare<string>(s1.name, s2.name) << " " 
		<< compare<int>(s1.age, s2.age) << " "
		<< compare<double>(s1.weight, s2.weight) << " " 
		<< compare<Student>(s1, s2);
}