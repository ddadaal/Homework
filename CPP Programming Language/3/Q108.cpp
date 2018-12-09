#include <iostream>
using namespace std;

class GPU {
private:
	int price;
	int hashRate;
public:
	virtual double calculate() = 0;
	int getHashRate() {
		return hashRate;
	}
	void setPrice(int price) {
		this->price = price;
	}

	void setHashRate(int hashRate) {
		this->hashRate = hashRate;
	}

	int getPrice() {
		return price;
	}
};

class SpecificGPU : public GPU {
protected:
	double power;
public:
	double calculate() override {
		return getHashRate() * 0.02 - power;
	}
	SpecificGPU(double power) : power(power) {

	}
	friend istream& operator >>(istream&, SpecificGPU&);
};

class GTX1080 : public SpecificGPU {
public:
	GTX1080() : SpecificGPU(16.2) {

	}
};

class TitanV : public SpecificGPU {
public:
	TitanV() : SpecificGPU(28.8){

	}
};

class GTX1080Ti : public SpecificGPU {
public:
	GTX1080Ti() : SpecificGPU(19.44) {

	}
};

istream& operator>> (istream& in, SpecificGPU& gpu){
	int price = 0, hashRate = 0;
	in >> price >> hashRate;
	gpu.setHashRate(hashRate);
	gpu.setPrice(price);
	return in;
}

int main() {
	GTX1080 gtx1080;
	TitanV titanV;
	GTX1080Ti gtx1080Ti;
	cin >> gtx1080 >> titanV >> gtx1080Ti;

	double dailyIncome = gtx1080.calculate() + titanV.calculate() + gtx1080Ti.calculate();
	if (dailyIncome <= 0) {
		cout << -1;
	}
	else {
		double dayCalculated = (gtx1080.getPrice() + titanV.getPrice() + gtx1080Ti.getPrice()) / dailyIncome;
		int day = (int)dayCalculated;
		if (day != dayCalculated) {
			cout << day + 1;
		}
		else {
			cout<< day;
		}
	}
	return 0;
}
