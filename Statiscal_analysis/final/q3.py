# 기말고사 실습 3번문제
#학번 , 이름을 출력하세요
import numpy as np
from scipy import stats
import math

print('학번 : 201810923    이름 : 김지영 ')

x = np.array([12,29,16,37,28,15])
o = np.array([10,28,17,35,25,16])
x_bar = np.mean(x)
o_bar = np.mean(o)
sd = np.std(o-x, ddof=1)
se = sd / math.sqrt(6)
t = se * (o_bar - x_bar); print(t)
p = stats.t.cdf(t, 5); print(p)

#결과 출력
print("효과없음")
