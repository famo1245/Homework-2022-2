


import numpy as np
import pandas as pd
import os
import matplotlib.pyplot as plt 


os.chdir("D:\\JIYEONG\\Study\\static_analysis")
os.getcwd()


#P.M.F
num_throws = 10000
outcomes = np.zeros(num_throws)
for i in range(num_throws):
    # let's roll the die
    outcome = np.random.choice(['1', '2', '3', '4', '5', '6'])
    outcomes[i] = outcome


val, cnt = np.unique(outcomes, return_counts=True)  # outcomes 안의 고유값을 세주는 함수
# print(val, cnt)   # val 1,2,3,4,5,6 / cnt 각각의 횟수
prop = cnt / len(outcomes)

# Now that we have rolled our die 10000 times, let's plot the results, 대수의 법칙에 따라 thorws변수가 커지면 확률도 비슷해짐
plt.bar(val, prop)
plt.ylabel("Probability")
plt.xlabel("Outcome")
plt.show()
plt.close()



