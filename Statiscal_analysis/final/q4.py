# 기말고사 실습 4번문제
#학번 , 이름을 출력하세요
import numpy as np
import math
from scipy import stats

birth = np.array([55,29,26,41])
rate = np.array([0.4,0.2,0.2,0.2])
n = sum(birth)
E = rate * n
chi2, p = stats.chisquare(birth, E)
#결과 출력
print(p, "가 유의 수준 0.1보다 크므로 1~3 월생이 다른 사분기에 비해 두배정도 많다는 주장은 맞다")
