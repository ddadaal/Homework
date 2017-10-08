#include <iostream>
#include <string>
using namespace std;

int main()
{
	string s;
	cin >> s;

	int result=0;

	for (unsigned int i = 0; i < s.length(); i++)
	{
		char c = s[i];

		if (c == 'V')
		{
			result += 5;
		}
		else if (c == 'M')
		{
			result += 1000;
		}
		else if (c == 'D')
		{
			result += 500;
		}
		else if (c == 'L')
		{
			result += 50;
		}
		else if (c == 'I')
		{
			char p = i < s.length() - 1 ? s[i + 1] : 0;
			if (p != 0 && p != 'I')
			{
				result -= 1;
			}
			else
			{
				result += 1;
			}
		}
		else if (c == 'X')
		{
			char p = i < s.length() - 1 ? s[i + 1] : 0;
			if (p != 0 && (p == 'C' || p=='M' || p=='L' || p=='D'))
			{
				result -= 10;
			}
			else
			{
				result += 10;
			}
		}
		else if (c == 'C')
		{
			char p = i < s.length() - 1 ? s[i + 1] : 0;
			if (p != 0 && (p == 'M' || p == 'D'))
			{
				result -= 100;
			}
			else
			{
				result += 100;
			}
		}


	}
	cout << result;
}
