
# Guiao de representacao do conhecimento
# -- Redes semanticas
#
# Introducao a Inteligencia Artificial
# DETI / UA
#
# (c) Luis Seabra Lopes, 2012-2015
# v1.7 - 2015/10/13
#


# Classe Relation, com as seguintes classes derivadas:
#     - Association - uma associacao generica entre duas entidades
#     - Subtype     - uma relacao de subtipo entre dois tipos
#     - Member      - uma relacao de pertenca de uma instancia a um tipo
#

class Relation:
    def __init__(self ,e1 ,rel ,e2):
        self.entity1 = e1
        #       self.relation = rel  # obsoleto
        self.name = rel
        self.entity2 = e2
    def __str__(self):
        return self.name + "(" + str(self.entity1) + "," + \
               str(self.entity2) + ")"
    def __repr__(self):
        return str(self)


# Subclasse Association
class Association(Relation):
    def __init__(self ,e1 ,assoc ,e2):
        Relation.__init__(self ,e1 ,assoc ,e2)


# Exemplo:
#   a = Association('socrates','professor','filosofia')

# Subclasse Subtype
class Subtype(Relation):
    def __init__(self ,sub ,super):
        Relation.__init__(self ,sub ,"subtype" ,super)


# Exemplo:
#   s = Subtype('homem','mamifero')

# Subclasse Member
class Member(Relation):
    def __init__(self ,obj ,type):
        Relation.__init__(self ,obj ,"member" ,type)


# Exemplo:
#   m = Member('socrates','homem')

# classe Declaration
# -- associa um utilizador a uma relacao por si inserida
#    na rede semantica
#
class Declaration:
    def __init__(self ,user ,rel):
        self.user = user
        self.relation = rel
    def __str__(self):
        return "decl("+ str(self.user) + "," + str(self.relation) + ")"

    def __repr__(self):
        return str(self)


# Exemplos:
#   da = Declaration('descartes',a)
#   ds = Declaration('darwin',s)
#   dm = Declaration('descartes',m)

# classe SemanticNetwork
# -- composta por um conjunto de declaracoes
#    armazenado na forma de uma lista
#
class SemanticNetwork:
    def __init__(self, ldecl=[]):
        self.declarations = ldecl

    def __str__(self):
        return my_list2string(self.declarations)

    def insert(self, decl):
        self.declarations.append(decl)

    def query_local(self, user=None, e1=None, rel=None, e2=None):
        self.query_result = \
            [d for d in self.declarations
             if (user == None or d.user == user)
             and (e1 == None or d.relation.entity1 == e1)
             and (rel == None or d.relation.name == rel)
             and (e2 == None or d.relation.entity2 == e2)]
        return self.query_result

    def show_query_result(self):
        for d in self.query_result:
            print(str(d))

    #ex1
    def predecessor(self,ent1,ent2):
        if ent1 == ent2:
            return True
        lst = self.query_local(e1=ent2,rel='member') + self.query_local(e1=ent2,rel='subtype')
        for rel in lst:
            return self.predecessor(ent1,rel.relation.entity2)
        return False
    #ex2
    def predecessor_path(self,ent1,ent2):
        if ent1 == ent2:
            return [ent1]
        lst = self.query_local(e1=ent2,rel='subtype') + self.query_local(e1=ent2,rel='member')
        for rel in lst:
            return self.predecessor_path(ent1,rel.relation.entity2) + [ent2]
    #ex3
    def assocLst(self):
        return list(set([x.relation.name for x in self.declarations if x.relation.name!='member' and x.relation.name!='subtype']))
    #ex4
    #ex5
    def users(self):
        return list(set([x.user for x in self.declarations]))
    #ex6
    def tipos(self):
        return list(set([x.relation.entity2 for x in self.declarations if x.relation.name=='subtype']))
    #ex7
    def assoc(self,ent1):
        return list(set([x.relation.name for x in self.declarations if x.relation.entity1==ent1]))
    #ex8
    def userRel(self,user):
        return list(set([x.relation.name for x in self.declarations if x.user==user]))
    #ex9
    def userRelCount(self,user):
        return len(self.userRel(user))
    #ex10
    def entUserAssoc(self,ent1):
        return [(x.relation.name,x.user) for x in self.query_local(e1=ent1)]
    #ex11_a
    def query(self,ent1,assoc=None):
        # if self.query_local(e1=ent1) == []:
        #     return []
        lst = [x.relation.entity2 for x in self.query_local(e1=ent1) if
               x.relation.name == 'member' or x.relation.name == 'subtype']
        rec = []
        for x in lst:
            rec += self.query(x, assoc) + [x for x in self.query_local(e1=x, rel=assoc)]
        return rec
    # return [self.query(x, assoc) + [x for x in self.query_local(e1=x, rel=assoc)] for x in lst]
    #ex11_b
    def query2(self, ent1, assoc=None):
        # if self.query_local(e1=ent1) == []:
        #     return []
        lst = [x.relation.entity2 for x in self.query_local(e1=ent1) if
               x.relation.name == 'member' or x.relation.name == 'subtype']
        rec = []
        for x in lst:
            rec += self.query2(x, assoc) + [x for x in self.query_local(e1=x, rel=assoc)]
        return rec
    #ex12
    def query_cancel(self,ent1,assoc,lstRec = []):
        lst = [x.relation.entity2 for x in self.query_local(e1=ent1) if
               x.relation.name == 'member' or x.relation.name == 'subtype']
        lstRec += [x.relation.name for x in self.query_local(e1=ent1) if x.relation.name not in lstRec]
        rec = []
        for x in lst:
            rec += [x for x in self.query_local(e1=x, rel=assoc)
                    if x.relation.name not in lstRec] + self.query_cancel(x, assoc)
        return rec

# Funcao auxiliar para converter para cadeias de caracteres
# listas cujos elementos sejam convertiveis para
# cadeias de caracteres
def my_list2string(list):
    if list == []:
        return "[]"
    s = "[ " + str(list[0])
    for i in range(1, len(list)):
        s += ", " + str(list[i])
    return s + " ]"

