from scipy.stats import chi2, poisson

i=[0,1,2,3,4,5,6,7,8,9,10,11,12]
ni=[1,5,16,17,26,11,9,9,2,1,2,1,0]

n=100


# calculate mean
sum=0
for x in range(13):
    sum+=i[x]*ni[x]

mean=sum/n


# merge

ni=[6,16,17,26,11,9,9,6]
pi=

# estimate lambda
l = mean

# calculate statistic value
chi2_1=-n
for x in range(13):
    oi=ni[x]
    ei=poisson.pmf(i[x],l)
    chi2_1 += oi**2/ei

# boundary

chi2_2=chi2.isf(0.05,n-1)

print(chi2_1, chi2_2)