#-*- coding:utf-8 -*-
from scipy.stats import norm
from scipy import sqrt
class Solution:
    def solve(self):
        z=norm.ppf(0.975)
        mean=8.5
        sigma=5
        delta=sigma/sqrt(3600)*z
        return [mean-delta, mean+delta]
        