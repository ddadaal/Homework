import numpy as np
from scipy import stats

# 均值

np.mean([1,2,3,4,5,6,7,8])

# 方差，分母是n

np.var([1,2,3])

# 样本方差，分母是n-1. ddof是自由度的偏移量，样本方差的自由度为n-1，比n少1，故为1

np.var([1,2,3],ddof=1)

# 标准差

np.std([1,2,3], ddof=0)

# 正态分布

# 积分
stats.norm.cdf(n) # 从-inf到n
stats.norm.sf(2.32) # 从n到+inf 
stats.norm.cdf(n) + stats.norm.sf(n) == 1

stats.norm.ppf(n) # 从表查n对应的x。上分位

stats.norm.interval(0.95, mean, std) # 获得双侧置信区间，alpha, mean, std

 # 获得当F(x)=0.99时，x的值=2.32；就是0.01上分位。下面两个相等
stats.norm.isf(0.01) # 是sf的逆
stats.norm.ppf(1-0.01)；# 是cdf的逆


# t分布

stats.t.isf(0.01,1) # t分布上分位表，这是t_0.01(1)=31.8205
-stats.t.ppf(0.01,1) # 记得有负号，这是t_0.01(1)=31.8205
stats.t.interval(0.95, 10, mean, std) # 获得双侧置信区间，alpha, n, mean, std


# 卡方分布
stats.chi2.isf(0.01,1)  # 卡方分布上分位表，这是x^2_0.01(1)=6.6348

# F分布

stats.f.isf(0.1,1,2) # alpha, n1, n2

# 离散分布，例如泊松分布。第一个是i，后面跟着的是参数。

stats.poisson.pmf(1,2) # 计算lambda=2时，P{X=1}。参数是[lambda]
 
stats.binom.pmf(1,3,1/4)  # 计算n=3, p=1/4，P{X=1}。参数是[n,p]

# 卡方拟合优度

stats.chisquare(data1,data2) # data2也是一个分布，但是好像不能用已有的分布，还是自己写把

# 卡方独立性检验

stats.chi2_contingency([[1,2,3],[1,2,3],[1,2,3]]) # 参数是列联表

# [0]卡方，[1] P值，[2]自由度

# K-S检验

stats.kstest([420,500,920,1380,1510,1650,1760,2100,2300,2350],"expon",arg=(1/1500,)) # 第一个参数数据，第二个参数是分布名，第三个参数是分布的参数。返回值第一个是D，第二个是P值

stats.ks_2samp([],[]) # 两组数据，第一个是D，第二个是P

# 秩和检验. 原假设是，两个数据从一个分布里来。进行了大样本逼近（n>10）。

scipy.stats.ranksums([134,146,104,119,124,161,107,83,113,129,97,123],[70,118,101,85,112,132,94])

# RanksumsResult(statistic=1.6903085094570331, pvalue=0.09096894797535775)