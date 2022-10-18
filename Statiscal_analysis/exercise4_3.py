import os
os.chdir("")
os.getcwd()

# 한글 입력
from matplotlib import font_manager, rc

font_name = font_manager.FontProperties(fname="C:/Windows/Fonts/malgun.ttf").get_name()
rc('font',family =font_name)


import pandas as pd
import numpy as np
soil_df = pd.read_csv('./5.4.csv',sep=",", header=None)
print(soil_df)
soil=np.array(soil_df)
print(soil)
# mud=np.array(soil[0:5,1])
# print(mud)
