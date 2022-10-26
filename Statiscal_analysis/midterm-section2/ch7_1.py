#chapter 7

from scipy import stats
import matplotlib.pyplot as plt
import numpy as np
#calculate cdf/pmf of binomial
print(stats.binom.cdf(10,200,0.04))
print(stats.binom.pmf(12,15,0.5))

#rv = stats.binom(n,p)
x = np.arange(0, 15)
plt.figure()
plt.plot(x, stats.binom.pmf(x, 15, 0.5), 'bo')
plt.vlines(x, 0, stats.binom.pmf(x, 15, 0.5), lw=2)
plt.xlabel('# of patients cured')
plt.ylabel('binomial')
plt.show()

#hypergeom
[M, n, N] = [20, 7, 12]
rv = stats.hypergeom(M, n, N)
x = np.arange(0, n+1)
pmf_dogs = rv.pmf(x)

plt.figure()
plt.plot(x, pmf_dogs, 'bo')
plt.vlines(x, 0, pmf_dogs, lw=2)
plt.xlabel('# of dogs in our group of chosen animals')
plt.ylabel('hypergeom PMF')
plt.show()

#calculate cdf of poisson


from scipy import stats
print(stats.poisson.cdf(10,8))

