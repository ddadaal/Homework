/*
 * @lc app=leetcode id=75 lang=cpp
 *
 * [75] Sort Colors
 *
 * https://leetcode.com/problems/sort-colors/description/
 *
 * algorithms
 * Medium (40.59%)
 * Total Accepted:    271.8K
 * Total Submissions: 669.6K
 * Testcase Example:  '[2,0,2,1,1,0]'
 *
 * Given an array with n objects colored red, white or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order
 * red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this
 * problem.
 * 
 * Example:
 * 
 * 
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * 
 * Follow up:
 * 
 * 
 * A rather straight forward solution is a two-pass algorithm using counting
 * sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then
 * overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 * 
 * 
 */

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    void sortColors(vector<int>& nums) {
        int e1 =0, e2 = nums.size()-1;
        int i =0;
        while(nums[e2] == 2) { e2--; }
        while(nums[e1] == 0) { e1++; i++; }


        while (i<=e2) {
            int num = nums[i];
            if (num == 1) {
                i++;
            } else if (num == 2) {
                swap(nums[e2], nums[i]);
                while(nums[e2] == 2) { e2--; }
            } else {
                swap(nums[e1], nums[i]);
                while(nums[e1] == 0) { e1++; i++; }
            }
        }
    }
};
