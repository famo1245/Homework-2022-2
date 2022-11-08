#정규분포함수

from scipy.stats import norm    # norm:표준정규분포
# print(norm.cdf(x=50,loc=63,scale=10))   # loc:평균, scale:분산, 연속형에서는 cdf써야함!
# print(norm.cdf(x=-1.3, loc =0, scale=1))    # 위와 같은 결과

# print(norm.cdf(x=1, loc =0, scale=1)) # 표준정규분포

# print(norm.ppf(q=0.9,loc=63,scale=10))  # 백분위점을 구하는 함수 ppf, q: 누적 비율

# print(norm.ppf(q=0.01,loc=0,scale=1)) # 표준정규분포에대한 제1백분위수

# ##예제12

import numpy as np
import statsmodels.api as sm
import matplotlib.pyplot as plt
 
# data = np.array([4001,3927,3048,4298,4000,3445,
#                 4949,3530,3075,4012,3797,3550,
#                 4027,3571,3738,5157,3598,4749,
#                 4263,3894,4262,4232,3852,4256,
#                 3271,4315,3078,3607,3889,3147,
#                 3421,3531,3987,4120,4349,4071,
#                 3683,3332,3285,3739,3544,
#                 4103,3401,3601,3717,4846,
#                 5005,3991,2866,3561,4003,
#                 4387,3510,2884,3819,3173,
#                 3470,3340,3214,3370,3694])
# sm.qqplot(data) # 중간값이용 가상의 백분위수를 구함
# plt.title("Normal Q-Q plot")
# plt.show()

# sm.qqplot(data,line='s') # line='s'는 추세선
# plt.title("Normal Q-Q plot")
# plt.show()
## 정규분포를 따른다고 할 수 있을 것 같다

##예제13

data = np.array([39.3,14.8,6.3,0.9,6.5,
                3.5,8.3,10.0,1.3,7.1,
                6.0,17.1,16.8,0.7,7.9,
                 2.7,26.2,24.3,17.7,3.2,
                 7.4,6.6,5.2,8.3,5.9,
                 3.5,8.3,44.8,8.3,13.4,
                 19.4,19.0,14.1,1.9,12.0,
                 19.7,10.3,3.4,16.7,4.3,
                 1.0,7.6,28.3,26.2,31.7,
                 8.7,18.9,3.4,10.0])

plt.hist(data, bins=5, range=(0,50))
plt.xlabel('data')
plt.ylabel('Density')
plt.title('Historam of data')
plt.show()

sm.qqplot(data,line='s')
plt.title("Normal Q-Q plot")
plt.show()

# histogram만 봐도 안따를 것 같음, qqplot을 보면 더 확실

# ##제곱근 변환

data2 = np.sqrt(data)
sm.qqplot(data2, line = 's')
plt.title("Normal Q-Q plot")


##네제곱근 변환

data4 = np.power(data,0.25)
sm.qqplot(data4, line = 's')
plt.title("Normal Q-Q plot")

plt.show()