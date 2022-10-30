# 김지영 201810923
from scipy import stats

print("12번 이상 시도해야 할 확률: ", end='')
print(1-stats.poisson.cdf(11, 7*(10/7)))