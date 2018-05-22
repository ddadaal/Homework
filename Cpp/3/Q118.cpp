#include <iostream>
using namespace std;

class Hero {
public:
	int hp;
	int atk;
	Hero(int hp, int atk) : hp(hp), atk(atk) {

	}
	bool isDead() {
		return hp <= 0;
	}
	virtual void beAttacked(int enemyAttack) {
		hp -= enemyAttack;
	};
	virtual void prePower() {

	}
	virtual void afterPower() = 0;

	virtual void outputName() = 0;
};

class Warrior : public Hero {
public:
	Warrior() : Hero(12,2) {

	}
	void afterPower() override {
		hp++;
	}
	void outputName() override {
		cout << "Warrior " << atk << " " << hp;
	}
};

class Magician : public Hero {
public:
	bool powerUsed = false;
	bool powerActivated = false;
	Magician() : Hero(2, 6) {

	}

	void beAttacked(int enemyAttack) override{
		if (powerActivated) {
			powerActivated = false;
		}
		else {
			Hero::beAttacked(enemyAttack);
		}
	}
	void prePower() override {
		if (!powerUsed) {
			powerActivated = true;
			powerUsed = true;
		}
	}
	void afterPower() override {

	}
	void outputName() override {
		cout << "Magician " << atk << " " << hp;
	}
};

class Leader : public Hero{
public:
	Leader() :Hero(6, 6) {

	}
	void afterPower() override {
		atk++;
	}
	void outputName() override {
		cout << "Leader " << atk << " " << hp;
	}
};

class Selector {
public:
	static Hero* select() {
		int i;
		cin >> i;
		switch (i){
		case 1:
			return new Warrior;
		case 2:
			return new Magician;
		default:
			return new Leader;		
		}
	}
};

int main() {
	int n;
	cin >> n;

	Hero** player1 = new Hero*[n];
	int p1 = 0;
	for (int i = 0; i < n; i++) {
		player1[i] = Selector::select();
	}

	Hero** player2 = new Hero*[n];
	int p2 = 0;
	for (int i = 0; i < n; i++) {
		player2[i] = Selector::select();
	}

	while (p1 != n && p2 != n) {
		Hero* h1 = player1[p1];
		Hero* h2 = player2[p2];
		h1->prePower();
		h2->prePower();
		h1->beAttacked(h2->atk);
		h2->beAttacked(h1->atk);
		if (h1->isDead()) {
			p1++;
		}
		else {
			h1->afterPower();
		}
		if (h2->isDead()) {
			p2++;
		}
		else {
			h2->afterPower();
		}
		
	}

	if (p1 == n && p2 == n) {
		cout << "All Dead";
		return 0;
	}

	for (; p1 < n; p1++) {
		player1[p1]->outputName();
		cout << endl;
	}
	for (; p2 < n; p2++) {
		player2[p2]->outputName();
		cout << endl;
	}

	return 0;


	
	
}