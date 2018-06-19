from start import *
import time
import csv
from functools import reduce

mapas = ["mapa_random", "mapa1", "mapa2", "mapa3", "mapa4", "mapa5", "mapa6", "mapa4__2f"]
n = 500
totalTime = time.time()
for mapa in mapas:
    lst_scores = []
    lst_iter_tunnel_deads = []
    print("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " + mapa + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ")
    for i in range(n):
        t1 = time.time()

        if mapa == "mapa_random":
            os.system("python3 start.py -v > log/log_" + mapa + "/" + str(i) + ".txt")
        else:
            os.system("python3 start.py -v -m maps/" + mapa + ".bmp > log/log_" + mapa + "/" + str(i) + ".txt")

        print("iteração", i, mapa + "| tempo exec ", time.time() - t1)
        # ler ficheiro
        lis = list(csv.reader(open("log/log_" + mapa + "/" + str(i) + ".txt")))
        # adiciona o score desta iteracao
        lst_scores.append(int(lis[-1][0]))
        # verifica se morreram num tunel e guarda essa iteracao
        if lis[-2] == lis[-4] and lis[-3] == lis[-5]:
            lst_iter_tunnel_deads.append(str(i) + ".txt")
        lis = None
    print("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -")
    print("\n          - - - -" + mapa + " stats- - - -")
    lst_scores.sort()
    print("-> MIN:", lst_scores[0], "| MAX:", lst_scores[-1], "|| AVG: ", reduce(lambda x, y: x + y, lst_scores) / len(lst_scores))
    print("-> All scores sorted:\n", lst_scores)
    if len(lst_iter_tunnel_deads) > 0:
        percentage = (len(lst_iter_tunnel_deads)*100)/n
        print("-> " + str(len(lst_iter_tunnel_deads)) + " iterations with tunnel deads(" + str(percentage) + "% of " + str(n) + "), check file(s):\n", lst_iter_tunnel_deads)
        print("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _end " + mapa + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n")
    else:
        print("-> Não morreu em tuneis")
        print("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _end " + mapa + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n")

t = time.time() - totalTime
ti = '{:02}:{:02}:{:02}'.format(round(t // 3600), round(t % 3600 // 60), round(t % 60))
print("Total time:\nkilled " + ti + " time of your computers life")
