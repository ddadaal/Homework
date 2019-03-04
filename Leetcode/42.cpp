/*
 * @lc app=leetcode id=42 lang=cpp
 *
 * [42] Trapping Rain Water
 *
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (40.50%)
 * Total Accepted:    228.6K
 * Total Submissions: 564.4K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 * 
 * Example:
 * 
 * 
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * 
 */
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int trap(vector<int>& height) {
        vector<int> cache(height.size(), 0);
        int maxLeft=0;
        for (int i=1;i<height.size();i++) {
            maxLeft = max(maxLeft, height[i-1]);
            cache[i] = maxLeft;
        }
        int maxRight = 0, result = 0;
        for (int i=height.size()-2;i>=1;i--) {
            maxRight = max(maxRight, height[i+1]);
            result += max(min(cache[i], maxRight) - height[i], 0);
        
        }
        return result;


    }
};
