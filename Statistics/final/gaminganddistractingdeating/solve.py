#-*- coding:utf-8 -*-
from scipy.stats import t
from numpy import abs,sqrt
class Solution():
    def solve(self):
        n=22
        mean_t, std_t=52.1, 45.1
        mean_c, std_c=27.1, 26.4
        alpha=0.05
        
        sw=sqrt( ( (n-1) * (std_t**2) + (n-1) * (std_c**2)) / (n-1+n-1) )
        
        n1=(mean_t-mean_c)/(sw*sqrt(1.0/n+1.0/n))
        
        n2=t.isf(alpha,2*n-2)
        
        return [n-1,round(n1,2),abs(n1)<n2]