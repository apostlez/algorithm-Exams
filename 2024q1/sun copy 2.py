
MAX = 10**13
t = int(input())

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
        # charge
        bt = battery + p[i]
        if bt > c:
            bt = c
        cfd = cost + (f[i] * dd)
        if cfd < minValue:
            stack.append([i+1, cfd, bt])
        # use
        dd = d[i]
        bd = battery - dd
        if bd >= 0:
            stack.append([i+1, cost, bd])


for _ in range(t):
    n, b, c = map(int, input().split(" "))
    p = list(map(int, input().split(" ")))
    f = list(map(int, input().split(" ")))
    d = list(map(int, input().split(" ")))

    print(dfs())
