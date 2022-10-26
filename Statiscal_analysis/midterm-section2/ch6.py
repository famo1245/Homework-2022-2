


import numpy as np
import pandas as pd
import os
import matplotlib.pyplot as plt 


os.chdir("c:\\Temp")
os.getcwd()


#P.M.F
num_throws = 10000
outcomes = np.zeros(num_throws)
for i in range(num_throws):
    # let's roll the die
    outcome = np.random.choice(['1', '2', '3', '4', '5', '6'])
    outcomes[i] = outcome


val, cnt = np.unique(outcomes, return_counts=True)
prop = cnt / len(outcomes)

# Now that we have rolled our die 10000 times, let's plot the results
plt.bar(val, prop)
plt.ylabel("Probability")
plt.xlabel("Outcome")
plt.show()
plt.close()



