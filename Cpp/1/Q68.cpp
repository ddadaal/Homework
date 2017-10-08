#include <iostream>
#include <string>
using namespace std;

string s;

void execute(string command)
{
	if (command == "append")
	{
		string arg;
		cin >> arg;
		s.append(arg);
	}
	else if (command == "print")
	{
		cout << s << endl;
	}
	else if (command == "replace")
	{
		string arg1, arg2;
		cin >> arg1;
		cin >> arg2;
		string::size_type i;
		
		while ((i = s.find(arg1)) != string::npos)
		{
			s.replace(i, arg1.length(), arg2);
			i += arg2.length();
		}
	}
	else if (command == "reverse")
	{
		reverse(s.begin(), s.end());
	}
	else if (command == "changecase")
	{
		string d;
		cin >> d;
		if (d == "up")
		{
			for (char& c : s)
			{
				if (c <= 'z' && c >= 'a')
				{
					c += 'A' - 'a';
				}
			}
		}
		else
		{
			for (char& c : s)
			{
				if (c <= 'Z' && c >= 'A')
				{
					c -= 'A' - 'a';
				}
			}
		}
	}
	else if (command == "length")
	{
		cout << s.length() << endl;
	}
}

int main()
{
	string s;
	int n;
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> s;
		execute(s);
	}

}

