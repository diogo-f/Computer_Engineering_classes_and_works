from constraintsearch import *


def pos_constraint(r1, c1, r2, c2):
    return not c1 == c2
    # if c1 == c2:
    #     return False
    # return True


def make_constraint_graph(n):
    # pos = ['P' + str(i + 1) for i in range(n)]
    return {(pos1, pos2): pos_constraint for pos1, pos2 in n}


def make_domains(n):
    domain = ['R' + str(i + 1) for i in range(n)]
    cols = [i + 1 for i in range(n)]
    return {r: cols for r in domain}


map1 = [('a', 'b'), ('a', 'e'), ('a', 'd'), ('b', 'e'), ('b', 'c'), ('d', 'e'), ('d', 'c'), ('d', 'e')]
map2 = [('a', 'b'), ('a', 'e'), ('a', 'd'), ('b', 'e'), ('b', 'c'), ('d', 'e'), ('d', 'f'), ('c', 'e'), ('c', 'f'),
        ('f', 'e')]
map3 = [('a', 'b'), ('a', 'f'), ('a', 'e'), ('a', 'd'), ('b', 'f'), ('b', 'c'), ('c', 'f'), ('c', 'g'), ('c', 'g'),
        ('d', 'e'), ('d', 'g'), ('f', 'e'), ('f', 'g'), ('d', 'c'), ('g', 'e')]

cs = ConstraintSearch(make_domains(4), make_constraint_graph(4))

print(cs.search())
