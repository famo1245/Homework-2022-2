import os
os.chdir("D:\\JIYEONG\\Study\\static_analysis")
os.getcwd()

# 한글 입력
from matplotlib import font_manager, rc

font_name = font_manager.FontProperties(fname="C:/Windows/Fonts/malgun.ttf").get_name()
rc('font',family =font_name)


import pandas as pd
import numpy as np
survey_df = pd.read_csv('./5.2.csv',sep=",", header=None)
# index={'인상', '불변', '인하'}
# survey_df=pd.Dataframe(index, index=['본인', '대리인'])
survey=np.array(survey_df)
survey_self=np.array(survey[0,:])
survey_rep=np.array(survey[1,:])
print(survey_self)
print(survey_rep)
total=survey_self[0]+survey_self[1]+survey_self[2]
survey_self=survey_self/total
print(survey_self)
total=survey_rep[0]+survey_rep[1]+survey_rep[2]
survey_rep=survey_rep/total
print(survey_rep)

# excel로 하는거임..