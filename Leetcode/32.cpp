/*
 * @lc app=leetcode id=32 lang=cpp
 *
 * [32] Longest Valid Parentheses
 *
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 *
 * algorithms
 * Hard (24.40%)
 * Total Accepted:    159.9K
 * Total Submissions: 655.4K
 * Testcase Example:  '"(()"'
 *
 * Given a string containing just the characters '(' and ')', find the length
 * of the longest valid (well-formed) parentheses substring.
 * 
 * Example 1:
 * 
 * 
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 * 
 * 
 */
#include <string>
#include <stack>
#include <algorithm>
using namespace std;

class Solution
{
  public:
    int longestValidParentheses(string s)
    {
        int current_max = 0;
        int smax = 0;
        int current_start = -1;
        int smax = 0;
        stack<int> st;
        st.push(-1);
        for (int i = 0; i < s.size(); i++)
        {
            if (s[i] == '(')
            {
                st.push(i);
                if (current_start == -1) {
                    current_start = i;
                }
            }
            else
            {
                if (st.empty()) {
                    if (current_start != -1) {
                        
                    }
                }
            }
        }
        return current_max;
    }
};
