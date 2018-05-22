#include <iostream>
#include <string>
using namespace std;

long long to(string command)
{
	long long result = 0;
	bool neg = command[0] == '-';

	for (auto a : command)
	{
		if (a == '-') continue;
		int t = a >= 'A' ? (a - 'A' + 10) : (a - '0');
		result = result * 13 + t;
	}
	return neg? -result : result;
}

string from(long long value)
{
	bool neg = false;
	if (value < 0)
	{
		neg = true;
		value = -value;
	}

	string result = "";
	while (value > 0)
	{
		int l = value % 13;
		if (l >= 10)
		{
			result += (char)(l - 10 + 'A');
		}
		else
		{
			result += (char)(l + '0');
		}
		value /= 13;
	}
	reverse(result.begin(), result.end());
	return neg ? '-'+result : result;
}

int main()
{
	char buffer[30],a[30],b[30];
	cin >> buffer;
	cin >> a;
	cin >> b;

	string sa(a), sb(b);
	
	if (!strcmp(buffer, "add"))
	{
		cout << from(to(sa) + to(sb));
	}
	else if (!strcmp(buffer, "sub"))
	{
		cout << from(to(sa) - to(sb));
	}
	else if (!strcmp(buffer, "mul"))
	{
		cout << from((to(sa)*to(sb)));
	}

	

}