#include <iostream>

using namespace std;

class Operation {
private:
	double NumberA;
	double NumberB;
public:
	virtual double GetResult() = 0;
	double getA() {
		return NumberA;
	}
	void setA(double a) {
		NumberA = a;
	}
	double getB() {
		return NumberB;
	}
	void setB(double b){
		NumberB = b;
	}
};

class OperationAdd : public Operation {
	double GetResult() override {
		return getA() + getB();
	}
};
class OperationSub : public Operation {
	double GetResult() override {
		return getA() - getB();
	}
}; 
class OperationMul : public Operation {
	double GetResult() override {
		return getA() * getB();
	}
}; 
class OperationDiv : public Operation {
	double GetResult() override {
		if (getB() == 0) {
			return -1;
		}
		return getA() / getB();
	}
};

class OperationFactory {
public:
	static Operation* CreateOperation(char op) {
		switch (op) {
		case '+':
			return new OperationAdd();
		case '-':
			return new OperationSub();
		case '*':
			return new OperationMul();
		case '/':
			return new OperationDiv();
		default:
			return nullptr;
		}
	}
};


int main() {
	char op;
	double a, b;
	cin >> op >> a >> b;
	Operation* opObj = OperationFactory::CreateOperation(op);
	opObj->setA(a);
	opObj->setB(b);
	cout << opObj->GetResult();
	return 0;
}