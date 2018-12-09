#include <iostream>
#include <string>
#include <vector>
#include <iomanip>
using namespace std;

struct Student
{
	Student(string name, string no, string course, double score) : name(name), no(no), course(course), score(score){}
	string name;
	string no;
	string course;
	double score;
};


int main()
{
	int sn;
	cin >> sn;
	
	Student** students = new Student*[sn];

	for (int i = 0; i < sn; i++)
	{
		string name, no, course;
		double score;
		cin >> name >> no >> course >> score;
		students[i] = new Student(name, no, course, score);
	}

	int cn;
	cin >> cn;

	string command;
	for (int i = 0; i < cn; i++)
	{
		cin >> command;
		if (command == "course")
		{
			string courseName;
			cin >> courseName;

			double total = 0;
			int time = 0;
			for (int j = 0; j < sn; j++)
			{
				Student* s = students[j];
				if (s->course == courseName)
				{
					total += s->score;
					time++;
				}
			}
			cout << courseName << " ";
			printf("%0.2f\n", total / time);
		}
		else
		{
			string studentName;
			cin >> studentName;
			double total = 0;
			int time = 0;
			for (int j = 0; j < sn; j++)
			{
				Student* s = students[j];
				if (s->name == studentName)
				{
					total += s->score;
					time++;
				}
			}
			cout << studentName << " ";
			printf("%0.2f\n", total / time);
		}
	}
	
}


