#include <iostream>
#include <string>
using namespace std;



bool is(string str, int sp, int ep)
{
	while (sp < ep)
	{
		if (str[sp] != str[ep])
		{
			return false;
		}
		sp++;
		ep--;
	}
	return true;

}

int main()
{
	int n;
	cin >> n;


	string str;
	cin >> str;

	int cm = 1;

	for (int sp=0; sp < str.length()-1; sp++)
	{
		for (int ep=sp+1; ep < str.length(); ep++)
		{
			if (str[sp]==str[ep] && is(str, sp, ep))
			{
				if (cm < ep - sp+1)
				{
					cm = ep - sp+1;
				}
			}
		}
	}
	cout << cm;



}


