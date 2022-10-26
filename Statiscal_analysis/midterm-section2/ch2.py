# module import

import os
os.chdir("c:\\Temp")
os.getcwd()
# 히스토그램
import matplotlib.pyplot as plt
plt.figure()
n, bins, patches = plt.hist(drink, bins = 10, facecolor="blue", alpha = 0.3)
print(n, bins)
x = [(bins[i]+bins[i+1])/2 for i in range(len(bins)-1)]   #도수다각형 
w_bin = bins[1]-bins[0]  #도수다각형 
x.insert(0,x[0]-w_bin)  #도수다각형 
x.append(x[-1]+w_bin)  #도수다각형 
n = np.insert(n,0,0.0)  #도수다각형 
n = np.append(n,0.0)  #도수다각형 
plt.xlabel('drink')
plt.ylabel('Frequency')
plt.title("Histogram of drink")
plt.plot(x,n,'red',marker='o')  #도수다각형 
plt.savefig('./plot/그림26.png')
plt.show()


#------------ data input --------------------
import numpy as np
death = np.array([2, 1, 2, 4, 2, 5, 3, 3, 5, 6, 3, 8, 3,
                  3, 6, 3, 6, 5, 3, 5, 2, 6, 2, 3, 4, 3,
                  2, 9, 2, 2, 3, 2, 7, 3, 2, 10, 6, 2, 3,
                  1, 2, 3, 3, 4, 3, 2, 6, 2, 2, 3, 2, 3,
                  4, 3, 2, 3, 5, 2, 5, 5, 3, 4, 3, 6, 2,
                  1, 2, 3, 2, 6, 3, 3, 6, 3, 2, 3, 6, 4,
                  6, 5, 3, 5, 6, 2, 6, 3, 2, 3, 2, 6, 2,
                  6, 3, 3, 2, 6, 9, 6, 3, 6, 6, 2, 3, 2,
                  3, 5, 3, 5, 2, 3, 2, 3, 3, 1, 3, 3, 2,
                  3, 3, 4, 3, 6, 6, 3, 3, 3, 2, 3, 3, 6])

#------------ data input --------------------
import numpy as np
drink = np.array([101.8, 101.5, 101.8, 102.6, 101, 96.8, 102.4, 100, 98.8, 98.1,
                  98.8, 98, 99.4,95.5, 100.1, 100.5, 97.4, 100.2, 101.4, 98.7,
                  101.4, 99.4, 101.7, 99, 99.7, 98.9, 99.5, 100, 99.7, 100.9,
                  99.7, 99, 98.8, 99.7, 100.9, 99.9, 97.5, 101.5, 98.2, 99.2,
                  98.6, 101.4, 102.1, 102.9, 100.8, 99.4, 103.7, 100.3, 100.2, 101.1,
                  101.8, 100, 101.2, 100.5, 101.2, 101.6, 99.9, 100.5, 100.4, 98.1,
                  100.1, 101.6, 99.3, 96.1, 100, 99.7, 99.7, 99.4, 101.5, 100.9,
                  101.3, 99.9, 99.1, 100.7, 100.8, 100.8, 101.4, 100.3, 98.4, 97.2])

#------------ frequency table --------------------
import pandas as pd

table = pd.crosstab(index = death , colnames = ["질병"], columns = '도수')
table.index = ["감염","각종암","순환기","호흡기","소화기",
               "사고사","비뇨기","정신병","노환","신경계"]
print(table)
#------------ bar plot --------------------

# 한글 입력
from matplotlib import font_manager, rc

font_name = font_manager.FontProperties(fname="C:/Windows/Fonts/malgun.ttf").get_name()
rc('font',family =font_name)
