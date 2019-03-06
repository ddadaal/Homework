#include "cppincludes.h"


class Solution {
public:
  string largestNumber(vector<int> &nums) {

    vector<string> strNums;
    for (int num : nums) {
      strNums.push_back(to_string(num));
    }

    sort(strNums.begin(), strNums.end(), [](string &a, string &b) {

      // always compare the most significant digit
      string sa = a;
      string sb = b;
      sa += b;
      sb += a;
      return sa>sb;
    });
    
    if (strNums[0][0] == '0') {
      return "0";
    }
    
    string result;
    for (auto& str: strNums) {
      result += str;
    }


    return result;
  }
};