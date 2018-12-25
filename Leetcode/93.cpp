#include <cppincludes.h>

class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> result;
        rec(s, 0, 3, result);
        return result;
    }
    
    void rec(string s, int start, int dots, vector<string>& result) {
        if (start >= s.size() ) {
            return;
        }
        
        if (dots == 0) {

            if (start <= s.size()-2 && s[start] == '0') {
                return;
            }
            
            if (start <= s.size() -4) {
                return;
            }
            
            int lastPart = 0;
            for (int i=start;i<s.size();i++) {
                lastPart = 10*lastPart + (s[i]-'0');
            }
            if (lastPart > 255) {
                return;
            }
            result.push_back(s);
            return;
        } 
        
        int part = s[start]-'0';
        
        // add dot after first digit
        s.insert(start+1, 1, '.');
        rec(s, start+2, dots-1, result);
        s.erase(s.begin()+start+1);
        
        if (part == 0) {
            return;
        }
        
        // add dot after two digits 
        
        if (start >= s.size()-1) {
            return;
        }
        part = part*10 + s[start+1] - '0';
        s.insert(start+2, 1, '.');
        rec(s, start+3, dots-1, result);
        s.erase(s.begin()+start+2);
        
        // add dot after third digits if valid
        if (start >= s.size() -2) {
            return;
        }
        part = part*10 + s[start+2] -'0';
        if (part > 255) {
            return;
        }
        s.insert(start+3, 1, '.');
        rec(s, start+4, dots-1, result);
        
        
        
    }
};