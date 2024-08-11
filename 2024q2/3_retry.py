from collections import deque
from itertools import combinations
from itertools import product

def solve():
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
    
    capacity = [[0] * (N) for _ in range(M)]

    combinations = list(product(combinations(list1, 2),
                                      combinations(list2, 2),
                                      combinations(list3, 2)))
    # Flatten and print the combinations
    flattened_combinations = [tuple(itertools.chain(*comb)) for comb in combinations]
    for combination in flattened_combinations:
        print(combination)


    for B in range(1, M + 1):
        for i in range(N):
            for buildings in combinations(robots[i]):
                for building in buildings:
                    capacity[i][building] = 1
                    if sum(capacity[k][building] for k in range(N)) > U[building]:
                        # failed




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
T = int(input())
for _ in range(T):
    solve()
