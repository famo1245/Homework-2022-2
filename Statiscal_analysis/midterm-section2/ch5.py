
import numpy as np
import pandas as pd
import os
import matplotlib.pyplot as plt 


os.chdir("c:\\Temp")
os.getcwd()

# https://gibles-deepmind.tistory.com/110

class TossCoin:
 
    def __init__(self, array_size, prob, experiments_num):
        self.array_size = array_size
        self.prob = prob
        self.experiments_num = experiments_num
 
    def get_prob_array(self):
 
        prob_array = []
        for _ in range(self.experiments_num):
            toss_coin_array = np.random.choice(['0.head', '1.tail']
                                               , size=self.array_size
                                               , p=[self.prob, 1-self.prob])
            _, toss_coin_cnt = np.unique(toss_coin_array, return_counts=True)
            prob_array.append(toss_coin_cnt[0]/self.array_size)
 
        return prob_array

coin_instance_10 = TossCoin(10, 0.5, 100)
coin_instance_100 = TossCoin(100, 0.5, 100)
coin_instance_10000 = TossCoin(10000, 0.5, 100)
coin_instance_100000 = TossCoin(100000, 0.5, 100)

fig, axes = plt.subplots(2, 2, figsize=(15, 12))
axes[0, 0].hist(coin_instance_10.get_prob_array())
axes[0, 0].set_xlim([0, 1])
axes[0, 0].set_title("coin toss for 10 times")
 
axes[0, 1].hist(coin_instance_100.get_prob_array())
axes[0, 1].set_xlim([0, 1])
axes[0, 1].set_title("coin toss for 100 times")
 
axes[1, 0].hist(coin_instance_10000.get_prob_array())
axes[1, 0].set_xlim([0, 1])
axes[1, 0].set_title("coin toss for 1000 times")
 
axes[1, 1].hist(coin_instance_100000.get_prob_array())
axes[1, 1].set_xlim([0, 1])
axes[1, 1].set_title("coin toss for 10000 times")
plt.show()
