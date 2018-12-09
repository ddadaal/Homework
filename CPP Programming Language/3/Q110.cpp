#include <iostream>
using namespace std;

class Point3D {
private:
	int x = 0;
	int y = 0;
	int z = 0;
public:
	Point3D(const Point3D& other){
		x = other.x;
		y = other.y;
		z = other.z;
	}
	Point3D() {

	}
	Point3D& operator +(Point3D& other) {
		Point3D* newPoint = new Point3D(*this);
		newPoint->x = x + other.x;
		newPoint->y = y + other.y;
		newPoint->z = z + other.z;
		return *newPoint;
	}
	friend Point3D& operator++(Point3D&, int);
	friend Point3D& operator++(Point3D&);
	friend ostream& operator<<(ostream&, Point3D&);
	friend istream& operator>>(istream&, Point3D&);
};

Point3D& operator++(Point3D& old){
	old.x += 1;
	old.y += 1;
	old.z += 1;
	return old;
}

Point3D& operator++(Point3D& old, int) {
	Point3D temp(old);
	++old;
	return temp;
}

ostream& operator<<(ostream& out, Point3D& point){
	out << "x:" << point.x << ",y:" << point.y << ",z:" << point.z;
	return out;
}

istream& operator>>(istream& in, Point3D& point) {
	in >> point.x >> point.y >> point.z;
	return in;
}



int main() {
	Point3D p1, p2;
	cin >> p1 >> p2;
	p1++;
	cout << p1 << endl;
	cout << p1 + p2;
	return 0;
}