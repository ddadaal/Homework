#-*- coding:utf-8 -*-
import math

class Solution():
    def solve(self, A):
        #call function prime
        return [i for i in A if self.prime(i)]

    #judge whether x is prime or not
    def prime(self, x):
        if x==1:
            return False
        if x==2:
            return True
        for i in range(3,int(math.sqrt(x))):
            if x % i == 0:
                return False
        return True