#include <iostream>
#include <string>
#include <math.h>
using namespace std;

int numbit(int length)
{
	int r = 0;

	while (pow(2, r)-r < length + 1)
	{
		r++;
	}

	return r;
}

int main()
{
	string old;
	cin >> old;

	int totalLength = old.length() + numbit(old.length());

	char* result = new char[totalLength+1];
	result[totalLength] = 0;


	int s = 0;
	for (int i = 0; i < totalLength; i++)
	{
		if (((i+1) & i) == 0)
		{
			result[i] = '?';
		}
		else
		{
			result[i] = old[s++];
		}

	}


	int bitNo=-1, power;
	int jylength = numbit(old.length());
	for (int l = 0; l < jylength; l++)
	{
		bitNo++;
		power = pow(2, bitNo);

		int bit = 0;
		int count = power;
		for (int i = power-1 ; i < totalLength; i++)
		{
			if (i != power-1)
			{
				bit ^= result[i] - '0';
			}
			count--;
			if (count == 0)
			{
				count = power;
				i += power;
			}
		}
		result[power - 1] = bit + '0';
	}
	string sr = result;
	cout << sr;
	cin >> sr;



}

