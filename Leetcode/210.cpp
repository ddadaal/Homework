#include "cppincludes.h"

class Solution {
public:
  vector<int> findOrder(int numCourses, vector<pair<int, int>> &prerequisites) {
    vector<int> order;

    if (prerequisites.size() == 0) {
      for (int i = 0; i < numCourses; i++) {
        order.push_back(i);
      }
      return order;
    }

    vector<int> inDegree(numCourses, 0);

    for (auto& edge : prerequisites) {
      if (inDegree[edge.second] == -1) {
        inDegree[edge.second] = 0;
      }
      if (inDegree[edge.first] == -1) {
        inDegree[edge.first] = 1;
      } else {
        inDegree[edge.first] += 1;
      }
    }

    stack<int> zeroDegrees;

    // add nodes with 0 in degree
    for (int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0) {
        zeroDegrees.push(i);
      }
    }

    // topological sort
    while (true) {

      if (zeroDegrees.empty()) {
        if (order.size() == numCourses) {
          return order;
        } else {
          return {};
        }
      }
      int node = zeroDegrees.top();
      zeroDegrees.pop();

      // add to list
      order.push_back(node);

      // remove the in degrees
      for (auto edge : prerequisites) {
        if (edge.second == node) {
          if (inDegree[edge.first] == 1) {
            zeroDegrees.push(edge.first);
          }
          inDegree[edge.first]--;
        }
      }
    }

    return {};
  }
};