
from queue import PriorityQueue
t = int(input())

for _ in range(t):

    n, m = map(int, input().split(" "))

    dist = [[-1 for i in range(n)] for j in range(n)]
    dist_source = [[0 for i in range(n)] for j in range(n)]
    graph = [[] for i in range(n)]
    edges = [] * n

    for ni in range(n-1):
        x, y, d = map(int, input().split(" "))
        edges.append([x-1, y-1, d])
        graph[x-1].append(y-1)
        graph[y-1].append(x-1)
        dist_source[x-1][y-1] = d
        dist_source[y-1][x-1] = d

    changes = [] * m
    for mi in range(m):
        aj, xj, yj, dj = map(int, input().split(" "))
        changes.append([aj-1, xj-1, yj-1, dj])

    def dijkstra(start):
        dist[start][start] = 0
        que = PriorityQueue()
        que.put([dist_source[start][start], start])
        while que.empty() == False:
            queItem = que.get()
            distance, current = queItem
            if dist[start][current] != -1 and dist[start][current] < distance:
                continue
            for dest in graph[current]:
                nextDist = distance + dist_source[current][dest]
                if dist[start][dest] == -1 or nextDist < dist[start][dest]:
                    dist[start][dest] = nextDist
                    dist[dest][start] = nextDist
                    # is it possible to store all path?
                    #print("found!: from", start, "to", dest, nextDist)
                    que.put([nextDist, dest])

    for ni in range(n):
        dijkstra(ni)
    #print(dist)
    t = sum(sum(dist, []))
    tmin = -1
    tmax = -1
    # apply change edge
    for mi in range(m): 
        # change dist
        dist = [[-1 for i in range(n)] for j in range(n)]
        # change graph
        aj, xj,yj, dj = changes[mi]
        x, y, d = edges[aj]
        graph[x].remove(y)
        graph[y].remove(x)
        graph[xj].append(yj)
        graph[yj].append(xj)
        # change dist
        ori_dist = dist_source[x][y]
        dist_source[x][y] = 0
        dist_source[y][x] = 0
        dist_source[xj][yj] = dj
        dist_source[yj][xj] = dj
        # find value
        for ni in range(n):
            dijkstra(ni)
        #print(dist)
        #print(sum(sum(dist, [])))
        tmp = sum(sum(dist, []))
        if tmin == -1 or tmin > tmp:
            tmin = tmp
        if tmax == -1 or tmax < tmp:
            tmax = tmp
        # restore graph
        graph[xj].remove(yj)
        graph[x].append(y)
        graph[yj].remove(xj)
        graph[y].append(x)
        # restore dist_source
        dist_source[xj][yj] = 0
        dist_source[yj][xj] = 0
        dist_source[x][y] = ori_dist
        dist_source[y][x] = ori_dist
    print(t>>1, tmin>>1, tmax>>1)

  

