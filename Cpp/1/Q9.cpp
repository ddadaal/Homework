// Q7.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include <math.h>

using namespace std;



bool isPrime(int num)
{
	if (num == 1){
		return false;
	}
	if (num == 2){
		return true;
	}
	if (num % 2 == 0)
	{
		return false;
	}
	for (int i = 3; i <= sqrt(num); i += 2)
	{
		if (num%i == 0)
		{
			return false;
		}
	}
	return true;
}

int main()
{
	int start, end;
	cin >> start >> end;
	int result = 0;
	for (int i = start; i <= end; i++)
	{
		if (isPrime(i))
		{
			result += i;
		}
	}
	cout << result;

}
