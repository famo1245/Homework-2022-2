from scipy import stats

print("1번 :", end=' ')
print(1-stats.binom.cdf(2, 100, 0.02))

print("2번 :", end=' ')
print(1-stats.poisson.cdf(2, 16))