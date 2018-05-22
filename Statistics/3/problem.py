import requests, zipfile
import pandas as pd
import numpy as np
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
        tc = data[data["Transition"]==1]
        
        reform = tc.filter(items=["country","year","finreform"]).groupby(by=["country"])

        maxTuple = ["",-1]
        for name, group in reform:
            for i in range(1, len(group)):
                values = group.values
                diff = values[i][2]-values[i-1][2]
                if diff>maxTuple[1]:
                    maxTuple = [name, diff]

        return maxTuple[0]