# 3 t 분포 사용 n = 10
# CI95 [36.2, 45.8]
from scipy import stats
x_bar = (36.2 + 45.8) / 2
se = (45.8 - x_bar) / (stats.t.cdf(1 - 0.25, 9))
zval = stats.t.cdf(1 - 0.02/2, 9)
interval = zval * se
CI98 = [x_bar - interval, x_bar + interval]
print("98% 신뢰구간: ",CI98)
