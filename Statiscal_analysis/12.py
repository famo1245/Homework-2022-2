# n = 80 x_bar = 35 s = 4
import math
from scipy import stats

n = 80
x_bar = 35
s = 4
se = s / math.sqrt(n)
z_alpha90 = stats.norm.ppf(1-0.1/2)
interval90 = z_alpha90 * se
CI90 = [x_bar-interval90, x_bar+interval90]
print("신뢰구간: ", CI90)
print("(1) 신뢰구간은 정확히 계산되었다.")
print("(2) 90%의 신뢰 구간은 실제 평균을 포함하는 것이 아니라, 90% 확률로 신뢰 구간에 실제 평균이 있다는 뜻이다.")