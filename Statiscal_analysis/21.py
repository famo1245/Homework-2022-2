# n = ? mean = 370000 sigma = 40000
# (1) n = ?, 오차 1만원 이하 최소 95%
from scipy import stats
import math

mean = 370000
sigma = 40000
alpha = 0.05
d = 10000
z = stats.norm.ppf(1-alpha/2)
n = math.ceil(z * sigma / d)
print("(1) 표본의 크기: n >= ", n)

# (2) n = 100 x_bar = 373000 s = 39000
# 모평균에 대한 98% 신뢰구간
n = 100
x_bar = 373000
s = 39000
se = s / math.sqrt(n)
z = stats.norm.ppf(1-0.02/2)
interval98 = z * se
CI98 = [x_bar - interval98, x_bar+interval98]
print("모평균에 대한 98% 신뢰구간: ", CI98)