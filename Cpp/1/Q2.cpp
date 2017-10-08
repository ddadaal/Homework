// Q2.cpp : Defines the entry point for the console application.
//

#include <iostream>
using namespace std;

int main()
{
	long long a, b;
	cin >> a >> b;
	long long result = 1;
	for (int i = 0; i < b; i++)
	{
		result *= a;
	}
	cout << result;
	return 0;
}

