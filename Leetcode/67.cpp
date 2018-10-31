#include <iostream>
#include <math.h>
using namespace std;

class Solution {
public:
    string addBinary(string a, string b) {
        int length = max(a.size(),b.size());
        
        int delta = max(length-a.size() ,length-b.size());
        string leadingZero;
        for (int i =0;i<delta;i++) {
            leadingZero += "0";
        }

        int deltaB = b.size() - length;

        if (length > a.size()) {
            a = leadingZero + a;
        } else {
            b = leadingZero + b;
        }

        string result;
        int carry=0;
        for (int i =length-1;i>=0;i--) {
            int num1 = a[i]-'0';
            int num2 = b[i]-'0';
            int r = num1+num2+carry;
            carry = r/2;
            result = (char)(r%2+'0') + result;
        }
        if (carry == 1){
            result = '1' + result;
        }
        return result;
    }
};