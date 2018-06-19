# Modulo: tree_search
# 
# Fornece um conjunto de classes para suporte a resolucao de 
# problemas por pesquisa em arvore:
#    SearchDomain  - dominios de problemas
#    SearchProblem - problemas concretos a resolver 
#    SearchNode    - nos da arvore de pesquisa
#    SearchTree    - arvore de pesquisa, com metodos para 
#                    a respectiva construcao
#
#  (c) Luis Seabra Lopes, Introducao a Inteligencia Artificial, 2012-2014


# Dominios de pesquisa
# Permitem calcular
# as accoes possiveis em cada estado, etc
class SearchDomain:
    # construtor
    def __init__(self):
        abstract

    # lista de accoes possiveis num estado
    def actions(self, state):
        abstract

    # resultado de uma accao num estado, ou seja, o estado seguinte
    def result(self, state, action):
        abstract

    # custo de uma accao num estado
    def cost(self, state, action):
        abstract

    # custo estimado de chegar de um estado a outro
    def heuristic(self, state, goal_state):
        abstract


# Problemas concretos a resolver
# dentro de um determinado dominio
class SearchProblem:
    def __init__(self, domain, initial, goal):
        self.domain = domain
        self.initial = initial
        self.goal = goal

    def goal_test(self, state):
        return state == self.goal


# Nos de uma arvore de pesquisa
class SearchNode:
    def __init__(self, state, parent, selfdepth=0, cost=0, heuristic=0):
        self.state = state
        self.parent = parent
        self.cost = cost
        self.selfdepth = selfdepth
        self.heuristic = heuristic

    def __str__(self):
        return "no(" + str(self.state) + "," + str(self.parent) + ")"

    def __repr__(self):
        return str(self)


# Arvores de pesquisa
class SearchTree:
    # construtor
    def __init__(self, problem, strategy='breadth', cost=0, transitions=0, maxDepth=-1):
        self.problem = problem
        root = SearchNode(problem.initial, None)
        self.open_nodes = [root]
        self.strategy = strategy
        self.costAcomu = cost
        self.totalCost = cost
        self.transitions = transitions
        self.maxDepth = maxDepth
        self.nonTerminalNodes = 0
        self.terminalNodes = 0

    # obter o caminho (sequencia de estados) da raiz ate um no
    def get_path(self, node):
        if node.parent == None:
            return [node.state]
        path = self.get_path(node.parent)
        path += [node.state]
        return path

    # procurar a solucao
    def search(self):
        while self.open_nodes != []:  # and self.open_nodes[0].selfdepth <= self.maxDepth:
            node = self.open_nodes[0]
            print(node,node.state,node.cost,node.heuristic)
            if self.problem.goal_test(node.state):
                self.terminalNodes += len(self.open_nodes)
                if self.maxDepth == None:
                    return [self.get_path(node), 'custo:', node.cost, 'custoTtl:', self.totalCost, 'transicoes:',
                            self.transitions, 'dept', node.selfdepth,self.terminalNodes,self.nonTerminalNodes]
                elif node.selfdepth <= self.maxDepth:
                    return [self.get_path(node), 'custo:', node.cost, 'custoTtl:', self.totalCost, 'transicoes:',
                            self.transitions, 'dept', node.selfdepth,self.terminalNodes,self.nonTerminalNodes]
                else:
                    return None
            if node.parent == None:
                depth = 0
                # print(node.selfdepth)
            else:
                depth = node.selfdepth
                # print(node,depth)
            self.open_nodes[0:1] = []
            lnewnodes = []
            for a in self.problem.domain.actions(node.state):
                newstate = self.problem.domain.result(node.state, a)
                # ex 5 ??
                self.totalCost = node.cost + self.problem.domain.cost('state', (node.state, newstate))
                if newstate not in self.get_path(node):
                    self.transitions += 1
                    # print(node.cost)
                    self.costAcomu = node.cost + self.problem.domain.cost('state', (node.state, newstate))
                    lnewnodes += [SearchNode(newstate, node, cost=self.costAcomu, selfdepth=depth + 1,
                                             heuristic=self.problem.domain.heuristic(newstate, self.problem.goal))]
                    # print(lnewnodes[0].selfdepth)
            self.nonTerminalNodes += 1
            self.add_to_open(lnewnodes)
        return None

    # juntar novos nos a lista de nos abertos de acordo com a estrategia
    def add_to_open(self, lnewnodes):
        if self.strategy == 'breadth':
            self.open_nodes.extend(lnewnodes)
        elif self.strategy == 'depth':
            self.open_nodes[0:0] = lnewnodes
        elif self.strategy == 'uniform':
            self.open_nodes = sorted(self.open_nodes + lnewnodes, key=lambda x: x.cost)
        elif self.strategy == 'greedy':
            self.open_nodes = sorted(self.open_nodes + lnewnodes, key=lambda x: x.heuristic)
        elif self.strategy == 'a*':
            self.open_nodes = sorted(self.open_nodes + lnewnodes, key=lambda x: x.heuristic+x.cost)