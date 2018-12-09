# -*- coding:utf-8 -*-
import random
from collections import Counter
import numpy as np

def cosine_simularity(vector1, vector2): # vector n*1
    return np.dot(vector1, vector2) / np.linalg.norm(vector1)*np.linalg.norm(vector2)

def non_zero_mean(array):
    count_of_nonzero=np.count_nonzero(array)
    if count_of_nonzero==0:
        return 0
    return np.sum(array)/count_of_nonzero

def find_indices_of_max_values(array,k):
    k=min(k,len(array))
    return np.argpartition(array,-k)[-k:]

class Solution():
    def solve(self, R, Y, ratings, k):
        shape = np.shape(Y) # 0 movie, 1 user
        
        most_similar_user_num = 20

        forecasted_movie_rates = {x: 0 for x in np.where(ratings==0)[0]}

        for movie_index in forecasted_movie_rates:
            
            user_simularities = {x: 0 for x in np.where(R[movie_index]==1)[0]}
            
            for user_index in user_simularities:
                user_simularities[user_index]=cosine_simularity(ratings[:,0], Y[:,user_index])
            

            # find the most similar users
            user_indices_and_simularities = Counter(user_simularities).most_common(most_similar_user_num)
            # calculate the forecasted rating for movie i
            numerator=0 # 分子
            denominator=0 # 分母
            for user_index, simularity in user_indices_and_simularities:
                denominator+=np.abs(simularity)
                
                numerator+=simularity*(Y[movie_index,user_index]-non_zero_mean(Y[:,user_index]))
                
            
            forecasted_movie_rates[movie_index]=non_zero_mean(ratings)+numerator/denominator
        
        # find the indices of movies of best forecasted ratings
        indices = {x[0] for x in Counter(forecasted_movie_rates).most_common(k)}
        return indices