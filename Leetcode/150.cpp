#include "cppincludes.h"

class Solution {
public:
  int evalRPN(vector<string> &tokens) {
    stack<int> numStack;

    for (string &token : tokens) {
      if (token == "+" || token == "-" || token == "*" || token == "/") {
        int num2 = numStack.top();
        numStack.pop();
        int num1 = numStack.top();
        numStack.pop();
        switch (token[0]) {
        case '+':
          numStack.push(num1 + num2);
          break;
        case '-':
          numStack.push(num1 - num2);
          break;
        case '*':
          numStack.push(num1 * num2);
          break;
        case '/':
          numStack.push(num1 / num2);
          break;
        }
        break;
      } else {
        numStack.push(stoi(token));
      }
    }

    return numStack.top();
  }
};