#include <iostream>
using namespace std;

class Ingredient {
public:
	virtual int getPrice() = 0;
};

class Meat : public Ingredient {

};

class Vegatable : public Ingredient {

};

class Beef : public Meat {
public:
	int getPrice() override {
		return 10;
	}
};

class Pork : public Meat {
public:
	int getPrice() override {
		return 8;
	}
};

class Chicken : public Meat {
public:
	int getPrice() override {
		return 7;
	}
};

class Lettuce : public Vegatable {
public:
	int getPrice() override {
		return 4;
	}
};

class Tomato : public Vegatable {
public:
	int getPrice() override {
		return 3;
	}
};


class Decorator {
private:
	Ingredient* ingredient;

public:
	Decorator(Ingredient* ingre) : ingredient(ingre) {

	}
	virtual int getPrice() {
		return ingredient->getPrice();
	}
};

class HamburgerIngredient : public Decorator {
private:
	int quantity;
public:
	HamburgerIngredient(Ingredient* ingre, int quantity) : Decorator(ingre), quantity(quantity) {

	}
	int getPrice() override {
		return Decorator::getPrice() * quantity;
	}
};

class Selector {
public:
	static Meat* selectMeat(int no) {
		switch (no) {
		case 1:
			return new Beef;
		case 2:
			return new Pork;
		default:
			return new Chicken;
		}
	}

	static Vegatable* selectVegatable(int no) {
		switch (no){
		case 1:
			return new Lettuce;
		default:
			return new Tomato;
		}
	}
};

int main() {
	int meatNo, meatQ, vegaNo, vegaQ;
	cin >> meatNo >> meatQ >> vegaNo >> vegaQ;

	HamburgerIngredient meat(Selector::selectMeat(meatNo), meatQ);
	HamburgerIngredient vegatable(Selector::selectVegatable(vegaNo), vegaQ);

	cout << meat.getPrice() + vegatable.getPrice();
}