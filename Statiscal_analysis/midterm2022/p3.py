import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
os.chdir("C:\\temp")
os.getcwd()

score_df = pd.read_csv('./q3.csv', sep=',', header=None)
score = np.array(score_df)
score = score.flatten()
target = np.percentile(score, [80])
print(target)
score_df = pd.DataFrame(score)
plt.figure()
plt.boxplot(score_df)
plt.show()
# 1) 오른쪽으로 편향된 분포이다
# 2) 기준 점수는 171.6점이다
