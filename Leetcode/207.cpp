#include "cppincludes.h"

class Solution {
public:
    bool canFinish(int numCourses, vector<pair<int, int>>& prerequisites) {
        unordered_map<int, int> inDegree;
        for (auto edge: prerequisites) {
            if (inDegree.find(edge.first) == inDegree.end()) {
                inDegree[edge.first] = 0;
            }
            if (inDegree.find(edge.second) == inDegree.end()) {
                inDegree[edge.second] = 1;
            } else {
                inDegree[edge.second] += 1;
            }
        } 

        // topological sort
        while (true) {
            int node;
            if (!findZeroInDegree(inDegree, node)) {
                return inDegree.size() == 0;
            } else {
                // remove the entry
                inDegree.erase(node);

                // remove the edges
                for (auto edge: prerequisites) {
                    if (edge.first != node) {
                        continue;
                    }
                    inDegree[edge.second]--;
                }
                
            }
        }

        return true;
    }

    bool findZeroInDegree(unordered_map<int, int>& inDegree, int& val) {
        for (auto in: inDegree) {
            if (in.second == 0) {
                val = in.first;
                return true;
            }
        }
        return false;
    }

};