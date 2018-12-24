/*
 * @lc app=leetcode id=78 lang=cpp
 *
 * [78] Subsets
 *
 * https://leetcode.com/problems/subsets/description/
 *
 * algorithms
 * Medium (49.34%)
 * Total Accepted:    303.7K
 * Total Submissions: 615.6K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output:
 * [
 * ⁠ [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * 
 */

#include <vector>
using namespace std;

class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> result;
        result.push_back(vector<int>());
        vector<int> leading;
        for (int i=1;i<=nums.size();i++) {
           rec(nums, i, leading, result);
        }
        return result;
    }
    
    void rec(vector<int>& nums, int k, vector<int>& leading, vector<vector<int>>& result) {
        if (k == 1) {
            for (int n: nums) {
                leading.push_back(n);
                result.push_back(leading);
                leading.erase(leading.end()-1);
            }
        } else if (nums.size() == k) {
            vector<int> r;
            for (auto n: leading) r.push_back(n);
            for (auto n: nums) r.push_back(n);
            result.push_back(r);
        } else {
            leading.push_back(nums[0]);
            vector<int> newNums(nums.begin()+1, nums.end());
            rec(newNums, k-1, leading, result);
            leading.erase(leading.end()-1);
            rec(newNums, k, leading, result);
        }
    }
};
