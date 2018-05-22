#include <iostream>
#include <algorithm>
using namespace std;

template <class T>
class BinarySearch {
public:
	T* arr;
	int size;
	
	friend istream& operator>>(istream& in, BinarySearch<T>& obj) {
			in >> obj.size;
			obj.arr = new T[obj.size];
			for (int i = 0; i < obj.size; i++){
				in >> obj.arr[i];
			}
			return in;
	}

	void Sort() {
		sort(arr, arr + size);
	}

	int binarySearch(T e) {
		int start = 0, end = size-1;
		int count = 0;
		while (start <= end){
			int mid = start + (end - start) / 2;
			count++;
			if (e == arr[mid]){
				return count;
			}
			else if (e < arr[mid]){
					end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		return count;
	}
};

template<class T>
void execute() {
	BinarySearch<T> bs;
	cin >> bs;
	bs.Sort();
	T ele;
	cin >> ele;
	cout << bs.binarySearch(ele);

}

int main() {
	execute<int>();
	cout << endl;
	execute<double>();
	cout << endl;
	execute<char>();

}