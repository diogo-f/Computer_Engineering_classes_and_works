#Diogo Reis - Nmec: 64231
#Joao Silva - Nmec: 90183

from agent import *
import random
import pickle


class StudentAgent(Agent):
    def __init__(self, name, body, world):
        self.name = name
        self.world = world
        
        self.DeadEndWalls = {}
        self.tunnels = {}
        self.tunnels_aux = []

        self.scanMap()

        
        self.body = body
        self.nutrients = {}
        self.age = 0
        self.timespent = 0.0

        self.path = None
        self.goal = None
        self.goal_type = 'random'
        self.history = {}
        self.staticFoodHistory = {}

        self.my_tunnel = None
        self.other_tunnel = None
        self.other_tunnel_wait_time = None

        self.inbox = {}
        self.outbox = {}
        self.other_goal = None
        self.food_type = None
        self.searched_vision_food = False
        self.last_man_standing = False

        self.f_key_i = 11 - int(self.name[1])
        self.gr_key_i = self.f_key_i + 10
        self.g_key_i = self.gr_key_i + 10
        self.t_key_i = self.g_key_i + 10

        self.f_key_o = 10 + int(self.name[1])
        self.gr_key_o = self.f_key_o + 10
        self.g_key_o = self.gr_key_o + 10
        self.t_key_o = self.g_key_o + 10

        self.worldMapDomain = WorldMap(self.world, self.DeadEndWalls, self.tunnels)

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
        # percorrer todos os dead ends e gerar o dead end completo
        for dead_end in dead_ends:
            self.getDead_ends(dead_end)
        # percorrer todos os possiveis tuneis e chamar o self.getTunnels() por cada um
        # este vai gerar os tuneis que encontrar(tuneis de tamanho 2 ou maior)
        for tunnelPos in tunnels_temp:
            if tunnelPos not in self.tunnels_aux:
                self.tunnel_ent = None
                self.tunnel_out = None
                newTunnel = self.getTunnels(tunnelPos, tunnels_temp)
                if newTunnel != []:
                    self.tunnels[self.tunnel_ent] = self.tunnel_out, len(newTunnel), newTunnel
                    self.tunnels[self.tunnel_out] = self.tunnel_ent, len(newTunnel), newTunnel
                    self.tunnels_aux += newTunnel
        self.tunnels_aux = []

    def getNeighbours(self, pos, actions=ACTIONS[1:], exclude=[]):
        neighs = []
        for act in actions:
            neighbour = self.world.translate(pos, act)
            if neighbour not in self.world.walls and neighbour not in self.DeadEndWalls and neighbour not in exclude:
                neighs.append(tuple(neighbour))
        return neighs

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
        self.worldMapDomain.body = self.body
        self.worldMapDomain.vision = vision
        self.worldMapDomain.other_body = []
        head = self.body[0]
        self.other_body = [pos for pos in vision.bodies if vision.bodies[pos] != self.name]
        action = None
        self.outbox = {}
        self.searched_vision_food = False


        # - - - - - - - - - - - - - - Sorts food - - - - - - - - - - - - - -
        # Dead-end Filter
        vision.food = self.filterFoodbyDeadEnds(vision.food)

        # Tunel Filter
        vision.food = self.filterFoodbyTunnels(vision.food)

        self.staticFoodHistory.update({x: vision.food[x] for x in vision.food if vision.food[x] == 'S'})

        # 1st Nutrient Filter
        if self.nutrients['M'] / self.nutrients['S'] > 1.4 or self.nutrients['S'] / self.nutrients['M'] > 1.4:
            vision.food = self.filterFoodbyNutrients(vision.food)

        nearby_food = {f: vision.food[f] for f in vision.food if self.world.dist(head, f) < 8}

        # 2nd Nutrient Filter
        if self.nutrients['M'] / self.nutrients['S'] > 1.2 or self.nutrients['S'] / self.nutrients['M'] > 1.2:
            vision.food = self.filterFoodbyNutrients(vision.food)
        # - - - - - - - - - - - - - - - - // - - - - - - - - - - - - - - - -


        ###TODO: if self.my_tunnel != None skip all searches


        # Agent exited the tunnel
        if self.my_tunnel != None and self.body[-1] == self.tunnels[self.my_tunnel][0]:
            self.my_tunnel = None
            self.worldMapDomain.my_tunnel = self.my_tunnel


        # Other agent exited his tunnel
        if self.other_tunnel_wait_time != None:
            self.other_tunnel_wait_time -= 1
            if self.other_tunnel_wait_time == 0:
                self.other_tunnel_wait_time = None
                self.other_tunnel = None
                self.worldMapDomain.other_tunnel = self.other_tunnel


        # Adds other agent's dead body to walls
        if len(self.other_body) == 2 and self.last_man_standing:
            self.DeadEndWalls[self.other_body[0]] = True
            self.DeadEndWalls[self.other_body[1]] = True

        # Agent is following a random path/moved with a random action; adds head to self.history
        if self.goal_type == 'random':
            self.history[head] = True

        # Agent is following a previously calculated path -> updates it if last action wasn't Stay
        if self.path != None:
            if len(self.path) > 2:
                if self.path[1].state[0] == head:
                    self.path = self.path[1:]
            else:
                self.path = None
                self.goal = None
                self.goal_type = None

        # Other agent is in vision; takes caution regarding body hits
        if self.other_body != []:
            if min([self.world.dist(head, x) for x in self.other_body]) == 1:
                if self.goal != None and self.world.translate(head, [act for act in DIRECTIONS if self.world.translate(head,act) == self.path[1].state[0]][0]) in vision.bodies:
                    self.path = None
                    self.goal = None
                    self.goal_type = None
                    self.food_type = None
                self.worldMapDomain.other_body = self.other_body 


        # - - - - - - - - - - - - - - - Inbox - - - - - - - - - - - - - - -
        # message key format = (yx); y -> message type, x -> player; see lines: 43-51
        # 1x - player x ate food in position = message[1x]
        # 2x - player x replied to the other player's type 3 message
        # 3x - player x set pos = message[3x][0] as his goal with path lenght = message[3x][1]
        # 4x - player x entered a tunel and sends the entrance position = message[4x]

        # Unpacks message
        if msg != b"":
            self.inbox = pickle.loads(msg)
        else:
            self.inbox = {}
        if self.inbox != {}:
            if any([True for key in self.inbox if key % 10 == int(self.name[1])]): # agent receives a message signed by himself => other agent is dead
                self.last_man_standing = True
                self.other_goal = None
                self.worldMapDomain.last_man_standing = True
                if self.other_tunnel != None:
                    self.DeadEndWalls[self.other_tunnel] = True
                    self.DeadEndWalls[self.tunnels[self.other_tunnel][0]] = True ###TODO: get deadEndWalls from dead body's neighbours

            if not self.last_man_standing:

                # 4x message: tunel recebido
                if self.t_key_i in self.inbox:
                    self.other_tunnel = self.inbox[self.t_key_i]
                    self.other_tunnel_wait_time = self.tunnels[self.other_tunnel][1]
                    other_tunnel_start = self.inbox[self.t_key_i]
                    other_tunnel_exit = self.tunnels[other_tunnel_start][0]
                    vision.food = self.filterFoodbyTunnels(vision.food)
                    nearby_food = {f: vision.food[f] for f in vision.food if self.world.dist(head, f) < 8}
                    self.worldMapDomain.other_tunnel = self.other_tunnel

                    # if received tunel is in his path, the agent checks the tunnel's direction, if forbidden -> resets path
                    if self.path != None and self.my_tunnel != self.other_tunnel:
                        ind_path_start = None
                        ind_path_exit = None
                        path_lst = [n.state[0] for n in self.path]
                        if other_tunnel_start in path_lst:
                            ind_path_start = path_lst.index(other_tunnel_start)
                        if other_tunnel_exit in path_lst:
                            ind_path_exit = path_lst.index(other_tunnel_exit)
                        if ind_path_exit != None and ind_path_start == None or ind_path_start != None and ind_path_exit != None and ind_path_start > ind_path_exit:
                            self.path = None
                            self.goal_type = None
                            self.goal = None
                            self.food_type = None

                # 1x message (check inbox comments starting in line: 191)
                if self.f_key_i in self.inbox:
                    self.other_goal = None
                    eaten_food = self.inbox[self.f_key_i]

                    if self.goal == eaten_food:  # vs if self.goal != None and self.world.dist(self.goal, eaten_food) < 2
                        self.path = None
                        self.goal = None
                        self.goal_type = None
                        self.food_type = None

                    if eaten_food in self.staticFoodHistory:
                        del self.staticFoodHistory[eaten_food]

                # 2x message
                if self.gr_key_i in self.inbox:
                    if self.other_goal != None and self.world.dist(self.other_goal[0], self.inbox[self.gr_key_i][0]) < 2:
                        self.other_goal = list(self.inbox[self.gr_key_i]) + [self.other_goal[2], self.age]
                    else:
                        self.other_goal = list(self.inbox[self.gr_key_i]) + [False, self.age]

                # 3x message
                if self.g_key_i in self.inbox:
                    # updates other_goal's position
                    if self.other_goal != None and self.world.dist(self.other_goal[0], self.inbox[self.g_key_i][0]) < 2:
                        self.other_goal = list(self.inbox[self.g_key_i]) + [self.other_goal[2], self.age]
                    else:
                        self.other_goal = list(self.inbox[self.g_key_i]) + [False, self.age]

                    # cant be sure if both players are following the same goal; recalculates where he's going
                    if self.goal != None and self.world.dist(self.goal, self.other_goal[0]) > 1 and not any([True for f in vision.food if self.world.dist(f, self.goal) < 2]) \
                        and self.goal_type == 'food' and self.food_type == 'M' or self.goal == None:
                            self.path = None
                            self.goal = None
                            self.goal_type = None
                            self.food_type = None

                            if len(vision.food) > 0:
                                self.getPathFoodGoal(vision.food)
                                self.searched_vision_food = True

                    # finds out who has a shorter path if both agents have the same goal
                    if self.goal != None and self.world.dist(self.goal, self.other_goal[0]) < 2:
                        # self.goal = other agent's goal
                        if len(self.path) < self.inbox[self.g_key_i][1]:
                            # this agent's path is shorter than the other agent's
                            self.other_goal = None
                            if len(self.path) > 2 and self.other_body != []:
                                self.outbox[self.g_key_o] = ((self.goal[0], self.goal[1]), len(self.path))  ###mandar current nutrients?
                            else:
                                if self.g_key_o in self.outbox:
                                    del self.outbox[self.g_key_o]
                        else:
                            # other agent's path is shorter
                            self.path = None
                            self.goal = None
                            self.goal_type = None
                            self.food_type = None
                            self.other_goal[2] = True
                            if self.g_key_o in self.outbox:
                                del self.outbox[self.g_key_o]
        # - - - - - - - - - - - - - - - - Inbox end - - - - - - - - - - - - - - - -

        self.worldMapDomain.other_goal = self.other_goal


        # Filters food by goals prioritized to the other agent
        if self.other_goal != None and self.other_goal[2]:
            vision.food = {f: vision.food[f] for f in vision.food if self.world.dist(f, self.other_goal[0]) > 1 + self.age - self.other_goal[3]}
            nearby_food = {f: nearby_food[f] for f in nearby_food if self.world.dist(f, self.other_goal[0]) > 1 + self.age - self.other_goal[3]}

        # - - - - - - - - - - - Sonar - - - - - - - - - - -
        if any(nearby_food) and not self.searched_vision_food and (self.path == None or self.path != None and self.goal not in vision.food or self.goal_type == 'random'):
            self.getPathFoodGoal(nearby_food, limit=4)
        # - - - - - - - - - - - - - - - - - - - - - - - - -



        # --------------------situações possiveis---------------------
        # 1-----------------------------------------------------------
        # ve comida e nao tem um path definido ou, se tem, este leva a um goal que nao e comida; procura um path para comida
        if len(vision.food) > 0 and (self.path == None or self.goal_type != 'food') and not self.searched_vision_food:
            self.getPathFoodGoal(vision.food)
        # 2-----------------------------------------------------------
        # nao ve comida mas ja viu comida que nao chegou a comer; procura um path para essa comida
        if self.path == None and len(self.staticFoodHistory) > 0:
            if self.other_goal != None and self.other_goal[2] and self.other_goal[ 0] in self.staticFoodHistory:
                uneaten_static_food = {f: self.staticFoodHistory[f] for f in self.staticFoodHistory if self.world.dist(f, self.other_goal[0]) > 1}
            else:
                uneaten_static_food = dict(self.staticFoodHistory)
            if uneaten_static_food:
                self.getPathFoodGoal(uneaten_static_food)
        # 3-----------------------------------------------------------
        # nao encontrou path para comida; procura um path para posicoes longinquas aleatorias
        if self.path == None:
            self.getPathRandomGoal()
        # 4-----------------------------------------------------------
        # ainda nao tem path; escolhe um accao possivel aleatoriamente
        if self.path == None:
            possible_actions = self.worldMapDomain.actions(SearchNode(self.body, None))
            new_possible_actions = [act for act in possible_actions if self.world.translate(head, act) not in self.history]
            if new_possible_actions != []:
                action = random.choice(possible_actions)
            elif possible_actions != []:
                action = random.choice(possible_actions)
            else:
                action = Stay
            self.goal_type = 'random'
            self.goal = None
            self.food_type = None
        else:
            action = [act for act in DIRECTIONS if self.world.translate(head,act) == self.path[1].state[0]][0]
        # ----------------------------//------------------------------


        next_pos = self.world.translate(head, action)
        

        # - - - - - - - - - - - - - - - Outbox - - - - - - - - - - - - - - -
        if not self.last_man_standing:

            if self.other_body != [] and self.goal != None and len(self.path) > 2 and (self.other_goal == None or self.world.dist(self.other_goal[0], self.goal)) < 2:
                self.outbox[self.g_key_o] = ((self.goal[0], self.goal[1]), len(self.path))  ###mandar current nutrients?

            if next_pos in vision.food:
                self.outbox[self.f_key_o] = (next_pos[0], next_pos[1])

            if self.g_key_i in self.inbox and self.g_key_o not in self.outbox and self.goal != None and len(self.path) > 2:
                self.outbox[self.gr_key_o] = ((self.goal[0], self.goal[1]), len(self.path))

            if next_pos in self.tunnels and self.my_tunnel == None:
                tunnel_out_len = self.tunnels[next_pos]
                if not(self.other_body != [] and min([self.world.dist(x, tunnel_out_len[0]) for x in self.other_body]) > (tunnel_out_len[1] + 1)):
                    self.outbox[self.t_key_o] = next_pos[0], next_pos[1]
        # - - - - - - - - - - - - -  Outbox end - - - - - - - - - - - - - - -


        # Agent is entering a tunnel
        if next_pos in self.tunnels and self.my_tunnel == None:
            self.my_tunnel = next_pos
            self.worldMapDomain.my_tunnel = self.my_tunnel


        if next_pos in self.staticFoodHistory:
            del self.staticFoodHistory[next_pos]

        if self.outbox:
            return action, pickle.dumps(self.outbox)
        else:
            return action, b""

    def getPathFoodGoal(self, food, limit=None, strat='a_star'):
        if limit != None:
            self.worldMapDomain.deadEndLimit = limit + 2
        path = SearchTree(SearchProblem(self.worldMapDomain, self.body, food), limit=limit).bidirectional_search()
        self.worldMapDomain.deadEndLimit = None
        if path != None:
            self.history = {}
            self.path = path
            # prev_goal = self.goal
            self.goal = path[-1].state[0]
            self.goal_type = 'food'
            self.food_type = food[self.goal]
            if len(path) > 2 and self.other_body != [] and not self.last_man_standing:  # and prev_goal != self.goal:
                self.outbox[self.g_key_o] = ((self.goal.x, self.goal.y), len(self.path))

    def getPathRandomGoal(self):
        aux_dict = {}
        while len(aux_dict) < 4:
            rand_goal = self.generateGoal()
            while self.world.dist(self.body[0], rand_goal) < max(self.world.size.y, self.world.size.x) // 4:
                rand_goal = self.generateGoal()
            aux_dict[rand_goal] = 'R'
            if self.other_goal and self.other_goal[2] and self.world.dist(self.other_goal[0], rand_goal) < self.world.dist(self.body[0], rand_goal):  ###muito dificil conseguir rands?
                del aux_dict[rand_goal]
        path = SearchTree(SearchProblem(self.worldMapDomain, self.body, aux_dict)).bidirectional_search()
        if path != None:
            self.path = path
            self.goal = path[-1].state[0]
            self.goal_type = 'random'
            self.food_type = None
            if len(path) > 2 and self.other_body != [] and not self.last_man_standing:
                self.outbox[self.g_key_o] = ((self.goal.x, self.goal.y), len(self.path))


    def filterFoodbyNutrients(self, food):
        if self.nutrients['M'] < self.nutrients['S'] and 'M' in food.values():
            return {f: food[f] for f in food if food[f] == 'M'}
        elif self.nutrients['S'] < self.nutrients['M'] and 'S' in food.values():
            return {f: food[f] for f in food if food[f] == 'S'}
        return food

    def filterFoodbyDeadEnds(self, food): 
        return {f: food[f] for f in food if f not in self.DeadEndWalls}

    def filterFoodbyTunnels(self, food):
        if self.other_tunnel != None and self.tunnels[self.other_tunnel][0] in food:
            del food[self.tunnels[self.other_tunnel][0]]
        return food

    def generateGoal(self):
        return self.world.generatePos(forbiden=ChainMap(self.world.walls, self.DeadEndWalls, self.history))


