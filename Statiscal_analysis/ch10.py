#10_8
import numpy as np
import math
height=np.array([163,161,168,161,157,162,153,159,164,170,
                 152,160,157,168,150,165,156,151,162,150,
                 156,152,161,165,168,167,165,168,159,156])
#10_9
# xbar_h=np.mean(height);print("x_bar : ", xbar_h)    # 표본일 때, ddof(자유도) 1을 넣어줘야 함
# var_h=np.var(height);print(var_h)
# sd_h=np.std(height,ddof=1);print(sd_h)
# median_h=np.median(height);print(median_h)
# min_h=np.min(height);print(min_h)
# max_h=np.max(height);print(max_h)
# sum_h=np.sum(height);print(sum_h)
# n=height.size;print(n)
# print("======")

#신뢰구간
from scipy import stats
# se_h=stats.sem(height);print("점추정에 대한 표준오차 S/rootN: " , se_h) # sd_h / math.sqrt(n)과 같다, 평균에 대한 표준 오차(sem method )
# # z_alpha=stats.norm.ppf(1-0.05/2);print("Za/2 : ",  z_alpha)
# z_alpha=stats.norm.ppf(1-0.1/2);print("Za/2 : ",  z_alpha)
# interval=z_alpha*se_h;print(interval)
# # CI=[xbar_h-interval,xbar_h+interval];print("CI95 : ", CI) # confidence interval
# CI=[xbar_h-interval,xbar_h+interval];print("CI90 : ", CI)

# ## 10-4

# sem = 8 / 5
# interval_2 = 1.96 * sem
# CI_2 = [42.7-interval_2, 42.7+interval_2];print("CI 95: ", CI_2)

## 10-5
# print(stats.norm.ppf(0.95))
# print(stats.norm.ppf(0.975))
# print(stats.norm.ppf(0.995))

## 사과농장
# se_g = 20 / math.sqrt(36)
# z_alpha95 = stats.norm.ppf(1-0.05/2)
# z_alpha90 = stats.norm.ppf(1-0.1/2)
# interval95 = z_alpha95 * se_g
# interval90 = z_alpha90 * se_g
# CI95 = [342-interval95, 342+interval95]
# CI90 = [342-interval90, 342+interval90]
# print(CI95)
# print(CI90)

#10_11
# zval=(xbar_h-159)/se_h;print("Z value: ", zval)

#10_12
# pval=2*(1-stats.norm.cdf(zval));print("p-value : ", pval) #양측 확률의 경우

# 체중
# print(stats.norm.cdf(0.83))
# print(stats.norm.cdf(-2.10))
# print(stats.norm.cdf(-2.635))

# 실습 치료법
# seh_c = 1.1 / math.sqrt(80);
# zval_c = (4.5 - 4.2) / seh_c;
# print(zval_c)
# pval_c = (1-stats.norm.cdf(zval_c)); print("p-value : ", pval_c)

#평균교통소음
noise=np.array([55.9,63.8,57.2,59.8,65.7,62.7,60.8,51.3,61.8,56.0,
                66.9,56.8,66.2,64.6,59.5,63.1,60.6,62.0,59.4,67.2,
                63.6,60.5,66.8,61.8,64.8,55.8,55.7,77.1,62.1,61.0,
                58.9,60.0,66.9,61.7,60.3,51.5,67.0,60.2,56.2,59.4,
                67.9,64.9,55.7,61.4,62.6,56.4,56.4,69.4,57.6,63.8
                ])  # data가 이상치면? 정책에 따라 다름...

xbar_n=np.mean(noise); print("X bar: ", xbar_n)
var_n=np.var(noise); print("var : ", var_n)
sd_n=np.std(noise, ddof=1); print("sd : ", sd_n)
print("n :", noise.size)
se_n = sd_n / math.sqrt(noise.size)
z_alpha98 = stats.norm.ppf(1-0.02/2)
interval98 = z_alpha98 * se_n
CI98 = [xbar_n-interval98, xbar_n+interval98]
print("CI98: ", CI98)
zval_n = (xbar_n - 60) / se_n; print("z-value: ", zval_n, ", ", stats.norm.ppf(0.95))
pval_n = (1-stats.norm.cdf(zval_n)); print("p-value : ", pval_n)

#z-test(np.array, m, alternative) => (Z, p) z값, p값 으로 구성된 tuple이 결과, 값을 쉽게 구할 수 있다
from statsmodels.stats.weightstats import ztest as ztest
print(ztest(noise, value=60, alternative="larger"))     # alternative: larger, smaller은 단측 검정 각각 m<m0 m>m0, two-sided는 양측검정 m != m0
