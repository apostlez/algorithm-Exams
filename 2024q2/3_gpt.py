from collections import deque

def bfs(capacity, source, sink, parent):
    visited = [False] * len(capacity)
    queue = deque([source])
    visited[source] = True

    while queue:
        u = queue.popleft()

        for v in range(len(capacity)):
            if not visited[v] and capacity[u][v] > 0:
                queue.append(v)
                visited[v] = True
                parent[v] = u

                if v == sink:
                    return True
    return False

def edmonds_karp(capacity, source, sink):
    parent = [-1] * len(capacity)
    max_flow = 0

    while bfs(capacity, source, sink, parent):
        path_flow = float('Inf')
        s = sink

        while s != source:
            path_flow = min(path_flow, capacity[parent[s]][s])
            s = parent[s]

        max_flow += path_flow
        v = sink

        while v != source:
            u = parent[v]
            capacity[u][v] -= path_flow
            capacity[v][u] += path_flow
            v = u

    return max_flow

def solve():
    T = int(input())
    for _ in range(T):
        N, M = map(int, input().split())
        robots = []
        robot_can_protect = [[] for _ in range(M)]

        for i in range(N):
            data = list(map(int, input().split()))
            g_i = data[0]
            x_i = data[1:]
            robots.append(x_i)
            for building in x_i:
                robot_can_protect[building - 1].append(i)
        
        L = []
        U = []
        for _ in range(M):
            l_k, u_k = map(int, input().split())
            L.append(l_k)
            U.append(u_k)
        
        results = []
        
        for B in range(1, M + 1):
            source = N + M
            sink = source + 1
            capacity = [[0] * (sink + 1) for _ in range(sink + 1)]
            
            for i in range(N):
                capacity[source][i] = B
            
            for j in range(M):
                capacity[N + j][sink] = U[j]

            for i in range(N):
                for building in robots[i]:
                    capacity[i][N + (building - 1)] = 1
            
            max_flow = edmonds_karp(capacity, source, sink)
            possible = True
            
            for j in range(M):
                total_flow = sum(capacity[N + j][sink + k] for k in range(1))
                if total_flow < L[j]:
                    possible = False
                    break
            
            if not possible:
                results.append(-1)
            else:
                results.append(sum(U) - sum(L) + max_flow)
        
        print(" ".join(map(str, results)))

# 실행 예시
solve()
