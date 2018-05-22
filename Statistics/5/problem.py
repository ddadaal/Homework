import pandas as pd
import numpy as np
import collections
import itertools

min_support = 150

def arraycontains(a1, a2): # a2 has all elements that a1 has
    for i in a1:
        if not i in a2:
            return False
    return True

def count_occurrence(candidate, values): # candidate: [], values: [[]] returns the occurrence
    result=0
    for e in values:
        if arraycontains(candidate, e):
            result+=1
    return result

def all_subsets(arg_set):
    result=[]
    for i in range(0,len(arg_set)):
        result += itertools.combinations(arg_set, i)
    return result

class Solution():
    def solve(self):
        data = pd.read_csv("A.csv")
        values = data.values
        first_level = self.filter_minimal_support(values)
        result = {}
        last_appended = []
        level =2
        first = True
        while True:
            combination = list(first_level.keys()) if first else set(np.array(last_appended).flatten())
            combinations = list(itertools.combinations(combination,level))
            count = {c: count_occurrence(c, values) for c in combinations if 'democrat0' in c or 'republican0' in c}
            # print(count)
            toappend = {x: count[x] for x in count if count[x]>=min_support}
            if len(toappend)==0:
                break
            else:
                first=False
                last_appended=list(toappend.keys())
                result.update(toappend)
                level+=1
        print(result)

        # result.keys()是频繁项集, values()是对应的值
        # 下面生成强规则

        for item in result:
            occurrence = result[item]
            subsets = all_subsets(item[1:])
            for subset in subsets:
                left=[item[0]]+list(subset)
                divider=count_occurrence(left,values)
                confidence = occurrence / divider
                if confidence >= min_confidence:
                    rule_set.append([left, list(set(item[1:])-set(subset))])
        return rule_set


    def filter_minimal_support(self, values):
      d = collections.Counter(values.flatten())
      return {x: d[x] for x in d if d[x]>=min_support}

Solution().solve()