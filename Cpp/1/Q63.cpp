#include <iostream>
using namespace std;




void add(int* a, int length, int n)
{
	for (int i = 0; i < length; i++)
	{
		a[i] += n;
	}
}
void sub(int* a, int length, int n)
{
	for (int i = 0; i < length; i++)
	{
		a[i] -= n;
	}
}
void mul(int* a, int length, int n)
{
	for (int i = 0; i < length; i++)
	{
		a[i] *= n;
	}
}
void pow(int* a, int length, int n)
{
	for (int i = 0; i < length; i++)
	{
		int base = a[i];
		for (int j = 1; j < n; j++)
		{
			a[i] *= base;
		}
	}
}
void sqa(int* a, int length)
{
	long long result = 0;
	for (int i = 0; i < length; i++)
	{
		result += a[i] * a[i];
	}
	cout << result<<endl;
}
void print(int* a, int length)
{
	for (int i = 0; i < length; i++)
	{
		cout << a[i];
		if (i != length - 1)
		{
			cout << " ";
		}
	}
	cout << endl;
}

int main()
{
	int length;
	cin >> length;
	int * a = new int[length];



	for (int i = 0; i < length; i++)
	{
		cin >> a[i];
	}

	int n;
	cin >> n;
	char buffer[20];

	for (int i = 0; i < n; i++)
	{

		string command;
		cin >> buffer;
		command = buffer;

		int arg =0;

		if (command == "add")
		{
			cin >> arg;
			add(a, length, arg);
		}
		else if (command == "sub")
		{
			cin >> arg;
			sub(a, length, arg);
		}
		else if (command == "mul")
		{
			cin >> arg;
			mul(a, length, arg);
		}
		else if (command == "pow")
		{
			cin >> arg;
			pow(a, length, arg);
		}
		else if (command == "print")
		{
			print(a, length);
		}
		else if (command == "sqa")
		{
			sqa(a, length);
		}

		
		
	}



}
