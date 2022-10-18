# module import

import os
os.chdir("")
os.getcwd()

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
#import numpy as np
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
# print(table)
#------------ bar plot --------------------

# 한글 입력
from matplotlib import font_manager, rc

font_name = font_manager.FontProperties(fname="C:/Windows/Fonts/malgun.ttf").get_name()
rc('font',family =font_name)


# 막대 그래프
import matplotlib.pyplot as plt

plt.figure()
# legend는 범례 유무
table.plot(kind='bar',legend=False, color="blue")
plt.xlabel('사망원인')
plt.ylabel("빈도수")
plt.title("사망원인에 따른 막대그래프")
#------ 여기까지 기본적인 막대그래프-----
# 범주명의 회전
plt.xticks(rotation=0)
plt.yticks(rotation=90)

# plt.savefig('./plot/그림2.png') # 그림 저장
# plt.show()

#------------ pie plot --------------------
import matplotlib.pyplot as plt

plt.figure()
index = ["감염","각종암","순환기","호흡기","소화기",
          "사고사","비뇨기","정신병","노환","신경계"]
index = table.index

# plt.pie로 명령 호출가능
plt.pie(table['도수'],labels=index)
plt.rc('font', size=8) # 그래프 글자 크기

plt.title("사망원인에 대한 원형 그래프")
plt.savefig('./plot/그림21.png')
plt.show()

# 히스토그램

import matplotlib.pyplot as plt

plt.figure()
# bins == 계급의 수, output의 bins == 계급의 경계값, n == 각 계급구간의 도수의 수
n, bins, patches = plt.hist(drink, bins = 10,facecolor="blue", alpha = 0.3)
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
plt.xlabel('drink')
plt.ylabel('Frequency')
plt.title("Histogram of drink")

#plt.figure()
#plt.title("도수다각형")
plt.plot(x,n,'red',marker='o')   #도수다각형 

#plt.figure()을 하지 않았으므로 히스토그램이랑 곂쳐서 출력
plt.savefig('./plot/그림26.png')
plt.show()
