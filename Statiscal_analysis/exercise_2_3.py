# module import

import os
import pandas as pd
from matplotlib import font_manager, rc
import matplotlib.pyplot as plt
import numpy as np

os.chdir("D:\\JIYEONG\\Study\\static_analysis")
os.getcwd()

#파일 읽기
df = pd.read_csv('2장-6-16.txt', header=None)
print(df)
ozone = np.array(df[0])
print(ozone)

# 히스토그램

plt.figure()
# bins == 계급의 수, output의 bins == 계급의 경계값, n == 각 계급구간의 도수의 수
n, bins, patches = plt.hist(ozone, bins = 12, facecolor="blue", alpha = 0.3)
print(n, bins)
#bins 사이의 평균값 구하기, 중앙점
x = [(bins[i]+bins[i+1])/2 for i in range(len(bins)-1)]   #도수다각형 
w_bin = bins[1]-bins[0]   #도수다각형 
# 맨 앞의 계급 추가
x.insert(0,x[0]-w_bin)   #도수다각형 
x.append(x[-1]+w_bin)   #도수다각형 
# 추가한 계급의 높의 정의
n = np.insert(n,0,0.0)   #도수다각형 
n = np.append(n,0.0)   #도수다각형 
plt.xlabel('ozone')
plt.ylabel('Frequency')
plt.title("Histogram of ozone")

#plt.figure()
#plt.title("도수다각형")
plt.plot(x,n,'red',marker='o')   #도수다각형 

#plt.figure()을 하지 않았으므로 히스토그램이랑 곂쳐서 출력
plt.savefig('./plot/그림e-2-3.png')
plt.show()