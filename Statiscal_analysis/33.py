# n = 250, lemon = 145, almond = 105
import math
from scipy import stats

# (1)
n = 250
p_l = 145 / 250
p_a = 105 / 250
se = math.sqrt(p_l * (1 - p_l) / n)
zval = (p_l - 0.5) / se
pval = (1 - stats.norm.cdf(zval)) * 2
print("(1) pval 이 ", pval, " 0.05보다 작으므로 선호도에는 차이가 없다")

# (2)
interval = stats.norm.ppf(1-0.05/2) * se
CI95 = [p_a - interval, p_a + interval]
print("(2) 신뢰구간: ", CI95)