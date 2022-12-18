# 동질성 검사?
import numpy as np
from scipy import stats as st

support = np.array([[112, 36, 28],[84, 68, 72]])
chi22, p2, dof, expected = st.chi2_contingency(support)
print(chi22)

# 귀무 가설을 예산 삭감에 대한 의견이 지지 정당에 따라 다르지 않다고 둘 때,
# 유의 수준 0.05에서 귀무가설이 기각되고 대립가설이 채택된다
# 따라서 지지 정당에 따라 의견이 달라진다