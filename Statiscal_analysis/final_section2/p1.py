# 1. n = 980, phat = 0.78, p = 0.75?
# H0 p == 0.75
import math
from scipy import stats as st

n = 980
phat = 0.78
p = 0.75
se = math.sqrt(p * (1 - p) / n)
z_value = (phat - p) / se
p_value = st.norm.cdf(1 - z_value)
print("P-value : ", p_value)

# 유의 수준이 0.12 보다 클 경우 성인의 75% 이상이 찬성할 수 있다고 주장할 수 있다.