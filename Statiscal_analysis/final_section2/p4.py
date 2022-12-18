# n = 6, m = 90, a = 0.05, t분포
import numpy as np
import math
from scipy import stats
n = 6
score = np.array([89, 74, 91, 88, 72, 84])
x_bar = np.mean(score); print(x_bar)
s = np.std(score, ddof=1); print(s)
se = s / math.sqrt(n)
t_value = (x_bar - 90) / se
p_value = stats.t.cdf(t_value, n - 1)
print(p_value)

# 한종대의 주장은 타당하다