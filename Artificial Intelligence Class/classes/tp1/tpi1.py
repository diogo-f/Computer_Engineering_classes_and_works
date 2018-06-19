#encoding: utf8
#Diogo Reis - 64231

from semnet import *


class MySemNet(SemanticNetwork):
    def __init__(self):
        SemanticNetwork.__init__(self)

    # local relations of a given entity, including
    # inverse relations
    def query_local_relations(self,e1,relname=None,e2=None):    #dependendo dos argumentos recebidos
        lst = self.query_local(e1=e1, relname=relname, e2=e2)   #lista de todas as relaçoes com a entidade 1
        lstInv = self.query_local(e1=e2,relname=relname,e2=e1)  #lista de todas as relaçoes da entidade 2 com a 1
        return list(set([(x.relation.name,x.relation.entity2) for x in lst] + \
                 [(self.inverse[x.relation.name],x.relation.entity1) for x in lstInv])) #Retorna uma lista de tuplos contendo:
                                                                                        #1º: o nome da relaçao e o nome da
                                                                                        # entidade com que faz essa relaçao
                                                                                        # dos resultados guardados em lst
                                                                                        #2º: O nome da relaçao inversa e o nome da
                                                                                        # entidade 1 dos resultados em lstInv

    # AssocSome associations of a given entity,
    # including inherited; inheritance is cancelled
    # by opposite relations;
    # If needed, you are free to use the optional argument "xpto"
    def query_excluding_opposites(self,entity,assocname,xpto=[]):
        if self.query_local(e1=entity,relname=assocname) == []:     #condiçao base quando recebe entidades
            return []                                               #que nao têm a associaçao recebida retorna []
        xpto += [x.relation.entity2 for x in self.query_local(e1=entity,relname=self.opposite[assocname])]      #adiciona a xpto todas as entidades2 que têm uma associaçao oposta à recebida com a entidade1
        temp = [x.relation.entity2 for x in self.query_local(e1=entity,relname=assocname) if x.relation.entity2 not in xpto]    #temp guarda as entidades2 que estao associadas a entidade1 excepto se estiver guardada em xpto
        lstRelations = [x.relation.entity2 for x in self.query_local(e1=entity,relname='member')] +\
                  [x.relation.entity2 for x in self.query_local(e1=entity,relname='subtype')]       #guarda as ent2 de todas as relaçoes do tipo member e subtype com o argumento recebido em entity
        rec = []    #todas as relaçoes em lstRelations vao chamar recursivamente a funcao e vai verificar outra vez tudo por cada uma,
        for x in lstRelations:
            rec += self.query_excluding_opposites(x,assocname,xpto)
        return list(set(temp+rec))      #returna a lista de todas as ent2 das associaçoes com a ent1 filtrando as associaçoes opostas

    # a general query method, processing different types of
    # relations according to their specificities
    def query(self,entity,relname):
        pass