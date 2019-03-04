/*
 * @lc app=leetcode id=77 lang=cpp
 *
 * [77] Combinations
 *
 * https://leetcode.com/problems/combinations/description/
 *
 * algorithms
 * Medium (44.74%)
 * Total Accepted:    174.1K
 * Total Submissions: 389.1K
 * Testcase Example:  '4\n2'
 *
 * Given two integers n and k, return all possible combinations of k numbers
 * out of 1 ... n.
 * 
 * Example:
 * 
 * 
 * Input: n = 4, k = 2
 * Output:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 * 
 */
#include <vector>
using namespace std;


class Solution {
public:
    vector<vector<int>> combine(int n, int k) {

        if (n == k) {
            vector<int> item;
            for (int i=1;i<=n;i++) {
                item.push_back(i);
            }
            return vector<vector<int>>{ item };
        }
        if (k == 1) {
            vector<vector<int>> result;
            for (int i=1;i<=n;i++) {
                result.push_back(vector<int> { i });
            }
            return result;
        }
        vector<vector<int>> result;
        auto sub = combine(n-1,k-1);
        for (auto v: sub) {
            v.push_back(n);
            result.push_back(v);
        }
        for (auto v: combine(n-1, k)) {
            result.push_back(v);
        }
        
        return result;
    }
};
