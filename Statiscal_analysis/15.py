# n = 40 sigma = 2.2 mean = -6.5
import math
from scipy import stats

# (1) 
n = 40
mean = -6.5
std = 2.2
c = -7.1
se = std / math.sqrt(n)
z_alpha = (c - mean) / se
alpha = stats.norm.cdf(z_alpha);print("(1) 유의 수준: ", alpha)

# (2)
c_2 = (stats.norm.ppf(0.1) + mean) * se; print("(2) C: ", c_2)