class WorldMap:
    def __init__(self, world, dead_end_walls, tunnels):
        self.world = world
        self.body = None
        self.other_body = []
        self.DeadEndWalls = dead_end_walls
        self.tunnels = tunnels
        self.vision = None
        self.other_goal = None
        self.deadEndLimit = None
        self.last_man_standing = False
        self.my_tunnel = None
        self.other_tunnel = None

    def actions(self, node):
        validact = []
        for act in DIRECTIONS:
            newpos = self.world.translate(node.state[0], act)
            if newpos not in self.world.walls and newpos not in node.state and newpos not in self.other_body and newpos not in self.DeadEndWalls:
                if self.my_tunnel != self.other_tunnel and self.other_tunnel != None and newpos == self.tunnels[self.other_tunnel][0]:
                    if node.direction == 'forwards' and self.other_tunnel in node.get_head_path() or \
                        node.direction == 'backwards' and node.state[0] not in self.tunnels[self.other_tunnel][2] and self.other_tunnel not in node.get_head_path():
                            validact.append(act)
                else:
                    validact.append(act)
        return validact

    def result(self, pos, action):
        return self.world.translate(pos, action)

    def cost(self, pos, act):
        return 1

    def heuristic(self, pos, goals):  # heuristica conta com a dist do other_body e do other_goal
        other_body = [pos for pos in self.vision.bodies if pos not in self.body]
        if other_body != [] and not self.last_man_standing:
            if self.other_goal != None:
                return min([self.world.dist(pos, goal) - 0.5*self.world.dist(goal, other_body[0]) - 0.4*self.world.dist(self.other_goal[0], goal) for goal in goals])
            return min([self.world.dist(pos, goal) - self.world.dist(goal, other_body[0]) for goal in goals])
        return min([self.world.dist(pos, goal) for goal in goals])

