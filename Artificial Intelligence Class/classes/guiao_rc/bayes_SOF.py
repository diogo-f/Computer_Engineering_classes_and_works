from bayes_net import *

bn = BayesNet()

bn.add('sobrecarga',[],0.600)
bn.add('sof2013',[],0.050)

bn.add('caraPreocupada',[('sobrecarga',True),('ajuda',False)],0.010)
bn.add('caraPreocupada',[('sobrecarga',True),('ajuda',True)],0.020)

bn.add('caraPreocupada',[('sobrecarga',False),('ajuda',False)],0.001)
bn.add('caraPreocupada',[('sobrecarga',False),('ajuda',True)],1.100)

bn.add('acumEmail',[('sobrecarga',False)],0.001)
bn.add('acumEmail',[('sobrecarga',True)],0.900)

bn.add('ajuda',[('sof2013',True)],0.250)
bn.add('ajuda',[('sof2013',False)],0.004)

bn.add('ratoExtra',[('ajuda',True),('sof2013',False)],0.100)
bn.add('ratoExtra',[('ajuda',False),('sof2013',False)],0.010)

bn.add('ratoExtra',[('ajuda',False),('sof2013',True)],0.900)
bn.add('ratoExtra',[('ajuda',True),('sof2013',True)],0.900)

conjunction = [('ajuda',True),('sobrecarga',True),('sof2013',True),('caraPreocupada',True),('acumEmail',True),('ratoExtra',True)]
print(bn.jointProb(conjunction))

conjunction = [('ajuda',False),('sobrecarga',False),('sof2013',False),('caraPreocupada',False),('acumEmail',False),('ratoExtra',False)]
print(bn.jointProb(conjunction))


# print(bn.probIndiv('acumEmail',True))

#rato -> 0,054842
print(bn.probIndiv('ratoExtra',True))
# print("\n",bn.probIndiv('ajuda',False))