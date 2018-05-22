#include <iostream>
using namespace std;

int main()
{
	long long n;
	cin >> n;

	long long a=0, b=1;

	int flag = 0;

	for (int i = 0; i < n; i++)
	{
		if (flag==0)
		{
			a += b;
			flag++;
		}
		else
		{
			b += a;
			flag--;
		}
	}
	cout<< (flag == 0 ? a : b);
	return 0;
	
}

