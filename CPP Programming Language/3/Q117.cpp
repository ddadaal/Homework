#include <iostream>
using namespace std;

enum Chess {
	X, O, N
};

enum Result {
	XWin, OWin, DRAW, NotFinished
};

class Board {
public:
	Chess last = Chess::N;
	Chess* board;
	int count = 0;
	int n,m;

	void initBoard() {
		cin >> n >> m;
		board = new Chess[n*n];
		for (int i = 0; i < n*n; i++){
			board[i] = Chess::N;
		}
	}

	Chess& get(int x, int y){
		return board[x*n + y];
	}

	void set(int x, int y, Chess last) {
		board[x*n + y] = last;
	}

	Result play() {
		count++;
		int x, y;
		cin >> x >> y;
		if (last == Chess::X || last == Chess::N){
			last = Chess::O;
		}
		else {
			last = Chess::X;
		}
		set(x, y, last);
		//printBoard();
		return judge();
	}

	bool isFull() {
		return count == n*n;
	}

	void printBoard() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cout << get(i, j) << " ";
			}
			cout << endl;
		}

	}

	Result judge() {
		//judge column
		for (int i = 0; i <= n-m; i++){
			for (int j = 0; j < n; j++){
				Chess& chess = get(i, j);
				if (chess == Chess::N) {
					continue;
				}
				bool isSame = true;
				for (int k = i + 1; k < i + m; k++){
					if (get(k, j) != chess) {
						isSame = false;
						break;
					}
				}
				if (isSame) {
					return chess == Chess::X ? Result::XWin : Result::OWin;
				}
			}
		}

		//judge row
		for (int i = 0; i < n; i++){
			for (int j = 0; j <= n-m; j++){
				Chess& chess = get(i, j);
				if (chess == Chess::N) {
					continue;
				}
				bool isSame = true;
				for (int k = j + 1; k <j + m; k++){
					if (get(i, k) != chess) {
						isSame = false;
						break;
					}
				}
				if (isSame) {
					return chess == Chess::X ? Result::XWin : Result::OWin;
				}
			}
		}

		//judge normal diag
		for (int i = 0; i <= n - m; i++) {
			for (int j = 0; j <= n - m; j++){
				Chess& chess = get(i, j);
				if (chess == Chess::N) {
					continue;
				}
				bool isSame = true;
				for (int k = 1; k < m; k++){
					if (get(i + k, j + k) != chess){
						isSame = false;
						break;
					}
				}
				if (isSame) {
					return chess == Chess::X ? Result::XWin : Result::OWin;
				}
			}
		}

		//judge abnormal diag
		for (int i = 0; i <= n - m; i++) {
			for (int j = 0; j <= n - m; j++){
				Chess& chess = get(n-i, j);
				if (chess == Chess::N) {
					continue;
				}
				bool isSame = true;
				for (int k = 1; k < m; k++){
					if (get(n-(i + k), j + k) != chess){
						isSame = false;
						break;
					}
				}
				if (isSame) {
					return chess == Chess::X ? Result::XWin : Result::OWin;
				}
			}
		}

		if (isFull()) {
			return Result::DRAW;
		}
		else {
			return Result::NotFinished;
		}
	}

};


int main() {
	Board board;
	board.initBoard();
	Result r = Result::NotFinished;
	while (r == Result::NotFinished) {
		r = board.play();
	}
	if (r == Result::DRAW) {
		cout << "Dogfall";
	}
	else if (r == Result::OWin) {
		cout << "O Success";
	}
	else {
		cout << "X Success";
	}
}