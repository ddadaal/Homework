#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    string simplifyPath(string path) {
        string temp;
        vector<string> paths;

        path += "/";

        for (int i=1;i<path.size();i++) {
            if (path[i] == '/') {
                if (temp == "..") {
                    if (!paths.empty()) {
                        paths.pop_back();
                    }
                        temp = "";

                } else {
                    if (temp != "" && temp != ".") {
                    paths.push_back(temp);
                    }
                    temp = "";

                }
            } else {
                temp += path[i];
            }
        }

        if (paths.empty()) {
            return "/";
        }

        string result;
        for (auto path: paths) {
            result += '/' + path;
        }

        return result;
    }
};