class SearchProblem:
    def __init__(self, domain, initial, goals):
        self.domain = domain
        self.initial = initial
        self.goal = goals

    def goal_test(self, path):
        return path[-1].state[0] in self.goal

class SearchNode:
    def __init__(self, state, parent):
        self.state = state
        self.parent = parent
        self.g = 0
        self.depth = 0
        self.h = 0
        self.direction = None
        self.orig_goal = None

    def get_path(self):
        if self.parent == None:
            return [self]
        path = self.parent.get_path()
        path += [self]
        return path

    def get_head_path(self):
        if self.parent == None:
            return [self.state[0]]
        path = self.parent.get_head_path()
        path += [self.state[0]]
        return path


class SearchTree:
    def __init__(self, problem, limit=None):
        self.problem = problem
        self.limit = limit

        self.direction = None
        self.forw_open = {}
        self.back_open = {}
        self.forw_visited = {}
        self.back_visited = {}


    def bidirectional_search(self):

        self.direction = 'forwards'
        near_goal_pos = {}

        # < creates fwd and bwd open nodes lists
        self.forw_open[self.problem.initial[0]] = [SearchNode(self.problem.initial, None)]
        self.forw_open[self.problem.initial[0]][0].h = self.problem.domain.heuristic(self.problem.initial[0], self.problem.goal)
        self.forw_open[self.problem.initial[0]][0].direction = 'forwards'

        for g in self.problem.goal:
            self.back_open[g] = [SearchNode((g, None), None)]
            self.back_open[g][0].h = self.problem.domain.heuristic(self.problem.initial[0], [g])
            self.back_open[g][0].direction = 'backwards'
            self.back_open[g][0].orig_goal = g
            near_goal_pos[g] = 1
        # >

        while len(self.back_open) > 0 and len(self.forw_open) > 0 and len(self.problem.goal) > 0:


            # < sets structures according to current direction
            if self.direction == 'forwards':
                visited = self.forw_visited
                open_nodes = self.forw_open
                other_open_nodes = self.back_open
            else:
                visited = self.back_visited
                open_nodes = self.back_open
                other_open_nodes = self.forw_open
            # >

            # < gets node with lowest f and removes it from open
            node_f = None
            node = None
            n_key = None
            n_index = None

            for pos in open_nodes:  ###TODO:self.get_node_f(node) que varia com a strategy
                for i in range(len(open_nodes[pos])):
                    if node_f == None or open_nodes[pos][i].g + open_nodes[pos][i].h < node_f:
                        node = open_nodes[pos][i]
                        node_f = open_nodes[pos][i].g + open_nodes[pos][i].h
                        n_key = pos
                        n_index = i

            del open_nodes[n_key][n_index]
            if open_nodes[n_key] == []:
                del open_nodes[n_key]
            # >

            # < "goal_test"
            if node.state[0] in other_open_nodes:
                possible_nodes = [n for n in other_open_nodes[node.state[0]] if node.state[1] != n.state[1]]
                if possible_nodes != []:
                    other_node = possible_nodes[0]
                    if node.direction == 'backwards':
                        other_node, node = node, other_node
                    return node.get_path() + (other_node.get_path()[::-1])[1:]
            # >

            # < checks node depth
            if self.limit != None and node.depth >= self.limit:
                return None
            # >

            # < adds new nodes to open
            lnewnodes = []
            for a in self.problem.domain.actions(node):
                newhead = self.problem.domain.result(node.state[0], a)
                if node.direction == 'forwards':
                    newtail = node.state[0]
                else:
                    newtail = None
                newstate = newhead, newtail
                if newstate not in visited:
                    visited[newstate] = True
                    newnode = SearchNode((newhead, node.state[0]), node)
                    if node.direction == 'forwards':
                        newnode.h = self.problem.domain.heuristic(newhead, self.problem.goal)
                    else:
                        newnode.h = self.problem.domain.heuristic(self.problem.initial[0], [newhead])
                        near_goal_pos[node.orig_goal] += 1
                    newnode.depth = node.depth + 1
                    newnode.g = self.problem.domain.cost(node.state[0], a) + node.g
                    newnode.direction = node.direction
                    newnode.orig_goal = node.orig_goal
                    if newhead in open_nodes:
                        open_nodes[newhead] += [newnode]
                        open_nodes[newhead].sort(key = lambda n: n.g) #este sort sera sempre, no pior caso, a ordenacao de 4 nodes, logo nao aumenta a complexidade
                    else:
                        open_nodes[newhead] = [newnode]
            # >

            # < deletes goals that are locked off
            if self.direction == 'backwards':
                near_goal_pos[node.orig_goal] -= 1
                if near_goal_pos[node.orig_goal] == 0:
                    del near_goal_pos[node.orig_goal]
                    del self.problem.goal[node.orig_goal]
            # >

            # < swaps search direction
            if self.direction == 'forwards':
                self.direction = 'backwards'
            else:
                self.direction = 'forwards'
            # >

        return None