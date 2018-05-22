#include <iostream>
#include <vector>
using namespace std;

class Food {
public:
	double carbohydrate;
	double protein;
	double df;
	double fat;
	Food(double carbohydrate, double protein, double df, double fat)
		: carbohydrate(carbohydrate), protein(protein), df(df), fat(fat){

	}

};

class Rice : public Food {
public:
	Rice() : Food(16.2, 3.7, 0, 0) { }
};
class Beef : public Food {
public:
	Beef() : Food(1.8,17.5,0,7.2) { }
};
class Bro : public Food {
public:
	Bro() : Food(0.2,0.4,3.6,0) { }
};
class Oat : public Food {
public:
	Oat() : Food(12.3, 5.7, 7.3, 3) { }
};
class Duck : public Food {
public:
	Duck() : Food(6.9,9,0,9.3) { }
};
class Cab : public Food {
public:
	Cab() : Food(2.1,0.8,4.3,0) { }
};



class Diet {
private:
	vector<Food*> foods;
public:
	Diet& operator+=(Food& food) {
		foods.push_back(&food);
		return *this;
	}

	double sum(double(*mapper)(Food*)) {
		double result = 0;
		for (Food* food : foods) {
			result += mapper(food);
		}
		return result;
	}

	bool if_healthy() {
		double totalCH = sum([](Food* food) {return food->carbohydrate; });
		double totalProtein = sum([](Food* food) { return food->protein; });
		double totalDF = sum([](Food* food) { return food->df;  });
		double totalFat = sum([](Food* food){ return food->fat; });
		return totalCH >= 13.3 
			&& totalProtein >= 13.5 
			&& totalDF >= 3.3 
			&& totalFat <= 10.3;
	}
};

Food* selectFood() {
	int i;
	cin >> i;
	switch (i) {
	case 1:
		return new Rice;
	case 2:
		return new Beef;
	case 3:
		return new Bro;
	case 4:
		return new Oat;
	case 5:
		return new Duck;
	case 6:
		return new Cab;
	default:
		return nullptr;
	}
}

int main() {
	Diet diet;
	for (int i = 0; i < 3; i++) {
		Food * food = selectFood();
		if (food == nullptr){
			cout << "-1";
			return 0;
		}
		diet += *food;
	}

	if (diet.if_healthy()) {
		cout << "healthy";
	}
	else {
		cout << "unhealthy";
	}
	return 0;
}