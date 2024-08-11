# 재귀호출은 확인 못하고
# 스택 루프는 메모리 초과

def removeRobot(robots, L, U, B, N):
    max_guard = -1
    stack = [(robots, L[:], U[:])]  # 스택에는 (robots, L, U)의 튜플을 저장

    while stack:
        current_robots, current_L, current_U = stack.pop()

        # base case: L의 합이 0인 경우
        if sum(current_L) == 0:
            available_build = [0] * len(current_U)
            for robot in current_robots:
                if B > L[robot[1]] + available_build[robot[1]]:
                    available_build[robot[1]] += 1
                if available_build[robot[1]] > current_U[robot[1]]:
                    available_build[robot[1]] = current_U[robot[1]]
            max_guard = max(max_guard, sum(available_build))
            continue

        found = False
        for ir in range(len(current_robots)):
            if current_L[current_robots[ir][1]] > 0:
                found = True
                new_L = current_L[:]
                new_U = current_U[:]
                new_L[current_robots[ir][1]] -= 1
                new_U[current_robots[ir][1]] -= 1
                new_robots = current_robots[:ir] + current_robots[ir+1:]
                stack.append((new_robots, new_L, new_U))

        if not found:
            return -1

    return max_guard

def removeRobot_rec(robots, L, U, B, N, ori_L):
    # check L
    if sum(L) == 0:
        # count remained U with remain robot
        # [0, 0, 0], [1, 0, 1]
        available_build = [0] * len(U)
        for robot in robots:
            if B > ori_L[robot[1]] + available_build[robot[1]]:
                available_build[robot[1]] += 1
            if available_build[robot[1]] >= U[robot[1]]:
                available_build[robot[1]] = U[robot[1]]
        return sum(available_build)
    max_guard = -1
    found = False
    for ir in range(len(robots)):
        if L[robots[ir][1]] > 0:
            found = True
            L[robots[ir][1]] = L[robots[ir][1]] - 1
            U[robots[ir][1]] = U[robots[ir][1]] - 1
            removed_robot = robots.pop(ir)
            ret = removeRobot_rec(robots, L, U, B, N, ori_L)
            robots.insert(ir, removed_robot)
            L[robots[ir][1]] = L[robots[ir][1]] + 1
            U[robots[ir][1]] = U[robots[ir][1]] + 1
            max_guard = max(ret, max_guard)
    if found == False:
        return -1
    return max_guard

def solve():
    N, M = map(int, input().split())
    robots = []
    robot_can_protect = [[] for _ in range(M)]
    robot_pair = []

    for i in range(N):
        data = list(map(int, input().split()))
        g_i = data[0]
        x_i = data[1:]
        robots.append(x_i)
        for building in x_i:
            robot_pair.append([i, building -1])
            robot_can_protect[building - 1].append(i)
    
    L = []
    U = []
    for _ in range(M):
        l_k, u_k = map(int, input().split())
        L.append(l_k)
        U.append(u_k)
    
    results = []

    for B in range(1, M + 1):
        ret = removeRobot_rec(robot_pair, L, U, B, N, L[:])
#        ret = removeRobot(robot_pair, L, U, B, N)
        if ret == -1:
            print(-1, -1, -1)
            return
        results.append(min(ret + sum(L), B * N))
    
    print(" ".join(map(str, results)))

# 실행 예시
T = int(input())
for _ in range(T):
    solve()
