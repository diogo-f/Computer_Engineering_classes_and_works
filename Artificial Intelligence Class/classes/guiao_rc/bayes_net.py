class BayesNet:
    def __init__(self, ldep={}):
        self.dependencies = ldep

    # Os dados estao num dicionario (var,dependencies)
    # em que as dependencias de cada variavel
    # estao num dicionario (mothers,prob);
    # "mothers" e' um frozenset de pares (mothervar,boolvalue)
    def add(self, var, mothers, prob):
        self.dependencies.setdefault(var, {})[frozenset(mothers)] = prob

    # Probabilidade conjunta de uma dada conjuncao
    # de valores de todas as variaveis da rede
    def jointProb(self, conjunction):
        prob = 1.0
        for (var, val) in conjunction:
            for (mothers, p) in self.dependencies[var].items():
                # print(mothers,p)
                if mothers.issubset(conjunction):
                    prob *= (p if val else 1 - p)
        return prob

    def probIndiv(self, var,val):
        # print(self.dependencies)
        conjFinal = []
        # probInd = 0.0
        for (mothers,prob) in self.dependencies[var].items():
            # print((var,mothers,prob))
            for x,y in mothers:
                conjFinal.append(self.getList(x,y))

                conj += [(x,y)]
                probInd += self.jointProb(conj)
                # self.probIndiv(x, y, probInd)
        # print(conj)
        for x in conjFinal:
            prob += self.jointProb(x)
        return prob

    def getList(self,var,val):
        for (mothers,prob) in self.dependencies[var].items():
            # print((var,mothers,prob))
            for x,y in mothers:
                self.getList(x,y)