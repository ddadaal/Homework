#include <iostream>
using namespace std;

int main()
{
	int n;
	cin >> n;

	int direction = 0; // 0: upwards, 1:downwards

	int a = 1, b = 1;

	while (n > 1)
	{
		if (direction == 1)
		{
			if (b == 1)
			{
				a++;
				direction--;
			}
			else
			{
				a++;
				b--;
			}
		}
		else
		{
			if (a == 1)
			{
				b++;
				direction++;
			}
			else
			{
				a--;
				b++;
			}
		}
		n--;
	}
	printf("%d/%d",a,b);
	return 0;
	
}