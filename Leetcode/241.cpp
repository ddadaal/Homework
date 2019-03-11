#include "cppincludes.h"

// 1+2+3*4+5

class Solution {
  public:
    unordered_map<string, vector<int>> dp;
    vector<int> diffWaysToCompute(string input) {
        if (dp.find(input) != dp.end()) {
            return dp[input];
        }

        vector<int> result;

        for (int i = 0; i < input.size(); i++) {
            char c = input[i];
            if (c == '+' || c == '-' || c == '*') {
                vector<int> left = diffWaysToCompute(input.substr(0, i));
                vector<int> right = diffWaysToCompute(
                    input.substr(i + 1, input.size() - i - 1));

                for (int leftNum : left) {
                    for (int rightNum : right) {
                        switch (c) {
                        case '+':
                            result.push_back(leftNum + rightNum);
                            break;
                        case '-':
                            result.push_back(leftNum - rightNum);
                            break;
                        case '*':
                            result.push_back(leftNum * rightNum);
                            break;
                        }
                    }
                }
            }
        }

        if (result.empty()) {
            result.push_back(stoi(input));
        }

        dp[input] = result;
        return result;

    }
};