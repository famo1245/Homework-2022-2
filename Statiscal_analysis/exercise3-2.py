#module import
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
os.chdir("")
os.getcwd()

employee_df = pd.read_csv('./6.14.CSV', sep=',',header=None)
print(employee_df)

employee = employee_df.values.flatten()
print(employee_df.describe())

plt.figure()
plt.boxplot(employee)
plt.show()
