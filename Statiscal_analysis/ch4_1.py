import numpy as np

height = np.array([181,161,170,160,158,168,162,179,183,178,171,177,163,158,160,160,158,
                   173,160,163,167,165,163,173,178,170,167,177,175,169,152,158,160,160,
                   159,180,169,162,178,173,173,171,171,170,160,167,168,166,164,173,180])

weight = np.array([78,49,52,53,50,57,53,54,71,73,55,73,51,53,65,48,59,
                   64,48,53,78,45,56,70,68,59,55,64,59,55,38,45,50,46,
                   50,63,71,52,74,52,61,65,68,57,47,48,58,59,55,74,74])

# 각각의 배열의 idx가 같은 사람을 가르켜야함, 두 변수의 자료를 사용할 때 순서를 바꾸는 것에 유의
#calculate the correlation

import numpy as np
print(np.corrcoef(height,weight)[0][1]) # 상관계수를 구하는 함수, [height weight] [height weight] 처럼 만들어서 각 변수별로 상관관계를 구해줌
# [0][1]이 weight * height 간의 상관관계 [1][0]도 같은 값, 원래 여러 변수 사이에서 가장 밀접한 관계를 찾는 용도

#draw the scatter plot


import matplotlib.pyplot as plt

plt.figure()
plt.scatter(height, weight, color ='black') # scatter() 산점도를 그려주는 함수
plt.xlabel('height(cm)')
plt.ylabel('weight(kg)')
plt.title('height(cm) and weight(kg)')
plt.show()

# import os
# os.chdir("")
# os.getcwd()

# import pandas as pd
# score_df = pd.read_csv('./score.csv',sep=",")
# print(score_df["math"])
