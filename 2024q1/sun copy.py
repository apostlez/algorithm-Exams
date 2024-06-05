
MAX = 10**13
t = int(input())

def findA(index, cost, battery): # 시간초과
    if index == n:
        if battery >= b:
            return cost
        return MAX
    # use
    costA = MAX
    dd = d[index]
    ff = f[index]
    fd = (ff * dd)
    cfd = cost + fd
    bd = battery - dd
    if bd >= 0:
        costA = findA(index+1, cost, bd)
    # charge
    bt = battery + p[index]
    if bt > c:
        bt = c
    costB = MAX
    if costA > cfd:
        costB = findA(index+1, cfd, bt)
    return min(costA, costB)

def dfs(): # 시간초과
    index = 0
    stack = []
    minValue = MAX

    battery = b
    dd = d[index]
    ff = f[index]
    fd = (ff * dd)
    bd = battery - dd
    # charge
    bt = battery + p[index]
    if bt > c:
        bt = c
    stack.append([index+1, fd, bt])
    # use
    if bd >= 0:
        stack.append([index+1, 0, bd])

    while True:
        if len(stack) == 0:
            return minValue
        i, cost, battery = stack.pop()
        if i == n:
            if battery >= b:
                minValue = min(cost, minValue)
            continue
        # use
        dd = d[i]
        bd = battery - dd
        if bd >= 0:
            stack.append([i+1, cost, bd])
        # charge
        bt = battery + p[i]
        if bt > c:
            bt = c
        cfd = cost + (f[i] * dd)
        if cfd < minValue:
            stack.append([i+1, cfd, bt])


def find(n, b ,c, p, f, d, index, cost, battery): # 시간초과
    if index == n:
        if battery >= b:
            return cost
        return -1
    # use
    costA = -1
    if battery >= d[index]:
        costA = find(n, b ,c, p, f, d, index+1, cost, battery - d[index])
    # charge
    costB = -1
    bt = battery + p[index]
    if bt > c:
        bt = c
    costB = find(n, b ,c, p, f, d, index+1, cost + (f[index] * d[index]), bt)
    if costA != -1 and costB != -1:
        return min(costA, costB)
    return max(costA, costB)

def bfs(n, b ,c, p, f, d): # 메모리 초과
    minValue = 0
    index = 0
    queue = []
    if b >= d[0]:
        queue.append([1, 0, b-d[0]])
    cost = f[0] * d[0]
    bt = b + p[0]
    if bt > c:
        bt = c
    queue.append([1, cost, bt])

    while True:
        if len(queue) == index:
            return minValue
        i, cost, battery = queue[index]
        index += 1
        if i == n:
            if battery >= b:
                if minValue == 0:
                    minValue = cost
                else:
                    minValue = min(cost, minValue)
            continue
        # use
        if battery >= d[i]:
            queue.append([i+1, cost, battery - d[i]])
        # charge
        bt = battery + p[i]
        if bt > c:
            bt = c
        queue.append([i+1, cost + (f[i] * d[i]), bt])

def bfs_queue(n, b ,c, p, f, d): # 시간초과
    minValue = 0
    queue = []
    if b >= d[0]:
        queue.append([1, 0, b-d[0]])
    bt = b + p[0]
    if bt > c:
        bt = c
    queue.append([1, f[0] * d[0], bt])

    while True:
        if len(queue) == 0:
            return minValue
        i, cost, battery = queue.pop(0)
        if i == n:
            if battery >= b:
                if minValue == 0:
                    minValue = cost
                else:
                    minValue = min(cost, minValue)
            continue
        # use
        if battery >= d[i]:
            queue.append([i+1, cost, battery - d[i]])
        # charge
        bt = battery + p[i]
        if bt > c:
            bt = c
        queue.append([i+1, cost + (f[i] * d[i]), bt])

def linear(n, b, c, p, f, d):
    battery = b
    cost = 0
    for i in range(n-1):
        if battery >= d[i] and battery + sum(p[i+1:]) - sum(d[i+1:]) > b and f[i] * d[i] >= f[i+1] * d[i+1]:
            battery -= d[i]
        else:
            battery += p[i]
            if battery > c:
                battery = c
            cost += f[i] * d[i]
    if battery - d[n-1] >= b:
        return cost
    else:
        return cost + f[n-1] * d[n-1]


for _ in range(t):
    n, b, c = map(int, input().split(" "))
    p = list(map(int, input().split(" ")))
    f = list(map(int, input().split(" ")))
    d = list(map(int, input().split(" ")))

#    print(find(n, b ,c, p, f, d, 0, 0, b))
#    print(bfs_queue(n, b ,c, p, f, d))
#    print(findA(0, 0, b))
#    print(linear(n, b ,c, p, f, d))
    print(dfs())
