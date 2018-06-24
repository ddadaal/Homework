import pandas as pd
import numpy as np
import collections
import itertools
import math
import codecs

min_support = 150
min_support_another = 0.45
min_confidence = 0.9

def sort_item_key(item):
  return 0 if item[0] in ['r','d'] else int(item[1:]) 


class FPTreeNode:
  def __init__(self,key,value=1,parent=None):
    self.key=key
    self.value=value
    self.parent=parent
    self.children=[]
    self.sibling=None
  def __str__(self):
    return "%s %s %s" % (self.key,self.value,self.parent)


def create_fp_tree(data): #return root, header_table data: {data: data occurrence time. for tree recursion}

  # header_table
  header_table={}
  for key in data:
    for value in key:
      if value in header_table:
        header_table[value]+=data[key]
      else:
        header_table[value]=data[key]


  # filter with min_support
  header_table={x: header_table[x] for x in header_table if header_table[x]>=min_support}

    
  # reverse space for pointer
  for i in header_table:
    header_table[i]=[header_table[i], None]

  # sort data
  sorted_data = collections.OrderedDict()
  for row in data:
    sorted_row=tuple(sorted([i for i in row if i in header_table.keys()], key=lambda x: header_table[x],reverse=True))
    if sorted_row in sorted_data:
      sorted_data[sorted_row]+=data[row]
    else:
      sorted_data[sorted_row]=data[row]

  # print(sorted_data)
    
  # build fp tree

  root = FPTreeNode(None,0)
  for row in sorted_data:
    prev=root
    for item in row:
      found_in_children=False
      for child in prev.children:
        if child.key==item:
          child.value+=sorted_data[row]
          prev=child
          found_in_children=True
          break
      if found_in_children:
        continue
      new_node=FPTreeNode(item,sorted_data[row],prev)
      prev.children.append(new_node)
      prev=new_node

      # link header table

      table_root=header_table[item]
      if table_root[1] == None:
        table_root[1]=new_node
        continue
      table_root=table_root[1]
      while table_root.sibling != None:
        table_root=table_root.sibling
      table_root.sibling=new_node

  return root, header_table

def trace_prefix(node):
    prefixes = []
    while node.key != None:
        prefixes.append(node.key)
        node = node.parent
    return tuple(prefixes)

def trace_prefix_path(header_table, item):
    prefix_path = {}
    node = header_table[item][1]

    while node != None:
      prefix = trace_prefix(node.parent)
      if len(prefix)!=0:
      # print(prefix, node)
        prefix_path[tuple(prefix)] = node.value
      node = node.sibling
    return prefix_path

def mine_fp_tree(header_table, prefix, frequent_patterns): # prefix: set() for prefix, frequent_patterns: {combination: frequency}
  head_point_items = [v[0] for v in sorted(header_table.items(), key = lambda v:v[1][0])]

  for header in head_point_items:
    new_prefix = prefix.copy()
    new_prefix.add(header)

    support = header_table[header][0]


    frequent_patterns[tuple(new_prefix)] = support
    # print(frequent_patterns)

    prefix_path = trace_prefix_path(header_table, header)
    # print(header,header_table[header][1],new_prefix,prefix_path)
    # print()
    # print(prefix_path)
    if len(prefix_path) != 0:
      tree_root, new_header_table = create_fp_tree(prefix_path)
      if len(new_header_table) != 0:
        mine_fp_tree(new_header_table, new_prefix, frequent_patterns)


class Relation:
  def __init__(self,left,right):
    self.left=left
    self.right=right
    self.left_str=str(left)
    self.right_str=str(right)
  
  def __eq__(self, another):
    return another.left_str == self.left_str and another.right_str == self.right_str

  def __hash__(self):
    return hash((self.left_str, self.right_str))

  def list_repr(self):
    return [self.left, self.right]


class Solution():
  def solve(self):
    content=codecs.open('A.csv', 'r', encoding='utf-8').read()
    # make data into a dict

    data_dict={}
    
    for item in content.split()[1:]:
      t=tuple(item.split(","))
      if t in data_dict:
        data_dict[t]+=1
      else:
        data_dict[t]=1

    # get FP tree

    root, header_table = create_fp_tree(data_dict)
    # mine fp tree
    prefix = set()
    itemsets = {}
    mine_fp_tree(header_table,prefix,itemsets)

    print(itemsets)

    # sort itemsets

    itemsets = {tuple(sorted(key,key=sort_item_key)): value for key,value in itemsets.items()}


    # apply confidence restraints
    
    result = set()
    for key in itemsets:
      key=tuple(sorted(key,key=sort_item_key))
      total_occurrence=itemsets[key]
      for r in range(1,len(key)):
        for left in itertools.combinations(key,r):
          left=tuple(sorted(left,key=sort_item_key))
          right=set(key)-set(left)
          # print(key,left,right)
          if left in itemsets:
            left_occurrence = itemsets[left]
            if total_occurrence/left_occurrence>=min_confidence:
              if "republican0" in left or "democrat0" in left:
                new_rule = Relation(list(left), list(right))
                result.add(new_rule)
            
    return [x.list_repr() for x in result]

result=Solution().solve()
print(result)