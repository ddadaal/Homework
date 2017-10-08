#include <iostream>
#include <string>
using namespace std;

long long splitTop3(long long r)
{
	unsigned long long offset = 0x8000000000000000;
	int num = 0;
	while (!(r&offset))
	{
		offset >>= 1;
		num++;
	}
	offset += (offset >> 1) + (offset >> 2);
	r &= offset;
	r >>= 61 - num;
	return r;
}

int main()
{
	int n;
	cin >> n;

	long long result = 0;
	long long r = 0;

	for (int i = 0; i < n; i++)
	{
		cin >> r;
		result += splitTop3(r);
	}
	cout << result;
}

