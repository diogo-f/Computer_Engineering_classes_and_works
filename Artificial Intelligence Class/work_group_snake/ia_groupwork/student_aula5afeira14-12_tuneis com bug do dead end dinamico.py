from agent import *
import random
import pickle
import copy


class StudentAgent(Agent):
    def __init__(self, name, body, world):
        self.name = name
        self.world = world

        # cria as listas dos dead ends e dos tuneis
        self.DeadEndWalls = []
        self.tunnels = {}
        self.tunnels_aux = []

        self.in_tunnel = {}
        self.forbidden_tunnels = {}
        self.tempForbiddenGoals = {}
        self.tunnel_ent = None
        self.tunnel_out = None

        self.scanMap()

        #self.getDeadEndWalls()
        self.worldMapDomain = WorldMap(self.world, self.DeadEndWalls)

        self.body = body
        self.nutrients = {}
        self.age = 0
        self.timespent = 0.0
        self.my_horizon = 20

        self.path = None
        self.sonarPath = None
        self.goal = None
        self.goal_type = 'random'
        self.history = {}
        self.staticFoodHistory = {}

        self.other_goal = None
        self.food_type = None
        self.searched_vision_food = False
        self.previous_outbox = None
        self.previous_goal = None

        # self.foodfield

        #self.getDeadEndWalls()

    def scanMap(self):
        dead_ends = []
        tunnels_temp = []
        for x in range(self.world.size.x):
            for y in range(self.world.size.y):
                if (x, y) not in ChainMap(self.DeadEndWalls, self.world.walls):
                    neighbours = len(self.getNeighbours((x, y)))
                    if neighbours == 1:
                        dead_ends += [(x, y)]
                    if neighbours == 2:
                        tunnels_temp += [(x, y)]
        # percorrer todos os dead ends e gerar o dead end completo quando necessario
        for dead_end in dead_ends:
            self.getDead_ends(dead_end)
        print("--------------", len(self.DeadEndWalls), "dead ends--------------")
        print("dead ends ->", self.DeadEndWalls)
        # percorrer todos os possiveis tuneis e chamar o self.getTunnels() por cada um
        # este vai gerar os tuneis que encontrar(tuneis de tamanho 1 para a frente)
        for tunnelPos in tunnels_temp:
            if tunnelPos not in self.tunnels_aux:
                self.tunnel_ent = None
                self.tunnel_out = None
                newTunnel = self.getTunnels(tunnelPos, tunnels_temp)
                print("newTunnel", newTunnel)
                if newTunnel != []:
                    self.tunnels[self.tunnel_ent] = self.tunnel_out, len(newTunnel)
                    self.tunnels[self.tunnel_out] = self.tunnel_ent, len(newTunnel)
                    self.tunnels_aux += newTunnel
        self.tunnels_aux = []
        print("---------------", len(self.tunnels) // 2, "tuneis/2----------------")
        print("tunnel ->", self.tunnels)

    def getNeighbours(self, pos, actions=ACTIONS[1:], exclude=[]):
        neighs = []
        for act in actions:
            neighbour = self.world.translate(pos, act)
            if neighbour not in ChainMap(self.world.walls, self.DeadEndWalls,
                                         exclude):  # and neighbour in possibleTunnels: ##pensar depois## o self.tunnels tem de ser pesquisado de outra forma
                neighs.append(tuple(neighbour))
        return neighs

    # retirar os tuneis de len(1), nunca se metem por causa de recalcular outro path!!
    def getTunnels(self, pos, possibleTunnels, final_tunnel=None):
        if final_tunnel == None:
            final_tunnel = [pos]
        neighs = self.getNeighbours(pos, exclude=final_tunnel)
        if any(x not in possibleTunnels for x in neighs):
            if self.tunnel_ent == None and self.tunnel_out == None:
                self.tunnel_ent = pos
            elif self.tunnel_ent != None and self.tunnel_out == None:
                self.tunnel_out = pos
        if all(x not in possibleTunnels for x in neighs):
            return []
        else:
            for neigh in neighs:
                if neigh in possibleTunnels and neigh not in final_tunnel:
                    final_tunnel += [tuple(neigh)]
                    final_tunnel += self.getTunnels(neigh, possibleTunnels, final_tunnel=final_tunnel)
            return list(set(final_tunnel))

    def getDead_ends(self, pos):
        neighbours = self.getNeighbours(pos)
        if len(neighbours) < 2:
            if pos not in self.DeadEndWalls:
                self.DeadEndWalls[pos] = True
            if neighbours != []:
                return self.getDead_ends(neighbours[0])

    def chooseAction(self, vision, msg):
        self.worldMapDomain.body = copy.deepcopy(self.body)
        self.worldMapDomain.vision = vision
        self.worldMapDomain.other_body = []
        head = self.body[0]
        self.other_body = [pos for pos in vision.bodies if vision.bodies[pos] != self.name]
        # print("self.other_body", self.other_body)
        action = None
        self.inbox = msg
        self.outbox = {}
        self.searched_vision_food = False

        # - - - - - - - - - - - - - - Sorts food - - - - - - - - - - - - - -
        # Dead-end Filter
        vision.food = self.filterFoodbyDeadEnds(vision.food)

        self.staticFoodHistory.update({x: vision.food[x] for x in vision.food if vision.food[x] == 'S'})

        # 1st Nutrient Filter
        # if self.nutrients['M']/self.nutrients['S'] > 1.4 or self.nutrients['S']/self.nutrients['M'] > 1.4:
        #    vision.food = self.filterFoodbyNutrients(vision.food)

        nearby_food = {f: vision.food[f] for f in vision.food if self.world.dist(head, f) < 8}

        # 2nd Nutrient Filter                                                                                       ###decidir se e para haver 2 food filters
        # if self.nutrients['M']/self.nutrients['S'] > 1.2 or self.nutrients['S']/self.nutrients['M'] > 1.2:
        #    vision.food = self.filterFoodbyNutrients(vision.food)
        # - - - - - - - - - - - - - - - - // - - - - - - - - - - - - - - - -
        # - - - - - - - - Tuneis - - - - - - - --
        # zerar os tuneis que ja nao estao proibidos, como esta so funciona com duas snakes no jogo, so ha um tunel proibido de cada vez
        if self.age in self.forbidden_tunnels:
            # self.worldMapDomain.forbidden_tunnels = []
            del self.worldMapDomain.forbidden_tunnels_ent[self.forbidden_tunnels[self.age]]
            del self.worldMapDomain.allowed_tunnels_ent[self.tunnels[self.forbidden_tunnels[self.age]][0]]
            del self.forbidden_tunnels[self.age]
            print("self.worldMapDomain.forbidden_tunnels_ent-->", self.worldMapDomain.forbidden_tunnels_ent)
            print("self.worldMapDomain.allowed_tunnels_ent---->", self.worldMapDomain.allowed_tunnels_ent)
            print("self.forbidden_tunnels--------------------->", self.forbidden_tunnels)

        ###DEBUG PRINTS
        print("\n- - - - - - - - - - - - - - - chooseAction - - - - - - - - - - - - - - -")
        print('\n> > > PLAYER: ', self.name, ', head = ', (head[0], head[1]), ', nutrients = ', self.nutrients, "age", self.age, "in_tunnel?", self.in_tunnel,
              '\n> > > vision.food = ', vision.food, 'other_body = ', self.other_body)
        if self.goal != None:
            print(' > > > goal = ', self.goal, ', type = ', self.goal_type, ', len(path) = ', len(self.path), '\n')
        else:
            print('> > >goal = None\n')

        print("self.forbidden_tunnels                ->",self.forbidden_tunnels)
        print("self.worldMapDomain.forbidden_tunnels ->",self.worldMapDomain.forbidden_tunnels_ent)
        print("self.worldMapDomain.allowed_tunnels   ->",self.worldMapDomain.allowed_tunnels_ent)
        print("self.worldMapDomain.allow_exits NUNCA TRUE", self.worldMapDomain.allow_exits)

        if self.goal_type == 'random':
            # print("HAS random path/action, adds current state to self.history")
            self.history[head] = True
            if self.path != None:
                print('Following random path')
            else:
                ('random action, not following a path (should rarely be happening)')

        # Agent is following a previously calculated path -> updates it
        if self.path != None:  ###do some shenanigans for M food: i path[-1] not in vision and dist(head,path[-1]) < 19, screw around with path etc etc
            if len(self.path) > 2:  ###or do shadow_goal for M food
                if self.path[1].state[0] == head:
                    self.path = self.path[1:]
                    # print("shortened self.path")
            else:
                # print('reached goal, resets a bunch of stuff')
                self.path = None
                self.goal = None
                self.goal_type = None

        # Other agent is in vision; takes caution about body hits
        ###if self.path!=None and self.world.translate(head, self.path[1].action) in self.other_body or \
        ###    self.path==None and len(self.other_body)==2 and min(self.world.dist(head,self.other_body[0]), self.world.dist(head, self.other_body[1]))==1:
        if self.other_body != []:
            if min([self.world.dist(head, x) for x in self.other_body]) == 1:
                if self.goal != None and self.world.translate(head, self.path[1].action) in vision.bodies:
                    self.path = None
                    self.goal = None
                    self.goal_type = None
                    self.food_type = None  ###TODO:pensar mais sobre optimizar qd path!=None mas sonar etc E PATH = NONE SO QD BATE COM A CURRENT ACTION
                self.worldMapDomain.other_body = self.other_body  ###TODO:por isto no fim?

        print('\n««« Inbox')
        # - - - - - - - - - - - - - - - Inbox - - - - - - - - - - - - - - -
        # Unpacks message
        if self.inbox != b"":
            self.inbox = pickle.loads(self.inbox)
        else:
            self.inbox = {}

        # 'f' --> other agent ate food
        if 'f' in self.inbox:
            # print('inbox[f] = ', self.inbox['f'])

            self.other_goal = None
            eaten_food = self.inbox['f']

            if self.goal == eaten_food:  # vs if self.goal != None and self.world.dist(self.goal, eaten_food) < 2
                self.path = None
                self.goal = None
                self.goal_type = None
                self.food_type = None

            if self.inbox['f'] in self.staticFoodHistory:
                del self.staticFoodHistory[eaten_food]

        # 'g' --> other agent set a goal for himself
        if 'g' in self.inbox:
            # print('inbox[g] = ', self.inbox['g'])

            # updates other_goal's position
            if self.other_goal != None and self.world.dist(self.other_goal[0], self.inbox['g'][0]) < 2:
                self.other_goal = self.inbox['g'] + [self.other_goal[2], self.age]
            else:
                self.other_goal = self.inbox['g'] + [False, self.age]

            # cant be sure if both players are following the same goal; recalculates path/goal if len(food) > 0
            if self.goal != None and self.world.dist(self.goal, self.other_goal[0]) > 1 and not any(
                    [True for f in vision.food if self.world.dist(f, self.goal) < 2]) \
                    and self.goal_type == 'food' and self.food_type == 'M' or self.goal == None:
                # print('cant be sure if both players are following the same goal; recalculates path/goal if len(food) > 0')
                self.path = None
                self.goal = None
                self.goal_type = None
                self.food_type = None

                if len(vision.food) > 0:
                    self.getPathFoodGoal(vision.food)
                    self.searched_vision_food = True
                    if self.goal != None:
                        print('found a path; goal = ', self.goal)

                    else:
                        print('didnt find a path')

                        # has an updated or semi-updated goal; checks if his and the other agent's goal are next to each other or if they are the same goal;
            # finds out who has a shorter path
            if self.goal != None and self.world.dist(self.goal, self.other_goal[0]) < 2:
                # print('self.goal = other_goal')
                if len(self.path) < self.inbox['g'][1]:
                    # print('meu path e mais curto -> my len = ', len(self.path))
                    self.other_goal = None
                    if len(self.path) > 2 and self.other_body != []:
                        self.outbox['g'] = [self.goal, len(self.path)]  ###mandar current nutrients?
                        # print('outbox[g] = ', self.outbox['g'])
                    else:
                        if 'g' in self.outbox:  ####################
                            del self.outbox['g']
                        # print('len(path) = 2 --> g wont be in outbox')
                        # if len(self.path) > 2:
                        # self.outbox['g'] = [self.goal, len(self.path)]
                        # print('outbox[g] = ', self.outbox['g'])
                else:
                    # print('path do outro e mais curto -> my len = ', len(self.path))
                    self.path = None
                    self.goal = None
                    self.goal_type = None
                    self.food_type = None
                    self.other_goal[2] = True
                    if 'g' in self.outbox:
                        del self.outbox['g']

            else:
                print("self.goal != other_goal[0]] and/or self.goal = None --> this agent isn't after the other one's food")
                print('current goal = ', self.goal)
                # if self.goal != None and len(self.path)>2 and self.other_body != []:
                #    self.outbox['g']= [self.goal, len(self.path)]          ###mandar current nutrients para ajudar a decidir quem vai?
                #    print('in Inbox: outbox[g] = ', self.outbox['g'])
                # else:
                #    print('in Inbox: self.goal = None, should go straight to getrandompath()')
        # - - - - - - tunel recebido - - - - - - -
        if 't' in self.inbox:
            other_tunnel_start = self.inbox['t']
            other_tunnel_exit = self.tunnels[other_tunnel_start][0]  # other_tunnel_exit is a tuple, (exit_position, len_tunnel)
            other_tunnel_len = self.tunnels[other_tunnel_start][1]
            print("ENTROU NUM TUNEL\ntunel in inbox | start->", other_tunnel_start, "exit->", other_tunnel_exit,"len->",other_tunnel_len)
            self.forbidden_tunnels[self.age + other_tunnel_len] = other_tunnel_exit     #  tinha: self.age + other_tunnel_len
            self.worldMapDomain.forbidden_tunnels_ent[other_tunnel_exit] = other_tunnel_start
            self.worldMapDomain.allowed_tunnels_ent[other_tunnel_start] = other_tunnel_exit
            if self.path:
                index_start_in_path = None
                index_exit_in_path = None
                if other_tunnel_start in self.path:
                    index_start_in_path = self.path.index(other_tunnel_start)
                if other_tunnel_exit in self.path:
                    index_exit_in_path = self.path.index(other_tunnel_exit)
                if index_exit_in_path != None and index_start_in_path == None or index_start_in_path != None and index_exit_in_path != None and index_start_in_path > index_exit_in_path:
                    print("tunel | self path vai ser none, saida do tunel em primeiro no caminho!!")
                    self.path = None
                    self.goal_type = None
                    self.goal = None
                    self.food_type = None
                    # AQUI falta reset a alguma coisa??
            print("inbox atualizar forbidden_tunnels ")
            print("self.worldMapDomain.forbidden_tunnels_ent-->", self.worldMapDomain.forbidden_tunnels_ent)
            print("self.worldMapDomain.allowed_tunnels_ent---->", self.worldMapDomain.allowed_tunnels_ent)
            print("self.forbidden_tunnels--------------------->", self.forbidden_tunnels)

        # - - - - - - - - - - - - - - - - // - - - - - - - - - - - - - - - -
        print('»»»\n')

        # Updates domain
        # print('updated other_goal = ', self.other_goal)
        self.worldMapDomain.other_goal = self.other_goal

        # Filters food by goals prioritized to the other agent
        if self.other_goal != None and self.other_goal[2]:
            vision.food = {f: vision.food[f] for f in vision.food if
                           self.world.dist(f, self.other_goal[0]) > 1 + self.age - self.other_goal[3]}
            nearby_food = {f: nearby_food[f] for f in nearby_food if
                           self.world.dist(f, self.other_goal[0]) > 1 + self.age - self.other_goal[3]}

        # - - - - - - - - - - - Sonar - - - - - - - - - - -
        if any(nearby_food) and not self.searched_vision_food and (
                    self.path == None or self.path != None and self.goal not in vision.food or self.goal_type == 'random'):
            print('ENTERED SONAR')
            print('nearby_food before sonar: ', nearby_food)
            self.getPathFoodGoal(nearby_food, limit=8, strat='greedy')
            print('nearby_food after sonar: ', nearby_food)
        # - - - - - - - - - - - - - - - - - - - - - - - - -



        # --------------------situações possiveis---------------------
        # 1-----------------------------------------------------------
        # ve comida e nao tem um path definido ou, se tem, este leva a um goal que nao e comida; procura um path para comida
        if len(vision.food) > 0 and (self.path == None or self.goal_type != 'food') and not self.searched_vision_food:
            # print('vision.food before standard search: ', vision.food)
            # print('SEES food and HAS random path/no path, calls getPathFoodGoal')
            self.getPathFoodGoal(vision.food)
            # print('returned from getPathFoodGoal')
            # print('vision.food after standard search: ', vision.food)
        # 2-----------------------------------------------------------
        # nao ve comida mas ja viu comida que nao chegou a comer; procura um path para essa comida
        if self.path == None and len(self.staticFoodHistory) > 0:
            # print('searches a path to food in staticFoodHistory')
            if self.other_goal != None and self.other_goal[2] and self.other_goal[0] in self.staticFoodHistory:  ###or could just delete it regardless and be efficient
                uneaten_static_food = {f: self.staticFoodHistory[f] for f in self.staticFoodHistory if
                                       self.world.dist(f, self.other_goal[0]) > 1}
            else:
                uneaten_static_food = dict(self.staticFoodHistory)
            # print('staticFoodHistory = ', self.staticFoodHistory)
            # print('uneaten_static_food = ', uneaten_static_food)
            self.getPathFoodGoal(uneaten_static_food)
        # ir para self.foodfield/movingFoodHistory/staticfoodhistory aqui? ###TODO: movingFoodHistory -> dict:  key = posicao, value = tempo de vida da informacao

        # 3-----------------------------------------------------------
        # nao encontrou path para comida; procura um path para posicoes longinquas aleatorias
        if self.path == None:
            # print('NO food in sight and NO self.path, calls getPathRANDOMGoal')
            self.getPathRandomGoal()
        # 4-----------------------------------------------------------
        # ainda nao tem path; escolhe um accao possivel aleatoriamente
        if self.path == None:
            # print('THIS SHOULD ALMOST NEVER BE PRINTED')
            possible_actions = self.worldMapDomain.actions(SearchNode(self.body, None))  ###TODO: assim mete-se em deadEnds
            self.worldMapDomain.allow_exits = {}
            new_possible_actions = [act for act in possible_actions if
                                    self.world.translate(head, act) not in self.history and self.world.translate(head, act) not in self.worldMapDomain.forbidden_tunnels_ent]
            if new_possible_actions != []:
                action = random.choice(possible_actions)
            elif possible_actions != []:
                action = random.choice(possible_actions)
            else:
                action = Stay
            self.goal_type = 'random'
            self.goal = None
            self.food_type = None
            # print(' # # # RANDOM: random choice # # #')
            # print('self.path = None, action = random.choice(actions) or action = Stay --> ', action)
        else:
            # print(self.name, 'self.path != None, self.path = ', [(n.state[0].x, n.state[0].y) for n in self.path])
            # print(self.name, 'action = path[1].action = ', self.path[1].action)
            action = self.path[1].action
        # ----------------------------//------------------------------


        # - - - - - - - - - - - - - - - Outbox - - - - - - - - - - - - - - -
        if self.other_body != [] and self.goal != None and len(self.path) > 2 and (self.other_goal == None or self.world.dist(self.other_goal[0], self.goal)) < 2:
            self.outbox['g'] = [self.goal, len(self.path)]  ###mandar current nutrients?
            # print('chooseAction: outbox[g] = ', self.outbox['g'])

        next_pos = self.world.translate(head, action)
        if next_pos in vision.food:
            self.outbox['f'] = next_pos
            # print('next_pos in outbox[g]: this should be False -> ','g' in self.outbox and next_pos in self.outbox['g'])
        if next_pos in self.staticFoodHistory:
            del self.staticFoodHistory[next_pos]

        # - - - - - Tuneis - - - - -
        if next_pos in self.tunnels and not self.in_tunnel:
            tunnel_ent = next_pos  # len([tunnel for tunnel in self.tunnels_aux if next_pos in tunnel][0])
            tunnel_out_len = self.tunnels[next_pos]
            self.in_tunnel[tunnel_ent] = tunnel_out_len
            print("QQQ tunnel ent", tunnel_ent)
            print("QQQ tunnel out len", tunnel_out_len)

            if tunnel_out_len[1] >= self.my_horizon - self.my_horizon // 1.25:
                self.outbox['t'] = next_pos[0],next_pos[1]
            elif self.other_body and min([self.world.dist(x, tunnel_out_len[0]) for x in self.other_body]) < (tunnel_out_len[1] + 1):  # and 3 * tunnel_out_len[1] >= min([self.world.dist(x, next_pos) for x in self.other_body]):
                print("tunel pequeno, mas a outra esta demasiado proxima na visao!")
                self.outbox['t'] = next_pos[0],next_pos[1]
        elif next_pos in self.tunnels and self.in_tunnel:
            #
            # elif head in self.tunnels and self.in_tunnel and head not in self.in_tunnel:
            # if self.in_tunnel:
            self.in_tunnel = {}
            print("QQQSAI do tunel", self.in_tunnel)
            # self.outbox['ot'] = [self.body[0]]

        if self.outbox == {}:
            self.previous_outbox = None
        else:
            self.previous_outbox = self.outbox
        # - - - - - - - - - - - - - - - // - - - - - - - - - - - - - - - - -


        # DEBUG PRINTS
        print('_______')
        if self.path != None:
            print(self.name, ' > > > len(path) = ', len([(n.state[0].x, n.state[0].y) for n in self.path]))
        else:
            print(self.name, ' > > > path = None')
        print(self.name, ' > > > goal = ', self.goal, ', goal_type = ', self.goal_type)
        if self.goal in vision.food:
            print(self.name, ' > > > foodtype = ', vision.food[self.goal])
        else:
            print(self.name, 'goal not in vision.food')
        print('outbox = ', self.outbox)
        if self.outbox:
            return action, pickle.dumps(self.outbox)
        else:
            return action, b""

    def getPathFoodGoal(self, food, limit=None, strat='a_star'):
        if limit != None:
            # print('# # # FOOD: sonar search # # #')
            self.worldMapDomain.deadEndLimit = limit + 2
        else:
            print('# # # FOOD: standard search # # #')
        path = SearchTree(SearchProblem(self.worldMapDomain, self.body, food), strategy=strat, limit=limit).search()
        self.worldMapDomain.deadEndLimit = None
        if path != None:
            self.history = {}
            self.path = path
            # prev_goal = self.goal
            self.goal = path[-1].state[0]
            self.goal_type = 'food'
            self.food_type = food[self.goal]
            if len(path) > 2 and self.other_body != []:  # and prev_goal != self.goal:
                self.outbox['g'] = [self.goal, len(self.path)]
                # print('getPathFoodGoal: outbox[g] = ', self.outbox['g'])
        if self.goal != None and self.goal in food:
            print('getPathFoodGoal: pos = ', (self.body[0], self.body[-1]), ' , -> goal = ',(self.goal[0], self.goal[-1]), ', foodtype = ', food[self.goal])
            print('self.path = ', [(n.state[0].x, n.state[0].y) for n in self.path])
        else:
            print('unsuccessful search')
        print('# # # # # # # # # # # #')

    def getPathRandomGoal(self):
        aux_dict = {}
        while len(aux_dict) < 4:
            rand_goal = self.generateGoal()
            while self.world.dist(self.body[0], rand_goal) < max(self.world.size.y, self.world.size.x) // 4:
                rand_goal = self.generateGoal()
            aux_dict[rand_goal] = 'R'
            if self.other_goal != None and self.other_goal[2] and self.world.dist(self.other_goal[0],
                                                                                  rand_goal) < self.world.dist(
                    self.body[0], rand_goal):  ###muito dificil conseguir rands?
                del aux_dict[rand_goal]
        # print('# # # RANDOM: random search # # #')
        path = SearchTree(SearchProblem(self.worldMapDomain, self.body, aux_dict), strategy='a_star').search()
        if path != None:
            self.path = path
            self.goal = path[-1].state[0]
            self.goal_type = 'random'
            self.food_type = None
            if len(path) > 2 and self.other_body != []:
                self.outbox['g'] = [self.goal, len(self.path)]
                # print('getPathFoodGoal: outbox[g] = ', self.outbox['g'])
            # print('getPathRandomGoal: pos = ', (self.body[0], self.body[-1]), ' , -> goal = ',(self.goal[0], self.goal[-1]))
            # print('self.path = ', [(n.state[0].x, n.state[0].y) for n in self.path])
        # print('# # # # # # # # # # # #')

    def filterFoodbyNutrients(self, food):
        if self.nutrients['M'] < self.nutrients['S'] and 'M' in food.values():
            return {f: food[f] for f in food if food[f] == 'M'}
        elif self.nutrients['S'] < self.nutrients['M'] and 'S' in food.values():
            return {f: food[f] for f in food if food[f] == 'S'}
        return food

    def filterFoodbyDeadEnds(self,
                             food):  ###TODO: add- > and not in <tunel onde esta o outro agente>? (é que pode entrar desde que seja por on o outro tambem entrou)
        return {f: food[f] for f in food if f not in self.DeadEndWalls}

    def generateGoal(self):
        return self.world.generatePos(forbiden=ChainMap(self.world.walls, self.DeadEndWalls, self.history))
        ###îf len(vFoodHist) == 0 preferred = posicoes_onde_ja_encontrou_comida


class WorldMap:
    def __init__(self, world, dead_end_walls):
        self.world = world
        self.body = None
        self.other_body = []
        self.DeadEndWalls = dead_end_walls
        self.forbidden_tunnels_ent = {}  # ex:
        self.allowed_tunnels_ent = {}  # ex:
        self.allow_exits = {}
        # self.see_a_later_tunnel = False
        self.mode = 'standard'
        self.vision = None
        self.other_goal = None
        # self.max_radius = round(max(self.world.size.x, self.world.size.y)/2)
        self.deadEndLimit = None

    def actions(self, node):
        validact = []
        for act in ACTIONS[1:]:
            newpos = self.world.translate(node.state[0], act)
            if newpos not in self.world.walls and newpos not in node.state and newpos not in self.other_body and newpos not in self.DeadEndWalls:
                if self.forbidden_tunnels_ent:
                    # if newpos in self.forbidden_tunnels_ent and not self.allowed_entrance:
                    #     continue
                    if newpos not in self.allowed_tunnels_ent and newpos not in self.forbidden_tunnels_ent:
                        validact.append(act)
                    elif newpos in self.allowed_tunnels_ent:  # and newpos not in self.allowed_entrance:
                        print("ACTIONS 1 newpos vai ser a entrada permitida, adiciona",newpos,self.forbidden_tunnels_ent)
                        self.allow_exits[self.allowed_tunnels_ent[newpos]] = newpos
                        validact.append(act)
                    elif newpos in self.forbidden_tunnels_ent and newpos in self.allow_exits:
                        print("ACTIONS 2 newpos vai adicionar a saida proibida porque a entrada ja passou, direçao certa!",newpos,self.forbidden_tunnels_ent)
                        del self.allow_exits[newpos]
                        validact.append(act)
                else:
                    validact.append(act)
        return validact

    def result(self, pos, action):
        return self.world.translate(pos, action)

    def cost(self, pos, act):
        return 1

    def heuristic(self, pos, goals):
        other_body = [pos for pos in self.vision.bodies if pos not in self.body]
        # if other_body != []:
        #    if self.other_goal != None: ### talvez experimentar que ela tenha em conta o goal da outra mesmo sem ter a outra na visao;
        #        return min([self.world.dist(pos, goal) - self.world.dist(goal, other_body[0]) - self.world.dist(self.other_goal[0], goal) for goal in goals])
        #    return min([self.world.dist(pos, goal) - self.world.dist(goal, other_body[0]) for goal in goals]) # + self.max_radius
        return min([self.world.dist(pos, goal) for goal in goals])

    # def deadEnd(self, path):
    #     if self.mode == 'dummy':
    #         return False
    #     temp_allow_exit = self.allow_exits
    #     self.allow_exits = False
    #     self.mode = 'dummy'
    #     aux_body = copy.deepcopy(self.body)  # em principio nao precisa de deepcopy
    #     aux_other_body = copy.deepcopy(self.other_body)  # ^
    #     self.body = path[-1].state
    #     self.other_body = [pos for pos in self.vision.bodies if pos not in aux_body]
    #     dummy_tree = SearchTree(SearchProblem(self, self.body, [aux_body[-1]]), strategy='a_star',
    #                             limit=self.deadEndLimit)
    #     dummy_path = dummy_tree.search()
    #     self.body = copy.deepcopy(aux_body)  # em principio nao precisa d deepcopy
    #     self.other_body = copy.deepcopy(aux_other_body)  # ^
    #     self.mode = 'standard'
    #     self.allow_exits = temp_allow_exit
    #     return dummy_path == None


class SearchProblem:
    def __init__(self, domain, initial, goals):
        self.domain = domain
        self.initial = initial
        self.goal = goals  # é dicionário (vision.food)

#TODO: o erro dos tuneis quase de certeza que esta aqui, quando for a pesquisar o dead end a search dele nao pode alterar o self.allow_exit

    def goal_test(self, path):
        if path[-1].state[0] in self.goal:
            # print("goal_test: started deadEnd()")
            res = self.domain.deadEnd(path)
            # print("goal_test: finished deadEnd()")
            # print("goal_test: path == dead end? -> ", res)
            if not res:
                # print("goal_test: not_a_deadEnd_path=" , [n.state[0] for n in path])
                return True
            del self.goal[path[-1].state[0]]
        return False


class SearchNode:
    def __init__(self, state, parent):
        self.state = state
        self.parent = parent
        self.g = 0
        self.depth = 0
        self.h = 0
        self.action = None


class SearchTree:
    def __init__(self, problem, strategy='a_star', limit=None):
        self.problem = problem
        root = SearchNode(problem.initial, None)
        self.open_nodes = [root]
        self.strategy = strategy
        self.limit = limit
        self.visited = {}

    def get_path(self, node):
        if node.parent == None:
            return [node]
        path = self.get_path(node.parent)
        path += [node]
        return path

    def search(self):
        print("search")
        while self.open_nodes != []:
            node = self.open_nodes[0]
            node_path = self.get_path(node)
            # if self.problem.domain.forbidden_tunnels:
            # if self.problem.domain.forbidden_tunnels[1] in node_path and self.problem.domain.forbidden_tunnels[0] not in node_path:
            # e trocar isto tudo por um any e uma lista de compreensao? sera que compensa?
            # for key in self.problem.domain.forbidden_tunnels:
                # print("-------------------")
                # print("search: out",key)
                # print("search: ent",self.problem.domain.forbidden_tunnels[key])
            # if any(key in node_path and self.problem.domain.forbidden_tunnels[key] not in node_path for key in self.problem.domain.forbidden_tunnels):
            #     if key in node_path and self.problem.domain.forbidden_tunnels[key] not in node_path:
            #     print("SEARCH: path ia ter o tunel proibido, retorna none")
            #     return None
            # print('search: before, self.problem.goal', self.problem.goal)
            if self.problem.goal_test(node_path):
                self.problem.domain.allow_exits = {}
                return node_path
            # print('search: after, self.problem.goal', self.problem.goal)
            if len(self.problem.goal) == 0:
                self.problem.domain.allow_exits = {}
                return None
            self.open_nodes[0:1] = []
            lnewnodes = []
            if self.limit != None and node.depth >= self.limit:
                self.problem.domain.allow_exits = {}
                return None
            for a in self.problem.domain.actions(node):
                newhead = self.problem.domain.result(node.state[0], a)
                if newhead not in self.visited:
                    # print("newhead repetida??",newhead)
                    self.visited[newhead] = True
                    newnode = SearchNode([newhead, node.state[0]], node)
                    newnode.action = a
                    newnode.h = self.problem.domain.heuristic(newhead, self.problem.goal)
                    newnode.depth = node.depth + 1
                    newnode.g = self.problem.domain.cost(node.state[0], a) + node.g
                    lnewnodes += [newnode]
            self.add_to_open(lnewnodes)
        self.problem.domain.allow_exits = {}
        return None

    def add_to_open(self,
                    lnewnodes):  ###se nao fizermos heap, ao menos parar de ordenar a lista toda, deixar desordenada, procurar o node
        if self.strategy == "a_star":  ###ao inicio de cada while do search
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.h + node.g)
        elif self.strategy == "greedy":
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.h)
        elif self.strategy == 'breadth':
            self.open_nodes.extend(lnewnodes)
        elif self.strategy == 'depth':
            self.open_nodes[0:0] = lnewnodes
        elif self.strategy == 'uniform':
            self.open_nodes.extend(lnewnodes)
            self.open_nodes.sort(key=lambda node: node.g)

