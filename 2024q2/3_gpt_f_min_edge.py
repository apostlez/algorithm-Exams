from collections import deque
from itertools import combinations
from itertools import product

def networkflow(source, sink, N, M, capacity, flow, L):
    v = sink + 1
    totalFlow = 0
    possible = False

    while True:
        parent = [-1] * len(capacity)
        queue = deque([source])
        parent[source] = source
        # parent == visited
        while queue and parent[sink] == -1:
            here = queue.popleft()
            # 잔여용량이 있을 때만 해당 경로로 탐색 및 방문 체크
            for there in range(v):
                if capacity[here][there] - flow[here][there] > 0 and parent[there] == -1:
                    queue.append(there)
                    parent[there] = here

        # 경로를 다 돌았는데 싱크를 못찾았을 경우 탐색 종료
        if parent[sink] == -1:
            break
        amount= float('Inf')
        # 탐색한 경로를 되짚어가면서 경로에 흐를 수 있는 유량을 구함
        s = sink
        while s != source:
            amount = min(amount, capacity[parent[s]][s] - flow[parent[s]][s])
            s = parent[s]

        totalFlow += amount
        # 증가 경로는 유량 추가, 그리고 역방향으로 음수 유량을 추가 함
        p = sink
        while p != source:
            flow[parent[p]][p] += amount
            flow[p][parent[p]] -= amount
            p = parent[p]
        # check L
#        if possible == False:
#            tmpp = True
#            for j in range(M):
#                total_flow = sum(flow[k+1][N+j+1] for k in range(N))
#                if total_flow < L[j]:
#                    tmpp = False
#            if tmpp == True:
#                possible = True
#    if possible:
#        return totalFlow
#    return -1
    return totalFlow

def canGuard(N, M, robots, U, capacity, B, flow, L):
    SOURCE = 0
    SINK = N + M + 1

    # SOURCE 와 로봇 연결, capacity 는 로봇이 지킬수 있는 숫자로 B
    for i in range(N):
        capacity[SOURCE][i + 1] = B
    # 대피소와 SINK 를 연결, capacity 는 최대로 지켜야 하는 숫자이므로 U
    # SINK 는 최소값 노드로 추가, 원래 SINK는 SINK+1로
    for j in range(M):
        capacity[N + j + 1+1][SINK+1] = U[j]
    # 로봇과 건물을 연결, capa 는 1
    for i in range(N):
        for building in robots[i]:
            capacity[i+1][N + (building)] = 1
    
    # Split the node min_flow_node into min_flow_node_in and min_flow_node_out
    # Adding the minimum flow constraint
    min = 1
    ln = SINK + 1
    for j in range(M):
        min_flow_node = N + j + 1
        capacity[min_flow_node][SINK] = -L[j]

#        # Redirect the original outgoing edges to the new node
#        for k in range(SINK+1):
#            capacity[ln+j][k] = capacity[min_flow_node][k]
#            capacity[min_flow_node][k] = 0

    maxFlow = networkflow(SOURCE, SINK+1, N, M, capacity, flow, L)
    return maxFlow


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
    

    for B in range(1, M + 1):
        source = N + M
        sink = source + 1
        min = 1
        #capacity = [[0] * (sink + 1) for _ in range(sink + 1)]
        capacity = [[0] * (sink + 1 + min) for _ in range(sink + 1 + min)] # add L
        flow = [[0] * (len(capacity)) for _ in range(len(capacity))]

        max_flow = canGuard(N, M, robots, U, capacity, B, flow, L)
        print("max_flow:", max_flow)

#        for i in range(len(flow)):
#            for j in range(len(flow[i])):
#                if flow[i][j] > 0:
#                    print(f"Edge {i} -> {j}: Flow {flow[i][j]}")
        # To find the minimum flow to a specific node (e.g., node 3)
#        specific_node = 2
#        incoming_flows = [flow[i][specific_node] for i in range(len(flow))]
#        print("::", incoming_flows)
#        print("::", min(incoming_flows))

        if max_flow == -1:
            results = [-1, -1, -1]
            break
        else:
            results.append(max_flow)
    
    print("==", " ".join(map(str, results)))

# 실행 예시
T = int(input())
for _ in range(T):
    solve()
