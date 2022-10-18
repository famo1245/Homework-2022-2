#------------ descriptive summary --------------------

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
os.chdir("D:\\JIYEONG\\Study\\static_analysis")
os.getcwd()

drink = np.array([101.8, 101.5, 101.8, 102.6, 101, 96.8, 102.4, 100, 98.8, 98.1,
                  98.8, 98, 99.4,95.5, 100.1, 100.5, 97.4, 100.2, 101.4, 98.7,
                  101.4, 99.4, 101.7, 99, 99.7, 98.9, 99.5, 100, 99.7, 100.9,
                  99.7, 99, 98.8, 99.7, 100.9, 99.9, 97.5, 101.5, 98.2, 99.2,
                  98.6, 101.4, 102.1, 102.9, 100.8, 99.4, 103.7, 100.3, 100.2, 101.1,
                  101.8, 100, 101.2, 100.5, 101.2, 101.6, 99.9, 100.5, 100.4, 98.1,
                  100.1, 101.6, 99.3, 96.1, 100, 99.7, 99.7, 99.4, 101.5, 100.9,
                  101.3, 99.9, 99.1, 100.7, 100.8, 100.8, 101.4, 100.3, 98.4, 97.2])

# length
count = len(drink)
print(count)
# mean
mean = np.mean(drink)   #평균을 구함
print(mean)
# median
median = np.median(drink)     #중앙값을 구함
print(median)
# quantile n-tile 분위수
per = np.percentile(drink,[0,25,50,75,100])
print(per)
# var 분산
var = np.var(drink)
print(var)
# std 표준편차
std = np.std(drink, ddof=1)
print(std)  #모표준편차 구함 ddof가 default일 때, 1을 넣으면 표본표준편차 구해줌
# min, max, range
min, max = np.min(drink), np.max(drink)
print(min,max)
range = max - min
print(range)
# IQR
q75, q25 = np.percentile(drink, [75, 25])
print(q75,q25)
IQR = q75 - q25
print(IQR)

# 10백분위수,90백분위수
per01_09 = np.percentile(drink,q=[10,90])
print(per01_09)

# summary
drink_df = pd.DataFrame(drink)
summary = drink_df.describe()
print(summary)

print('길이     :' , count,'\n',
      '평균     :' , mean,'\n',
      '분산     :' , var , '\n',
      '표준편차 :' , std, '\n',
      '중앙값   :' , median ,'\n',
      '최댓값   :' , max  , '\n',
      '최솟값   :' , min  , '\n',
      '제1사분위수 :', q25, '\n',
      # '제2사분위수 :', q50, '\n',
      '제3사분위수 :', q75, '\n',
      '사분위수범위 :' ,IQR,'\n'
      )


plt.figure()
plt.boxplot(drink)
plt.show()