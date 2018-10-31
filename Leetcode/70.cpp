// 从最后一步开始往前考虑，可省去递归

class Solution {
public:
    int climbStairs(int n) {
        int * cache = new int[n];

       cache[n-1]=1;
       cache[n-2]=2;

        for (int i=n-3;i>=0;i--) {
            cache[i]=cache[i+1]+cache[i+2];
        }

        return cache[0];
    }
};