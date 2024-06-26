# 7
# 4 2 10
# 2 4 11
# 5 3 15
# 20 22 2022
# 6 9 2000000000
# 1 1 2000000000
# 4 7 2

# 5 3 15
# 최소공배수: (5, 3) = 15
# 최대공약수: (5, 3) = 1
# 빼기 반복: 5 - 3 = 2, 2 - 3 = -1(o)
# 나머지: 5 % 3 = 2
# 나머지 + 빼기: 5 % 3 = 2, 2 - 5 = -3(x), 2 - 3 = -1(o)

# 짝수, 홀수 단위로 각각 계산해야 할 듯.


def calc(list_x, list_y, graph_x, graph_y, n, m):
    sum = 0
    for x in list_x:
        sum += len(graph_x[x]) * n
        n -= 1
    for y in list_y:
        sum += len(graph_y[y]) * m
        m -= 1
    
    # sort x
    # sort y
    # find each last
    min_last = sum
    val_y = (n+1) + (m+1) + len(list_y)-1
    for last in graph_x[list_x[-1]]:
        #print("last in y", len(list_y), list_y.index(last))
        cur_last =  val_y - list_y.index(last) # <- one more loop
        if cur_last < min_last:
            min_last = cur_last
    val_x = (n+1) + (m+1) + len(list_x)-1
    for last in graph_y[list_y[-1]]:
        #print("last in x", len(list_x), list_x.index(last))
        cur_last = val_x - list_x.index(last) # <- one more loop
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
    for i in range(k):
        x, y = map(int, input().split(" "))
        x -= 1
        y -= 1
        graph_x[x].append(y)
        graph_y[y].append(x)
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
    #print(graph_x)
    #print(graph_y)
    #print(list_x)
    #print(list_y)
    #print(' '.join(map(str, calc(list_x, list_y, graph_x, graph_y, n, m))))
    calc(list_x, list_y, graph_x, graph_y, n, m)

