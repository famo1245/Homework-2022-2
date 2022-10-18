# module import

import os
import pandas as pd
from matplotlib import font_manager, rc
import matplotlib.pyplot as plt
import numpy as np

os.chdir("")
os.getcwd()

#------------ data input --------------------
#1. 전적으로 동의 2. 조금 동의 3. 조금 반대 4. 전적으로 반대
survey = np.array([4,2,1,3,3,2,4,2,1,1,2,2,2,2,1,
                3,4,1,4,4,1,3,2,4,1,4,3,3,1,1,1,2,1,1,4,4,
                4,4,1,2,2,2,4,4,4,1,3,4,2])

#------------ frequency table --------------------

survey_result = pd.crosstab(index = survey , colnames = ["정도"], columns = '도수')
survey_result.index = ["전적으로 동의", "조금 동의", "조금 반대", "전적으로 반대"]
print(survey_result)

#------------ bar plot --------------------

# 한글 입력

font_name = font_manager.FontProperties(fname="C:/Windows/Fonts/malgun.ttf").get_name()
rc('font',family =font_name)

# 막대 그래프

plt.figure()
# legend는 범례 유무
survey_result.plot(kind='bar',legend=False, color="blue")
plt.xlabel('동의 정도')
plt.ylabel("도수")
plt.title("출석을 성적에 반영하여야 하는가?")
#------ 여기까지 기본적인 막대그래프-----
# 범주명의 회전
plt.xticks(rotation=0)

plt.savefig('./plot/그림e-2.png') # 그림 저장
plt.show()

#------------ pie plot --------------------
plt.figure()
index = ["전적으로 동의", "조금 동의", "조금 반대", "전적으로 반대"]
index = survey_result.index

# plt.pie로 명령 호출가능
plt.pie(survey_result['도수'],labels=index)
plt.rc('font', size=8) # 그래프 글자 크기

plt.title("출석을 성적에 반영하여야 하는가?")
plt.savefig('./plot/그림e-2-1.png')
plt.show()
