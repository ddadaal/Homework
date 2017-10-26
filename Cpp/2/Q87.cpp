#include <iostream>
#include <string>
#include <stack>
#include <map>

using namespace std;

map<pair<int, int>, int> cache;

int traverse(int matrix[][100],int n, int x, int y){
	decltype(cache.begin()) it = cache.find(make_pair(x, y));
	if (it != cache.end()){
		return it->second;
	}
	if (x == n){
		cache.insert(make_pair(make_pair(x, y), matrix[x - 1][y - 1]));
		return matrix[x-1][y-1];
	}
	else{
		int left = matrix[x-1][y-1] + traverse(matrix,n,x + 1,y);
		int right = matrix[x-1][y-1] + traverse(matrix, n, x + 1, y+1);
		int min= left > right ? right : left;
		cache.insert(make_pair(make_pair(x, y), min));
		return min;
	}
}

int main(){
	int n;
	cin >> n;
	int matrix[100][100];

	for (int i = 1; i <= n; i++){
		for (int j = 0; j < i; j++){
			cin >> matrix[i - 1][j];
		}
	}


	cout << traverse(matrix, n, 1, 1);
	return 0;

	
}