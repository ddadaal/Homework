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

stats.norm.cdf(0) # 0.5，积分

stats.norm.ppf(n) # 从表查n对应的x。上分位
# alpha=0.05, 单侧，对应的z-score
stats.norm.ppf(1-alpha)

# alpha=0.05, 双侧
stats.norm.ppf(1-alpha/2)

stats.norm.interval(0.95, mean, std) # 获得双侧置信区间，alpha, mean, std

# t分布

stats.t.isf(0.01,1) # t分布上分位表，这是t_0.01(1)=31.8205
-stats.t.ppf(0.01,1) # 记得有负号，这是t_0.01(1)=31.8205
stats.t.interval(0.95, 10, mean, std) # 获得双侧置信区间，alpha, n, mean, std


# 卡方分布
stats.chi2.isf(0.01,1)  # 卡方分布上分位表，这是x^2_0.01(1)=6.6348
