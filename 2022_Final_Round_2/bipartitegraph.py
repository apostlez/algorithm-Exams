# 3
# 1 5 2
# 1 -  5
#   ＼ 4
# max 1+5 + 1+4 = 11 max' 1+5 = 6
#／
#
# 2 2 3
# 1 - 2
#   ／
# 2 - 1
# max 1+2 + 2+2 + 2+1 = 10 max' 1+2 + 2+2 = 7
# 3 2 4
# 1 1
# 2 1
# 2 2
# 3 2
# 2 - 2
#  ／
# 3 - 1
# 1／
# max 2+2 + 3+2 + 3+1 + 1+1 = 15 max' 2+2 + 3+2 + 3+1 = 13

# 가중치를 결정하는 순서는
# cond 1: 가장 많은 숫자의 연결을 가진 노드가 가장 큰 가중치를 가지고
# cond 2: 같은 숫자의 연결을 가진 노드 사이에서는 연결된 노드가 가진 연결의 숫자가 많은 노드가 큰 가중치를 가진다
# cond 3: 같은 숫자의 연결을 가진 노드들이 연결된 노드가 가진 연결의 숫자도 같은 경우, 먼저 가중치가 결정된 연결을 가진 쪽이 큰 가중치를 가진다.
# ex
# 3 2 4
# 1 1
# 2 1
# 2 2
# 3 2
########
# x
# 1 - 1
# 2 - 1, 2
# 3 - 2
# y
# 1 - 1, 2
# 2 - 2, 3
# step 1
# x: sort max node
# 2 - 1, 2
# 1 - 1
# 3 - 2
# y
# 1 - 1, 2
# 2 - 2, 3
# step 2
# x
# 2(3) - 1(2), 2(1)
# 1(2) - 1(2)
# 3(1) - 2(1)
# y
# 1 - 1, 2
# 2 - 2, 3

########
# 1 - 1
#  ／
# 3 - 2
# 2／


# 가중치를 결정하는 순서는
# cond 1: 가장 많은 숫자의 연결을 가진 노드가 가장 큰 가중치를 가지고
# cond 2: 같은 숫자의 연결을 가진 노드 사이에서는 연결된 노드가 가진 연결의 숫자가 많은 노드가 큰 가중치를 가진다
# cond 3: 같은 숫자의 연결을 가진 노드들이 연결된 노드가 가진 연결의 숫자도 같은 경우, 먼저 가중치가 결정된 연결을 가진 쪽이 큰 가중치를 가진다.

def calc(list_x, list_y, graph_x, graph_y, n, m, leng_x, leng_y):
    weight_x = [0] * n
    weight_y = [0] * m
    sum = 0
    for x in list_x:
        sum += len(graph_x[x]) * n
        weight_x[x] = n
        n -= 1
    for y in list_y:
        sum += len(graph_y[y]) * m
        weight_y[y] = m
        m -= 1
    
    # sort x
    # sort y
    # find each last
    min_last = sum
    n += 1
    m += 1
    #val_y = (n+1) + (m+1) + len(list_y)-1
    for last in graph_x[list_x[-1]]:
        #print("last in y", len(list_y), list_y.index(last))
        #cur_last =  val_y - list_y.index(last)
        cur_last =  n + weight_y[last]
        if cur_last < min_last:
            min_last = cur_last
    #val_x = (n+1) + (m+1) + len(list_x)-1
    for last in graph_y[list_y[-1]]:
        #print("last in x", len(list_x), list_x.index(last))
        #cur_last = val_x - list_x.index(last)
        cur_last =  m + weight_x[last]
        if cur_last < min_last:
            min_last = cur_last
    #return sum, sum - min_last
    print(sum, sum - min_last)

t = int(input())
for i in range(t):
    n, m, k = map(int, input().split(" "))
    graph_x = [[] for i in range(n)]
    graph_y = [[] for i in range(m)]
    list_x = [] * (n)
    list_y = [] * (m)
    leng_x = [0] * n
    leng_y = [0] * m
    for i in range(k):
        x, y = map(int, input().split(" "))
        x -= 1
        y -= 1
        graph_x[x].append(y)
        graph_y[y].append(x)
        leng_x[x] += 1
        leng_y[y] += 1
        if len(graph_x[x]) == 1:
            list_x.append(x)
        if len(graph_y[y]) == 1:
            list_y.append(y)
    #for i in list_x:
        #graph_x[i].sort(key=lambda y:len(graph_y[y]), reverse=True)
    #for i in list_y:
        #graph_y[i].sort(key=lambda x:len(graph_x[x]), reverse=True)
    list_x.sort(key=lambda x:len(graph_x[x]), reverse=True)
    list_y.sort(key=lambda y:len(graph_y[y]), reverse=True)
    #leng_x.sort(key=lambda x:leng_x[x], reverse=True)
    #leng_y.sort(key=lambda y:leng_y[y], reverse=True)
    #print(graph_x)
    #print(graph_y)
    #print(list_x)
    #print(list_y)
    #print(' '.join(map(str, calc(list_x, list_y, graph_x, graph_y, n, m))))
    calc(list_x, list_y, graph_x, graph_y, n, m, leng_x, leng_y)

