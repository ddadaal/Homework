#include "cppincludes.h"

class Solution {
public:
    string shortestPalindrome(string s) {
        int size = s.size();
        if (size <= 1) { return s; }
        
        int longest = size-1;
        for (;longest>=1;longest--) {
            if (isPalindrome(s, longest)) {
                break;
            }
        }
        
        cout << longest;

        string result(s.begin()+longest+1, s.end());
        reverse(result.begin(), result.end());
        result+=s;
        return result;
    }

    bool isPalindrome(string& s, int end) {
        int start = 0;
        while (start<end) {
            if (s[start] != s[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
};