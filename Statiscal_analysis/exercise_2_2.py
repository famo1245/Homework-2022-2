# module import

import os
import pandas as pd
from matplotlib import font_manager, rc
import matplotlib.pyplot as plt
import numpy as np

os.chdir("")
os.getcwd()
 
 #파일 읽기
df = pd.read_csv('./BMI-example.txt',sep = '\t',header=None)    #sep는 seperator
print(df)
height = np.array(df[0])
weight = np.array(df[1])
print(height)
print(weight)

# table1 = pd.crosstab(index = death , colnames = ["질병"], columns = '도수')
# table1.index = ["감염","각종암","순환기","호흡기","소화기",
#                "사고사","비뇨기","정신병","노환","신경계"]
# print(table)
