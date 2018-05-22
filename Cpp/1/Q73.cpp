#include <iostream>
#include <string>
#include<stack>
using namespace std;

char firstLetter[] = { 0, 0, 'a', 'd', 'g', 'j', 'm', 'p', 't', 'w', 'w' + 4 };


void f(string s, int start, string previous)
{
	int c = s[start]-'0';
	if (start == s.length()-1)
	{
		for (char x = firstLetter[c]; x < firstLetter[c + 1]; x++)
		{
			cout <<previous<< x << endl;
		}
	}
	else
	{
		for (char x = firstLetter[c]; x < firstLetter[c + 1]; x++)
		{
			previous += x;
			f(s, start + 1, previous);
			previous = previous.substr(0, previous.length() - 1);
		}
	}
}

int main()
{

	string s;
	cin >> s;

	string previous;
	f(s, 0, previous);
}