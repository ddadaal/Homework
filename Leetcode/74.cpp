/*
 * @lc app=leetcode id=74 lang=cpp
 *
 * [74] Search a 2D Matrix
 *
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 *
 * algorithms
 * Medium (34.48%)
 * Total Accepted:    192.9K
 * Total Submissions: 559.4K
 * Testcase Example:  '[[1,3,5,7],[10,11,16,20],[23,30,34,50]]\n3'
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the
 * previous row.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * matrix = [
 * ⁠ [1,   3,  5,  7],
 * ⁠ [10, 11, 16, 20],
 * ⁠ [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * matrix = [
 * ⁠ [1,   3,  5,  7],
 * ⁠ [10, 11, 16, 20],
 * ⁠ [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 * 
 */
#include <vector>
using namespace std;

class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {

        if (matrix.size() == 0 || matrix[0].size() == 0) {
            return false;
        }

        // locate row
        int left = 0, right = matrix.size()-1;
        while (left<=right) {
            int mid = (left+right)/2;
            int value = matrix[mid][0];
            if (target == value) {
                return true;
            } else if (target < value) {
                right=mid-1;
            } else {
                left=mid+1;
            }
        }

        // right is row
        // locate column
        if (right == -1 || right >= matrix.size()) {
            return false;
        }

        vector<int>& base = matrix[right];
        int l2 = 0, r2 = base.size()-1;
        while (l2 <= r2) {
            int mid = (l2+r2)/2;
            int value = base[mid];
            if (target == value) {
                return true;
            } else if (target < value) {
                r2 = mid-1;
            } else {
                l2 = mid+1;
            }
        }
        return false;


    }
};
