#include <iostream>
#include <string>
using namespace std;


void print(int** a, int m, int n){
	for (int i = 0; i < m; i++){
		for (int j = 0; j < n; j++){
			cout << a[i][j] << " ";
		}
		cout << endl;
	}
}

int main(){
	int m, n;
	cin >> m >> n;
	int ** data = new int*[m];
	int** barrier = new int*[m];
	for (int i = 0; i < m; i++){
		data[i] = new int[n];
		barrier[i] = new int[n];
	}

	for (int i = 0; i < m; i++){
		for (int j = 0; j < n; j++){
			cin >> data[i][j];
			barrier[i][j] = 0;
		}
	}


	int index;
	cin >> index;

	barrier[0][0] = 1;
	int x = 0, y = 0;
	int direction = 0; //0 right, 1 down, 2left,3 up
	for (int count = 0; count < index; count++){
		switch (direction){
		case 0:
			if (y == n - 1 || barrier[x][y+1] == 1){// bounce into the barrier
				direction = 1;
				x++;
			}
			else{
				y++;
			}
			break;
		case 1:
			if (x == m - 1 || barrier[x+1][y] == 1){
				direction = 2;
				y--;
			}
			else{
				x++;
			}
			break;
		case 2:
			if (y == 0 || barrier[x][y-1] == 1){
				direction = 3;
				x--;
			}
			else{
				y--;
			}
			break;
		case 3:
			if (x == 0 || barrier[x-1][y] == 1){
				direction = 0;
				y++;
			}
			else{
				x--;
			}
		}
		barrier[x][y] = 1;
	}



	cout << data[x][y];

}