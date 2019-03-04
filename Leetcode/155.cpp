#include "cppincludes.h"

class MinStack {

private:
  stack<int> main;
  stack<int> minStack;

public:
  /** initialize your data structure here. */
  MinStack() {}

  void push(int x) {
    main.push(x);
    if (minStack.empty() || x <= getMin()) {
      minStack.push(x);
    }
  }

  void pop() {
    if (main.top() == minStack.top()) {
      minStack.pop();
    }
    main.pop();
  }

  int top() { return main.top(); }

  int getMin() { return minStack.top(); }
};
