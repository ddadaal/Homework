import math
import numpy as np
import random

k=12

def distance(point1, point2): # p1, p2 both should be numpy.array
    return np.linalg.norm(point1-point2)

def init_centroids(points):
    sample_indices = random.sample(range(0,len(points)),k)
    return [points[i] for i in sample_indices]

class Solution():
    def solve(self, X):
        data = np.array(X)
        dim = len(data[0])
        centroids = init_centroids(data)

        cluster_changed = True
        cluster_map=[[0,0] for i in range(0, len(data))] # clusterNo, distance to centroid
        while cluster_changed:
            cluster_changed = False
            for i in range(0,len(data)):
                min_distance=np.inf
                min_centroid_index = 0
                for j in range(k):
                    dist = distance(centroids[j], data[i])
                    if dist < min_distance:
                        min_distance = dist
                        min_centroid_index = j
                if cluster_map[i][0] != min_centroid_index:
                    cluster_changed = True
                    cluster_map[i]=[min_centroid_index, min_distance]
            # recalculates centroids
            for centroid_index in range(0,k):
                points =[i for i in range(0,len(data)) if cluster_map[i][0] == centroid_index]
                new_centroid = np.mean([data[i] for i in points], axis=0)
                centroids[centroid_index] = new_centroid
        
    
        return [x[0] for x in cluster_map]