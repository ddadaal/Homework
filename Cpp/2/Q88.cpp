#include <iostream>
#include <string>
using namespace std;

const string seq = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";




int cmp(char a, char b){ //-1 a<b 0 a=b 1 a>b
	int i = 0;
	while (seq[i] != a && seq[i] != b) { i++;  }
	if (seq[i] == a && seq[i] == b) return 0;
	if (seq[i] == b) return 1; else return -1;
}

int cmp(string& a, string& b){
	int al = a.length(), bl = b.length();
	if (al != bl) return al - bl;
	for (int i = 0; i < al; i++){
		int r = cmp(a[i], b[i]);
		if (r != 0) return r;
	}
	return 0;

}

void sort(string* array, int length){
	for (int i = 0; i < length; i++){
		for (int j = i; j < length; j++){
			if (cmp(array[i], array[j])>0){
				string s = array[i];
				array[i] = array[j];
				array[j] = s;
			}
		}
	}
}

int main(){
	int n;
	cin >> n;

	string* array = new string[n];
	for (int i = 0; i < n; i++){
		cin >> array[i];
	}

	sort(array, n);

	for (int i = 0; i < n; i++){
		cout << array[i] << endl;

	}
	return 0;
	
	
}