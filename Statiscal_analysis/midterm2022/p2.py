import numpy as np

amount = np.array([8.7,9.0,11.0,8.5,9.2,12.0,12.0,18.0])
satisfaction = np.array([25,25,26,48,65,87,90,100])
print(np.corrcoef(amount, satisfaction)[0][1])

# 마그네슘의 양과 맛에 대한 만족도의 상관 계수는
# 약 0.73이다
# 둘 사이에 양의 상관관계가 있으며, 강한 선형 관계가 있다
