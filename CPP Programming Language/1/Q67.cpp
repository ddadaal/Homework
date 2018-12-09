#include <iostream>
#include <string>
using namespace std;


int main()
{
	string content;
	getline(cin, content);

	int l = content.find_last_of(' ');

	string s = content.substr(content.find_last_of(' ')+1, content.length() - content.find_last_of(' ')-1);

	cout << s.length();




	

}