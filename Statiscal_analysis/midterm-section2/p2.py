import numpy as np
import pandas as pd

amount = np.array([8.7, 9.0, 11.0, 8.5, 9.2, 12.0, 12.0, 18.0])
satisfaction = np.array([25.0, 25.0, 26.0, 48.0, 65.0, 87.0, 90.0, 100.0])

print(np.corrcoef(amount, satisfaction)[0][1])

# 상관계수는 약 0.73, 마그네슘의 양과 맛에 대한 만족도 사이에는 강한 양의 선형관계가 있다