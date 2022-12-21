# 기말고사 실습 1번문제
#학번 , 이름을 출력하세요
from scipy import stats

print('학번 : 201810923    이름 : 김지영 ')
mean = 70
s = 8
zf = (58-mean)/s
zd = (66-mean)/s
zc = (74-mean)/s
zb = (82-mean)/s
F = stats.norm.cdf(zf)
d = stats.norm.cdf(zd) - F
c = stats.norm.cdf(zc) - stats.norm.cdf(zd)
b = stats.norm.cdf(zb) - stats.norm.cdf(zc)
a = 1-stats.norm.cdf(zb)
#결과 출력

print('F 비율: ', F , 'D 비율 : ', d,'C 비율 : ', c,'B 비율 : ', b,'A 비율 : ', a)