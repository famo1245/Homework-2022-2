#module import
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
os.chdir("")
os.getcwd()

waiting_df = pd.read_csv('./6.13.CSV', sep=',',header=None)
print(waiting_df)

waiting = waiting_df.values.flatten()
waiting = waiting[:-3]
waiting_df=pd.DataFrame(waiting)
print(waiting_df.describe())

plt.figure()
plt.boxplot(waiting)    #오른쪽 꼬리가 긴 분포 나온다
plt.show()
