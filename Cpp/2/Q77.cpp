#include <iostream>
#include <string>
#include <stack>
#define OUTPUT(r) output(r); return 0;
using namespace std;

//when function starts, i should be at one behind the !
//when function ends, i is at the ending >
bool validateComment(const string& str, int& i){

	if (str[i++] != '-' || str[i++] != '-') return false;
	while (!(str[i] == '-' && str[i+1]=='-' && str[i+2]=='>')){
		i++;
	}
	i += 2;
	return true;

}

// when function starts, i should be at one behind the leading <
// when function ends, i is one at the ending >
bool validateTagName(const string& str, int& i, string& start, string& end){
	string tagName;
	string& written = str[i] == '/' ? (i++, start = "invalid", end = "") : (end = "invalid", start = "");


	int count = 0;
	while (count <= 9){
		if (str[i] == '>'){
			return count > 0;
		}
		if (str[i]<'A' || str[i]>'Z'){
			return false;
		}
		written += str[i];
		count++;
		i++;
	}
	return false;
}

void output(bool result){
	if (result){
		cout << "True";
	}
	else{
		cout << "False";
	}
}

int main(){
	stack<string> stack;
	string content;
	getline(cin, content);
	for (int i = 0; i < content.length(); i++){
		char c = content[i];
		if (c == '<'){
			i++;
			if (content[i] == '!'){
				i++;
				if (!validateComment(content, i)){
					OUTPUT(false);
				}
			}
			else{
				string start, end;
				if (!validateTagName(content, i, start, end)){
					OUTPUT(false);
				}
				else{
					if (start != "invalid"){
						stack.push(start);
					}
					else{
						if (stack.top() != end){
							OUTPUT(false);
						}
						else{
							stack.pop();
						}
					}
				}
			}
		}

	}
	OUTPUT(stack.empty());
}