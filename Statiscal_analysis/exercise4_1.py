import os
os.chdir("D:\\JIYEONG\\Study\\static_analysis")
os.getcwd()

import pandas as pd
import numpy as np
score_df = pd.read_csv('./score.csv',sep=",")
math = np.array(score_df["math"])
english = np.array(score_df["english"])
print(np.corrcoef(math, english)[0][1])

import matplotlib.pyplot as plt

plt.figure()
plt.scatter(math, english, color ='black') # scatter() 산점도를 그려주는 함수
plt.xlabel('math')
plt.ylabel('english')
plt.title('math and english')
plt.savefig('./plot/ex4_1.png')
plt.show()