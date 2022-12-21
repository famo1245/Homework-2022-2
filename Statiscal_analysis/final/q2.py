# 기말고사 실습 2번문제
#학번 , 이름을 출력하세요
import math
from scipy import stats

print('학번 : 201810923    이름 : 김지영 ')
n = 5
x_bar = 115
mean = 120
s = 7
se = s / math.sqrt(n)
t = (x_bar - mean) / se
pvalue = 1-stats.t.cdf(t, n-1)

#결과 출력
print("P-값: ", pvalue, " 이 유의수준 0.05보다 크므로 과장광고가 아님")
