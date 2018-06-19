# aula 01
# 1-ex 1,2,3,5,6
# 2-ex 2
# 3-ex 4

# aula 02
# 4-ex 1,2,3,4,5,6,10,12,15
# 5.1-ex c(a,b)
# 5.2-ex c(a,b)

########################################
#__init data__
from functools import reduce

l1 = [1,2,3,4,5]
l2 = [6,7,8,9,0]
l3 = [1,2,3,3,2,1]
l4 = [1,2,3,2,1]
l5 = [[1,2,3],['a','b','c'],[123]]
l6 = [2,4,-3,7]
l7 = [-1,-1,-1,-1,-99]
lp = [(1,2),('a','b'),(3,4)]
#################__1__##################
print('\nex 1.1')
def lengList(l):
    if l == []:
        return 0
    return 1 + lengList(l[1:])
print(lengList(l1))

print('\nex 1.2')
def soma(l):
    if l == []:
        return 0
    return l[0] + soma(l[1:])
print(soma(l1))

print('\nex 1.3')
def findValue(l,v):
    if l == []:
        return False
    if v == l[0]:
        return True
    return findValue(l[1:],v)

print(findValue(l1, 3))
print(findValue(l1, 6))

print('\nex 1.4')
def concat(l1,l2):
    if l1 == []:
        return l2
    conc = concat(l1[1:],l2)
    conc[:0] = [l1[0]]
    return conc

print(concat(l1,l2))

print('\nex 1.5')
def inversa_1(l):
    if l == []:
        return []
    return [] + l[-1:] + inversa_1(l[:-1])

def inversa_2(l):
    if l == []:
        return []
    return inversa_2(l[1:]) + [l[0]]

print(inversa_1(l1))
print(inversa_2(l1))

print('\nex 1.6')
def capicua(l):
    if l == [] or len(l) == 1:
        return True
    if l[0] == l[-1]:
        return capicua(l[1:-1])
    else:
        return False

print(capicua(l1))
print(capicua(l3))
print(capicua(l4))

print('\nex 1.7')

def concatList_of_list(l):
    if l == []:
        return []
    return l[0] + concatList_of_list(l[1:])

print(concatList_of_list(l5))

print('\nex 1.8')
def subst(l,x,y):
    if l == []:
        return []
    if l[0] == x:
        l[0] = y
    return [l[0]] + subst(l[1:],x,y)

print(subst(l1,3,999))

#################__2__##################
print('\nex 2.1')
def listaPares(lpares):
    if lpares == []:
        return ([],[])
    t = listaPares(lpares[1:])
    return [lpares[0][0]] + t[0], [lpares[0][1]] + t[1]

print(listaPares(lp))

print('\nex 2.2')
def remove_e_conta(l,x):
    if l == []:
        return ([],0)
    t = remove_e_conta(l[1:],x)
    if l[0] == x:
        return (t[0],t[1]+1)
    else:
        return ([l[0]] + t[0],t[1])

print(remove_e_conta([1,6,2,5,5,2,5,2],2))

print('\nex 2.3')
def sumElem(l):
    if l == []:
        return []
    t = sumElem(l[1:])
    for i in range(len(t)):
        if l[0] in t[i]:
            t[i] = (t[i][0], t[i][1] + 1)
            return t
    t.append((l[0],1))
    return t

print(sumElem(['a','a','c','b','c']))

#################__3__##################
print('\nex 3.1')
def firstElem(l):
    if len(l) == 0:
        return None
    else:
        return l[0]

print(firstElem([1,2,3,4]))
print(firstElem(''))

print('\nex 3.2')
def tailElem(l):
    if len(l) == 0:
        return None
    else:
        return l[1:]

print(tailElem([1,2,3,4]))
print(tailElem(''))

print('\nex 3.3')


print('\nex 3.4')
def menorElem(l):
    if len(l) == 0:
        return None
    else:
        menor = None
        for c in l:
            if menor is None:
                menor = c
            elif c < menor:
                menor = c
        return menor

print(menorElem([4,3,2,6,4,1]))
print(menorElem('diogo'))

#################__4__##################
print('\nex 4.1')
impar = lambda x: False if x % 2 == 0 else True

print(4, impar(4))
print(5, impar(5))

print('\nex 4.2')
positivo = lambda x: True if x>=0 else False

print(positivo(12))
print(positivo(-12))

print('\nex 4.3')
maior = lambda x,y: True if x<y else False

print(maior(2,4))
print(maior(4,2))

print('\nex 4.4')
import math
polarCoord = lambda x, y: (math.sqrt(x ** 2 + y ** 2), math.atan(y / x))

print(polarCoord(2, 4))

print('\nex 4.5')
f1 = lambda x,y: x%y
f2 = lambda x,y: x+y
f3 = lambda x,y: x//y
funcaoFinal = lambda x, y, z: f3(f1(x, y), f2(y, z))

print(funcaoFinal(20,22,71),'???')

print('\nex 4.6')
ex46 = lambda lst: all(map(positivo,lst))

print(ex46(l6))
print(ex46(l4))

print('\nex 4.7')
ex47 = lambda lst: any(map(positivo,lst))

print(ex47(l6))
print(ex47(l7))

print('\nex 4.8')
lst1 = [1,2,3]
lst2 = [1,2,6]
lst3 = [1,2,3,4,5]
ex48 = lambda lst1,lst2: all(x in lst2 for x in lst1)

print(ex48(lst1,lst3))
print(ex48(lst2,lst3))

print('\nex 4.9')
l49 = [1,3,2,6,4]
menorex49 = lambda x,y: True if x<y else False

def ex49(lst,funcao):
    if len(lst) == 1:
        return lst[0]
    else:
        rt = lst[0]
        for x in lst:
            if funcao(x,rt):
                rt = x
        return rt
