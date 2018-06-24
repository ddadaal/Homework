#-*- coding:utf-8 -*-
from scipy.stats import norm
from scipy import sqrt
from numpy import abs
class Solution():
    def solve(self):
        n=22
        mean_t, std_t=52.1, 45.1
        mean_c, std_c=27.1, 26.4
        alpha=0.05
        
        n1=(mean_t-mean_c)/(sqrt((std_t**2)/n+(std_c**2)/n))
        
        n2=norm.ppf(1-alpha)
        
        return [n-1,round(n1,2),abs(n1)<n2]