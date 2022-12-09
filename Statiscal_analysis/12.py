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
print("(2) 여러번 추출하여 신뢰구간을 구했을 때, 모수를 포함하는 구간의 비율이 90%라는 의미이므로, 알 수 없다.")