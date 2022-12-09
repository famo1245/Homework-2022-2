import numpy as np
import scipy.stats as stats
import math

# # 예제 11-4
# bacteria=np.array([175,190,215,198,184,207,210,193,196,180])
# #11_10
# xbar_b=np.mean(bacteria); print("X bar: ", xbar_b)
# var_b=np.var(bacteria,ddof=1)
# sd_b=np.std(bacteria,ddof=1); print("sd: ", sd_b)
# median_b=np.median(bacteria)
# min_b=np.min(bacteria)
# max_b=np.max(bacteria)
# sum_b=np.sum(bacteria)
# n=bacteria.size; print("n: ", n)

# # qqplot 찍어보기
import matplotlib.pyplot as plt
import statsmodels.api as sm
# # sm.qqplot(bacteria,line="r")    # 가진 정보로는 아마 정규분포를 따른다고 판단
# # plt.show()

# # t분포 사용, CI90
# se_b=stats.sem(bacteria);print("standard error: ", se_b)
# t_alpha=stats.t.ppf(1-0.1/2,n-1);print("t-alpha0.05: ", t_alpha)
# interval=t_alpha*se_b;print(interval)
# CI=[xbar_b-interval,xbar_b+interval];print("CI90 : ", CI)

# # t-value 구하기
# tval_b=(xbar_b-200)/se_b;print("t-value: ", tval_b)   # tval_b * se_b = xbar_b - m
# pval_b=stats.t.cdf(tval_b,n-1);print("p-value: ", pval_b)

# 실습1
x=np.array([31,35,37,38,38,38,39,40,40,41,42,43,44,44,46,48])
# 정규성 검정
sm.qqplot(x,line="r")
plt.show()
n_x = x.size; print("n: ", n_x) # n이 30보다 작고, 모집단이 정규분포를 따르며, 모표준편차를 모르므로 t-분포 사용
xbar_x = np.mean(x); print("X bar: ", xbar_x)
sd_x = np.std(x, ddof=1)
se_x=stats.sem(x);print(se_x)
tval_x=(xbar_x - 38) / se_x; print("t-value: ", tval_x)
pval_x = stats.t.cdf(tval_x, n_x-1); print("p-value: ", pval_x)     # 유의수준 0.05보다 작으므로, 귀무가설(m=38)을 기각하지 못함
