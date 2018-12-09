#-*- coding:utf-8 -*-

from scipy.stats import norm
from scipy import sqrt,square
import math

class Solution():
    def solve(self):
        n=51
        mean=1.1
        std=4.9
        alpha=0.05
        
        z=(mean-0)/(std/(sqrt(n)))
        z1=norm.ppf(1-alpha)
        
        return [n-1, round(z,2), z<z1]