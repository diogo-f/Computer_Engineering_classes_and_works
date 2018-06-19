#mapa random
#neste student deu 6968 com food_quantity = 2
#morreram por nutrientes a 0
#mapa 4
#morreram em becos sem saida
#uma com 1284, outra 470
#total 1754

import copy

from agent import *
import random
import pickle


class StudentAgent(Agent):
    def __init__(self, name, body, world):
        self.name = name
        self.world = world
        self.worldMapDomain = WorldMap(self.world)

        self.body = body
        self.nutrients = {}
        self.age = 0
        self.timespent = 0.0

        self.path = None
        self.sonarPath = None
        self.goal = None
        self.goal_type = ""
        self.history = []
        self.visionFoodHistory = {}

        self.runaway = True

    def chooseAction(self, vision, msg):
        print("CA_self.body", self.body)
        self.worldMapDomain.body = self.body
        head = self.body[0]
        path = None
        action = None

        ##ve as comidas a volta e guarda-as no historico
        ##-ex: {Point(x=48, y=10): 'S', Point(x=30, y=30): 'S'}
        self.visionFoodHistory.update({x: vision.food[x] for x in vision.food})
        ##se estiver em cima de uma comida por acaso remove-a do historico
        print("CA_visionHistory depois de criar",self.visionFoodHistory)
        if head in self.visionFoodHistory:
            del self.visionFoodHistory[head]
        print("CA_visionHistory depois de remover se comer",self.visionFoodHistory)
        ##verifica a mensagem
        if msg != b"":
            message = pickle.loads(msg)
        else:
            message = []
        msg = b""

        if self.path != None:
            print("yo",self.path[-1].state)
            if len(self.path) > 2 and self.world.translate(head, self.path[2].action) not in vision.bodies and self.path[1].state == head:
                print('path!=None path[1:]')
                self.path = self.path[1:]
            else:
                print('else meter o path a none')
                self.path = None

        ##Verifica os niveis dos nutrientes
        if self.nutrients['M'] < 2000 or self.nutrients['S'] < 2000:
            print("if: verificar nutrientes")
            vision.food = self.getVisionFood(vision)
        # --------------------situações possiveis---------------------
        if len(vision.food) > 0 and len([f for f in vision.food if self.world.dist(head,f) < 5]) > 0:
            self.sonarPath = SearchTree(SearchProblem(self.worldMapDomain, head, vision.food), strategy='greedy', limit=5).search()
            if self.sonarPath != None and (self.path == None or len(self.sonarPath) < len(self.path)):
                self.path = self.sonarPath
                self.goal = self.path[-1]
                self.goal_type = 'food'
        # ------------------------------------------------------------
        # 1- se tiver comida na visao e nao tem selfPath ou o path que tem é para uma comida random
        # cria um path para uma food nova (new goal)
        if len(vision.food) > 0 and (self.path == None or self.goal_type == 'random'):
            self.getPathFoodGoal(vision, head)
        # ------------------------------------------------------------
        # 2- se tiver comida na visao MAS TEM path para outra comida,
        # aqui podemos adicionar a comida ao historico de comidas vistas(fiz no inicio, explico no comentario a seguir)
        # (o possivel sonar ficaria aqui??)
        # ALSO!: arranjar forma de apagar as comidas comidas por ela sem querer
        # e o random goal podia ter preferencia para sitios onde ja tinha visto comidas
        # como tinhas dito
        # senao, acho que se pode tirar daqui, o elif a seguir faz o mesmo!!!
        # ------------------------------------------------------------
        # 3- nao tem comida na visao mas ja tem um path calculado para outra posiçao(food or random)!
        elif len(vision.food) == 0 and self.path != None and self.goal_type == 'random':
            self.history += self.path[0].state
        # ------------------------------------------------------------
        # 4- nao ter comida na visao e nao ter nenhum path criado
        elif len(vision.food) == 0 and self.path == None:
            self.getPathRandomGoal(vision, head)
        # ----------------------------//------------------------------
        if self.path == None:
            # action = ACTIONS[0]
            action = random.choice(self.worldMapDomain.actions(head))
        else:
            action = self.path[1].action
        # testar se vai bater noutra snake
        if self.world.translate(head, action) in vision.bodies and not 's' in message:
            action = ACTIONS[0]
            msg = pickle.dumps({'s': True})

        return action, msg

    def getPathFoodGoal(self, vision, head, strategy='a_star', limit=None):
        path = SearchTree(SearchProblem(self.worldMapDomain, head, vision.food), strategy='a_star', limit=limit).search()
        if path != None:  # and not self.deadEnd(path, vision): ###TODO: do deadEnd()
            self.path = path
            self.goal = path[-1].state
            self.goal_type = 'food'
            self.history = []

    def getPathRandomGoal(self, vision, head,strategy='a_star', limit=None):
        self.goal = self.generateGoal()
        print(self.world.dist(head, self.goal), self.world.size.y // 2)
        print(self.world.dist(head, self.goal) > self.world.size.y // 2)
        print("qwe_HEAD,SELF.GOAL",head,self.goal)
        while self.world.dist(head, self.goal) < self.world.size.y // 2:
            self.goal = self.generateGoal()
        path = SearchTree(SearchProblem(self.worldMapDomain, head, [self.goal]), strategy=strategy).search()
        if path != None:  # and not self.deadEnd(path, vision): ###TODO: do deadEnd()
            self.path = path
            self.goal = path[-1].state
            self.goal_type = 'random'
            self.history = []

    def getVisionFood(self, vision):
        if self.nutrients['M'] < self.nutrients['S'] and 'M' in vision.food.values():
            return {x: vision.food[x] for x in vision.food if vision.food[x] == 'M'}
        elif self.nutrients['S'] < self.nutrients['M'] and 'S' in vision.food.values():
            return {x: vision.food[x] for x in vision.food if vision.food[x] == 'S'}
        else:
            return vision.food

    def generateGoal(self):
        return self.world.generatePos(forbiden=ChainMap(self.world.walls, self.body, self.history),
                                      preferred=[x for x in self.visionFoodHistory])


class WorldMap:
    def __init__(self, world):  # , vision):
        self.world = world
        self.body = None

    def actions(self, head):
        validact = ACTIONS[:1]
        for act in ACTIONS[1:]:
            newpos = self.world.translate(head, act)
            if newpos not in self.world.walls and newpos not in self.body:
                validact.append(act)
        return validact

    def result(self, head, action):
        return self.world.translate(head, action)

    def cost(self, state, act):
        return 1

    def heuristic(self, state, goals):
        distances_lst = [self.world.dist(state, goal) for goal in goals]
        return min(distances_lst)

    def deadEnd(self, path):
        if self.mode == 'dummy':
            return False
        self.mode = 'dummy'
        aux_body = copy.deepcopy(self.body)
        self.body = path[-1].state
        self.body += [pos for pos in self.vision.bodies if pos not in aux_body]
        dummy_tree = SearchTree(SearchProblem(self, self.body, [self.body[1]]), strategy='a_star')
        dummy_path = dummy_tree.search()
        self.body = copy.deepcopy(aux_body)
        self.mode = 'standard'
        # if dummy_path != None:
        # print("deadEnd: dummy_path = ", [n.state[0] for n in dummy_path])
        return dummy_path == None

class SearchProblem:
    def __init__(self, domain, initial, goals):
        self.domain = domain
        self.initial = initial
        self.goal = goals  # é dicionário (vision.food)

    def goal_test(self, state):
        return state in self.goal


class SearchNode:
    def __init__(self, state, parent):
        self.state = state
        self.parent = parent
        self.g = 0
        self.depth = 0
        self.h = 0
        self.action = (-50, -50)


class SearchTree:
    def __init__(self, problem, strategy='a_star', limit=None):
        self.problem = problem
        root = SearchNode(problem.initial, None)
        self.open_nodes = [root]
        self.strategy = strategy
        self.limit = limit
        self.visited = []

    def get_path(self, node):
        if node.parent == None:
            return [node]
        path = self.get_path(node.parent)
        path += [node]
        return path

    def search(self):
        while self.open_nodes != []:
            node = self.open_nodes[0]
            node_path = self.get_path(node)
            if self.problem.goal_test(node_path):
                return node_path
            self.open_nodes[0:1] = []
            lnewnodes = []

            for a in self.problem.domain.actions(node.state):
                newstate = self.problem.domain.result(node.state, a)
                if newstate not in self.visited:
                    self.visited += [newstate]
                    newnode = SearchNode(newstate, node)
                    newnode.action = a
                    if self.problem.goal_test(newstate):
                        return self.get_path(newnode)
                    newnode.h = self.problem.domain.heuristic(newstate, self.problem.goal)
                    newnode.depth = node.depth + 1
                    newnode.g = self.problem.domain.cost(node.state, a) + node.g
                    lnewnodes += [newnode]
            self.add_to_open(lnewnodes)
        return None

    def add_to_open(self, lnewnodes):
        if self.strategy == 'breadth':
            self.open_nodes.extend(lnewnodes)
        elif self.strategy == 'depth':
            self.open_nodes[0:0] = lnewnodes
        elif self.strategy == 'uniform':
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.g)
        elif self.strategy == "greedy":
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.h)
        elif self.strategy == "a_star":
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.h + node.g)
