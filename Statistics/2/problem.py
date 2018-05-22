#-*- coding:utf-8 -*-
import requests, zipfile
import pandas as pd
import numpy as np
class Solution():
    def download_file(self):
        url = 'http://files.grouplens.org/datasets/movielens/ml-latest-small.zip' 
        r = requests.get(url)

        with open('zip.zip', 'wb') as f:  
            f.write(r.content)
            
        zip_ref = zipfile.ZipFile('zip.zip', 'r')
        zip_ref.extractall('extracted')
        zip_ref.close()


    def solve(self):
        # self.download_file()
        
        movies = pd.read_csv("extracted/ml-latest-small/movies.csv")
        movie = movies[movies["movieId"]==1].values[0]
        
        movieNameOfMovieId1 = movie[1]
        genresCounts = len(movie[2].split('|'))
        
        movie_ratings = {}
        movie_ratings_count = {}
        
        ratings = pd.read_csv("extracted/ml-latest-small/ratings.csv").filter(items=["movieId","rating"]).groupby(by=["movieId"]).count()
        
        maxId = ratings["rating"].idxmax()
        
        movie = movies[movies["movieId"]==maxId].values[0]
        
        movieNameOfTheMostRatedMovie = movie[1]
        return [movieNameOfMovieId1, genresCounts, movieNameOfTheMostRatedMovie]
        pass