## OR ##
def ex49_rec(lst,funcao):
    if len(lst) == 1:
        return lst[0]
    rec = ex49_rec(lst[1:],funcao)
    if funcao(lst[0],rec):
        return lst[0]
    return rec
print(ex49(l49,menorex49))
print('rec:',ex49_rec(l49,menorex49))
###############
# feito pelo prof
print('ex4.9 prof:')
def ex49_prof(lst, funcao):
    if lst == []:  # esta comparação é desnecessária, visto que a lista tem sempre 1 elemento, no mínimo
        return None

    rt = lst[0]  # iniciamos o valor máximo com o 1º elemento
    # como fazias já dentro da 3ª condição

    for x in lst[1:]:  # iteramos sobre a lista (a partir do 2.º elemento)
        if funcao(x, rt):
            rt = x
    return rt

print(ex49_prof(l49, menorex49))

def ex49Rec_prof(lst, funcao):  # forma recursiva
    if len(lst) == 1:
        return lst[0]
    rec = ex49Rec_prof(lst[1:], funcao)  # o menor dos elementos do resto da lista ...
    print('debug',lst, rec)
    if funcao(lst[0], rec):  # a comparação entre o menor do resto e a cabeça da lista
        return lst[0]
    return rec

print(ex49Rec_prof(l49, menorex49))

print('\nex 4.10')
l50 = [3,2,4,1,7,1]
def ex410(lst,funcao):
    if lst == []:
        return None
    elif len(lst) == 1:
        return (lst[0],[])
    else:
        rt = lst[0]
        for x in lst:
            if funcao(x,rt):
                rt = x
        return (rt,list(filter(lambda x: x!=rt,lst)))
## OR ##
def ex410_2(lst,funcao):
    rt = ex49(lst,funcao)
    #return (rt,list(filter(lambda x: x!=rt,lst)))
    ## OR ##
    return (rt,[x for x in lst if x !=rt])

print(ex410(l50,menorex49))
print(ex410_2(l50,menorex49))

print('\nex 4.11')
def ex411(lst,func):
    rt = ex410_2(lst,func)
    rt2 = ex410_2(rt[1],func)
    return (rt[0],rt2[0],rt2[1])

print(ex411(l50,menorex49))

print('\nex 4.12')
cordenList = [(3,5),(1,7),(9,3),(12,45)]
def ex412(lst):
    return [polarCoord(elem[0], elem[1]) for elem in lst]

#    if len(lst)==0:
#        return None
#    else:
#        listRt = []
#        for x,y in lst:
#            listRt.append(polarCood(x,y))
#        return listRt

print(ex412(cordenList))

print('\nex 4.13\n acabado mas - perguntar se esta bem!!')
lst1ord = [3,5,7,11,21]
lst2ord = [1,2,4,5,20,22]

#      3   5 7 11 16
#  1 2   4 5         20 22

def ex413_rec(lst1,lst2,func):
    if lst1 == []:
        return lst2
    elif lst2 == []:
        return lst1
    if func(lst1[0],lst2[0]):
        rec = ex413_rec(lst1[1:],lst2,func)
        tke = [lst1[0]]
    elif func(lst2[0],lst1[0]):
        rec = ex413_rec(lst1,lst2[1:],func)
        tke = [lst2[0]]
    else:
        rec = ex413_rec(lst1[1:], lst2[1:], func)
        tke = [lst1[0], lst2[0]]

    return tke + rec
    # if func(lst1[0],lst2[0]):
    #     rec[:0] = [lst1[0]]
    #     return rec
    # elif func(lst2[0],lst1[0]):
    #     rec[:0] = [lst2[0]]
    #     return rec
    # else:
    #     rec[:0] = [lst1[0]] + [lst2[0]]
    #     return rec

print(ex413_rec(lst1ord,lst2ord,menorex49))


print('\nex 4.14')
lstex414 = [[1,3,2],[4,2],[5,4,3,7,5],[0]]
funcEx14 = lambda x: x**2

def ex414(lst,func):
    newLst = []
    for x in lst:
        newLst += x
    return [funcEx14(x) for y in lst for x in y]

print(ex414(lstex414,funcEx14))

print('\nex 4.15')
parL = ([1,4,2,7,4,3],[1,4,3,6,4,3])
funcEx15 = lambda x,y: x+y

def ex415(parlst,func):
    if len(parlst[0]) != len(parlst[1]):
        return None
    elif parlst[0] == []:
        return []
    rec = ex415((parlst[0][1:],parlst[1][1:]),func)
    return [func(parlst[0][0],parlst[1][0])] + rec

print(ex415(parL,funcEx15))

#################__5__##################
print('\nex 5.1')
lstSelectSort = [4, 24, 2, 7, 8, 44, 22, 36, 2, 198, 100, 15]
def selectionSort(lst):
    if lst == []:
        return []
    min = reduce(lambda x,y: x if x<y else y,lst)
    lst.remove(min)
    lst[:0] = [min]
    final = selectionSort(lst[1:])
    return [lst[0]] + final
print(lstSelectSort)
print(selectionSort(lstSelectSort))
print(lstSelectSort)

print('\nex 5.2')
lstBubbleSort = [4, 24, 2, 7, 8, 44, 22, 36, 2, 198, 100, 15]
def bubbleSort(lst):
    if len(lst) == 0: return []
    for i in range(len(lst)-1):
        if lst[i]>lst[i+1]:
            lst[i], lst[i+1] = lst[i+1], lst[i]
    rec = bubbleSort(lst[:len(lst)-1])
    return rec + [lst[-1]]

print(bubbleSort(lstBubbleSort))

