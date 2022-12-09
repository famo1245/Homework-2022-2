# n = 170, p = 0.3
# H0 = 0.3, H1 != 0.3
from scipy import stats
import math

# (1)
n = 170
z = 0.06 / math.sqrt(0.3 * 0.7 / n)
alpha = (1 - stats.norm.cdf(z)) * 2; print("(1) 유의 수준 a = ", alpha)

# (2)
pr = 0.95
z = stats.norm.ppf(pr) * math.sqrt(0.3 * 0.7 / n)
print("(2) c: ", z)
