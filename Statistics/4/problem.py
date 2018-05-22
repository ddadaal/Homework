import pandas as pd
import codecs
from collections import Counter
class Solution():
    def solve(self):
        content = codecs.open('A.txt', 'r', encoding='utf-8').read()
        include = [" "] + [chr(i) for i in range(ord("a"),ord("z")+1)] + [chr(i) for i in range(ord("A"),ord("Z")+1)] 
        cleared_content = ''.join([i if i in include else " " for i in content])
        
        words = cleared_content.split()

        counted = Counter(words)
        return [5059]+[x[0] for x in counted.most_common(10)]