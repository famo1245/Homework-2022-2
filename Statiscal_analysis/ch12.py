from scipy import stats

# print(stats.norm.cdf(4.06*2/52))

# stats.norm.ppf(0.6) # 정규분포, 좌측 누적값이 0.6인 z값찾기
# stats.norm.cdf(0.6) # 정규 분포 0.6에서 누적값
# stats.t.ppf(1-0.05/2, n1+n2-2) # qt in R
# stats.t.cdf(0.5, 5) # pt in R

import numpy as np
x = np.array([77, 77, 78, 78, 81, 81, 82, 82, 82, 82,
              82, 83, 83, 84, 84, 84, 84, 85, 86, 86,
              86, 86, 86, 87, 87, 87, 87, 87, 87, 87,
              89, 89, 89, 89, 89, 89, 89, 90, 90, 90,
              91, 91, 91, 91, 91, 91, 91, 91, 91, 91,
              93, 93, 93, 93, 93, 93, 94, 94, 94, 94,
              94, 94, 94, 94, 94, 94, 94, 94, 95, 95,
              95, 95, 95, 96, 96, 96, 96, 96, 96, 97,
              97, 97, 97, 97, 97, 97, 97, 97, 98, 99,
              100, 100, 100, 100, 100, 100, 100, 100,
              100, 101, 101, 101, 101, 101, 101, 102,
              102, 102, 102, 102, 102, 103, 103, 104,
              104, 104, 105, 107])
y = np.array([71, 72, 73, 74, 75, 77, 78, 79, 79, 79,
              79, 80, 80, 80, 81, 81, 81, 82, 82, 82,
              82, 84, 84, 84, 84, 84, 84, 85, 85, 85,
              85, 85, 85, 86, 86, 87, 88, 90, 91, 94])

print("기초통계량")
print("X: ", np.average(x), np.std(x, ddof=1), x.size)
print("Y: ", np.average(y), np.std(y, ddof=1), y.size)
# 표본의 크기가 클때

import math
var1 = np.var(x, ddof=1); print(var1)
var2 = np.var(y, ddof=1); print(var2)
n1 = len(x); print(n1)
n2 = len(y); print(n2)
se = math.sqrt(var1/n1 + var2/n2); print(se)

z_alpha = stats.norm.ppf(1 - 0.05/2); print(z_alpha)
interval_z = z_alpha * se; print(interval_z)
xbar1 = np.mean(x); print(xbar1)
xbar2 = np.mean(y); print(xbar2)
diff = xbar1 - xbar2; print(diff)
CI_1 = [diff - interval_z, diff + interval_z]; print(CI_1)

zval = (xbar1 - xbar2) / se; print(zval)
pval = 1 - stats.norm.cdf(zval); print(pval)
# Q.E.D

# 만약 t검정을 쓴다면?
import matplotlib.pyplot as plt
import statsmodels.api as sm
# sm.qqplot(x, line="r")
# plt.show()

# sm.qqplot(y, line="r")
# plt.show()
# qqplot으로 확인

# 합동추정량 및 표준오차 계산
import math
spooled = ((n1 - 1) * var1 + (n2 - 1) * var2) / (n1 + n2 - 2); print(spooled) # 합동 추정량 계산
se_spooled = math.sqrt(spooled) * math.sqrt(1/n1 + 1/n2); print(se_spooled) # 표준 오차

# 신뢰구간 계산, 신뢰구간의 폭이 조금 더 넓어짐
t_alpha = stats.t.ppf(1 - 0.05/2, n1 + n2 - 2); print(t_alpha)
interval_t = t_alpha * se_spooled; print(interval_t)
CI_2 = [diff - interval_t, diff + interval_t]; print(CI_2)

# 등분산성 가정 실패
interval_t2 = t_alpha * se
CI_3 = [diff - interval_t2, diff + interval_t2]; print(CI_3)