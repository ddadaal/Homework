#-*- coding:utf-8 -*-
import numpy as np

class Solution():
    def solve(self, A):
        f=np.poly1d([2,0,-1,1])
        g=np.poly1d(A)
        return f*g