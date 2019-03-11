#include "cppincludes.h"

// push 1, push 2, top, pop, push 3, push 4, pop, pop
// [1,2] []
// [1,2]
// [2] [1] 2,4,
// [3, 4] [1]
// 


class MyStack {
public:
    queue<int> q1;
    queue<int> q2;
    int topVal;
    /** Initialize your data structure here. */
    MyStack() {
        
    }
    
    /** Push element x onto stack. */
    void push(int x) {
        q1.push(x);
        topVal = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    int pop() {
        while (q1.size() > 1){
            int val = q1.front();
            if (q1.size() == 2) {
                topVal = val;
            }
            q2.push(val);
            q1.pop();
        }
        int val = q1.front();
        q1.pop();

        while (!q2.empty()) {
            q1.push(q2.front());
            q2.pop();
        }
        return val;

    }
    
    /** Get the top element. */
    int top() {
        return topVal;
    }
    
    /** Returns whether the stack is empty. */
    bool empty() {
        return q1.empty();
    }
};
