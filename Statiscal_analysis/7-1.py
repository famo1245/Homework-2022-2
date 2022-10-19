from scipy import stats # scipy에서 부분적으로 import
import matplotlib.pyplot as plt
import numpy as np

#calculate cdf/pmf of binomial

# caculate tree
# print(stats.binom.pmf(0, 5, 0.25)) # x, n, p
# print(stats.binom.pmf(4, 5, 0.25) + stats.binom.pmf(5, 5, 0.25))
# print(1 - stats.binom.cdf(3, 5, 0.25))  #cdf x이하인 확률, 누적밀도함수

# hospital
# rv_patient = stats.binom(15, 0.5)
# print(rv_patient.cdf(6))  # 아래와 같은 함수임
# print(stats.binom.cdf(6, 15, 0.5))
# print(stats.binom.cdf(10, 15, 0.5) - stats.binom.cdf(5, 15, 0.5))
# print(1 - stats.binom.cdf(11, 15, 0.5))

# print(stats.binom.cdf(10,200,0.04)) # binomial distribution 함수 있음
# print(stats.binom.pmf(12,15,0.5))   # pmf == 확률질량함수

#rv = stats.binom(n,p)  # 확률변수를 만드는 코드
# visualization of hospital case
# x = np.arange(0, 16)
# plt.figure()
# plt.plot(x, stats.binom.pmf(x, 15, 0.5), 'bo')  # 점 그래프
# plt.vlines(x, 0, stats.binom.pmf(x, 15, 0.5), lw=2)
# plt.xlabel('# of patients cured')
# plt.ylabel('binomial')
# plt.show()

# game X ~B(100, 0.01)
# print(stats.binom.pmf(0, 100, 0.005))

#hypergeom

# case of car
# [M, n, N] = [48, 8, 12]
# rv_car = stats.hypergeom(M, n, N)
# x = np.arange(0, n+1)
# pmf_cars = rv_car.pmf(x)
# print(1-rv_car.pmf(0))

# case of animal
# [N, D, n] = [20, 7, 12]
# rv = stats.hypergeom(N, D, n)
# x = np.arange(0, n+1)
# pmf_dogs = rv.pmf(x)
# print(pmf_dogs)

# plt.figure()
# plt.plot(x, pmf_dogs, 'bo')
# plt.vlines(x, 0, pmf_dogs, lw=2)
# plt.xlabel('# of dogs in our group of chosen animals')
# plt.ylabel('hypergeom PMF')
# plt.show()

#calculate cdf of poisson

# case of phone use poisson distribution
# print(1- stats.poisson.pmf(0, 3/5))

# case of students who are color blindness
# print(stats.poisson.cdf(10,8))  # x, m

# prcatice 1
# print("1건의 사고도 발생하지 않을 확률")
# print(stats.poisson.pmf(0,5))
# print("5건 이상의 발생할 확률")
# print(1-stats.poisson.cdf(4,5))

# practice 2
# print("10분간 샤워를 하는 사이에 전화벨이 울릴 확률")
# print(1-stats.poisson.pmf(0, 2/6))
print("몇분?")
for i in range(1, 61):
    if((1-stats.poisson.pmf(0, 2/(60/i)))>0.5):
        maximum=i-1
        break

print("최대 : " + str(maximum) + "분")