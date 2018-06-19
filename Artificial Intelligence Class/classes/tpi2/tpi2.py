#Diogo Reis
#nmec 64231

from tree_search import *
from bayes_net import *


# -------------------------------------------------------------

class MyBN(BayesNet):
    def individual_probabilities(self):
        # IMPLEMENTAR AQUI
        pass


# -------------------------------------------------------------

class MyTree(SearchTree):
    def __init__(self, problem, strategy):
        super().__init__(problem, strategy=strategy)
        root = self.open_nodes[0]
        root.arg3 = 0
        root.arg4 = self.problem.domain.heuristic(root.state, self.problem.goal)
        self.solution_cost = 0
        self.total_nodes = 1
        self.open_nodes_bid = []

    # uniform, adiciona a lista e ordena pelo menor custo de cada node | arg3 = cost
    def uniform_add_to_open(self, lnewnodes):
        self.open_nodes = sorted(self.open_nodes + lnewnodes, key=lambda x: x.arg3)

    # greedy, adiciona a lista e ordena pela menor heuristica          | arg4 = heuristic
    def greedy_add_to_open(self, lnewnodes):
        self.open_nodes = sorted(self.open_nodes + lnewnodes, key=lambda x: x.arg4)

    # procurar a solucao
    def search2(self):
        while self.open_nodes != []:
            node = self.open_nodes[0]
            # print(node,node.state,node.arg3,node.arg4)
            self.open_nodes[:1] = []
            if self.problem.goal_test(node.state):
                self.solution_cost = node.arg3
                return self.get_path(node)
            self.add_to_open(self.getNewNodes(node))
        return None

    # procurar a solucao: pesquisa em arvore bi-direccional
    def bidirectional_search(self):
        # criar o no raiz(goal) para a pesquisa inversa e a lista de nos abertos
        root_bid = SearchNode(self.problem.goal, None)
        root_bid.arg3 = 0
        root_bid.arg4 = self.problem.domain.heuristic(root_bid.state, self.problem.initial)
        self.open_nodes_bid = [root_bid]

        while self.open_nodes != [] and self.open_nodes_bid != []:
            node = self.open_nodes[0]
            node_bid = self.open_nodes_bid[0]
            self.open_nodes[:1] = []
            self.open_nodes_bid[:1] = []
            # ----------------arvore normal----------------
            # testa as condiçoes de paragem
            if self.problem.goal_test(node.state):
                return self.get_path(node)
            elif any(x == node.state for lists in [self.get_path(x) for x in self.open_nodes_bid] for x in lists):
                # neste caso tem de somar a raiz(goal) da pesquisa inversa
                self.total_nodes += 1
                return self.pathBidFinal(node, self.open_nodes_bid)
            # pesquisa do no inicial para o goal
            self.add_to_open(self.getNewNodes(node))
            # ----------------------------------------------
            # ----------------arvore inversa----------------
            # testa as condiçoes de paragem
            if node_bid.state == self.problem.initial:
                return self.get_path(node_bid)
            elif any(x == node_bid.state for lists in [self.get_path(x) for x in self.open_nodes] for x in lists):
                # neste caso tem de somar a raiz da pesquisa inversa
                self.total_nodes += 1
                return self.pathBidFinal(node_bid, self.open_nodes)
            # pesquisa do goal para o no inicial
            self.add_to_open_bid(self.getNewNodes(node_bid))
            # ----------------------------------------------
        return None

    def getNewNodes(self, node):
        actions = self.problem.domain.actions(node.state)
        lnewnodes = []
        for a in actions:
            newstate = self.problem.domain.result(node.state, a)
            if newstate not in self.get_path(node):
                self.total_nodes += 1
                nodeCost = node.arg3 + self.problem.domain.cost(node.state, (node.state, newstate))
                newnode = SearchNode(newstate, node, arg3=nodeCost,
                                     arg4=self.problem.domain.heuristic(newstate, self.problem.goal))
                lnewnodes += [newnode]
        return lnewnodes

    def pathBidFinal(self, node, lst):
        # ------------------------------------------------
        aaa = sorted([x for x in lst if x.state == node.state], key=lambda x: x.arg3)
        for x in aaa:
            print(x,x.arg3)
        # ------------------------------------------------
        node_temp = sorted([x for x in lst if x.state == node.state], key=lambda x: x.arg3)[0]
        if node.arg3 < node_temp.arg3:
            self.solution_cost = node.arg3 + node_temp.arg3
            path = self.get_path(node) + list(reversed(self.get_path(node_temp.parent)))
        else:
            self.solution_cost = node.arg3 + node_temp.arg3
            path = self.get_path(node.parent) + list(reversed(self.get_path(node_temp)))
        if lst == self.open_nodes_bid:
            return path
        else:
            return list(reversed(path))

    def add_to_open_bid(self, lnewnodes):
        if self.strategy == 'breadth':
            self.open_nodes_bid.extend(lnewnodes)
        elif self.strategy == 'depth':
            self.open_nodes_bid[:0] = lnewnodes
        elif self.strategy == 'uniform':
            self.open_nodes_bid = sorted(self.open_nodes_bid + lnewnodes, key=lambda x: x.arg3)
        elif self.strategy == 'greedy':
            self.open_nodes_bid = sorted(self.open_nodes_bid + lnewnodes, key=lambda x: x.arg4)


# racios medios de custo e numero de nos
# para uma dada estrategia de pesquisa num
# dado conjunto de problemas
def profile_strategy(strategy, problems):
    g = 0
    n = 0
    for problem in problems:
        treeOpt = MyTree(problem, 'uniform')  # arvore custo uniforme(optima)
        treeOpt.search2()
        tree = MyTree(problem, strategy)  # arvore com estrategia recebida
        tree.search2()
        g += tree.solution_cost / treeOpt.solution_cost  # ratio dos custos
        n += tree.total_nodes / treeOpt.total_nodes  # ratio dos nos
    return g / len(problems), n / len(problems)  # return da media dos racios