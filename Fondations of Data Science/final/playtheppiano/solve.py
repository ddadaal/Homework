#-*- coding:utf-8 -*-
from scipy.stats import t
from scipy import sqrt

class Solution():
    def solve(self):
        claim_mean=5
        mean=4.6
        std=2.2
        n=20
        alpha=0.05
        
        t1=(mean-claim_mean)/(std/(sqrt(n)))
        
        t2=t.isf(0.05,15)
        
        return [n-1, round(t1,2), t1<t2]
        
        