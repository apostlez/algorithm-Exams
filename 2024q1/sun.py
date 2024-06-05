
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

    cost = 0
    battery = b
    for i in range(n):
        if battery < d[i]:
            battery += p[i]
            cost += f[i] * d[i]
        else:
            break
    last_b = b
    for j in range(n-1, i, -1):
        if last_b + d[j] >= c:
            last_b -= p[j]
            cost += f[j] * d[j]
        else:
            break
    if i != j:
        for k in range(i, j):
            if battery > d[k]:
                if sum(d[k:j]) < battery:
                    # ? 2개 미리 보기
                    battery -= d[k]
            else:
                battery += p[k]
                if battery > c:
                    battery = c
                cost += f[k] * d[k]
    print(cost)



    
        
