#-*- coding:utf-8 -*-
import requests
import numpy as np
import pandas as pd
import zipfile

class Solution():
    def download_file(self):
        url = 'https://www.imf.org/external/pubs/ft/wp/2008/Data/wp08266.zip'  
        r = requests.get(url)

        with open('zip.zip', 'wb') as f:  
            f.write(r.content)
            
        zip_ref = zipfile.ZipFile('zip.zip', 'r')
        zip_ref.extractall('extracted')
        zip_ref.close()

    def solve(self):
        self.download_file()
        data = pd.read_stata("extracted/Financial Reform Dataset Dec 2008.dta")
        countries = np.count_nonzero(np.unique(data.filter(items=["country"])))
        
        d = {}
        for i in data.filter(items=["country"]).values:
            if i[0] in d:
                d[i[0]]=d[i[0]]+1
            else:
                d[i[0]]=1
        
        
        
        
        medianNumber = np.median(list(d.values()))
        return [countries, medianNumber]
        pass