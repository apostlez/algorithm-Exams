t = int(input())
n = int(input())
l = list(map(int, input().split(" ")))
r = list(map(int, input().split(" ")))

# 6
# 0 5 6 2 0 3
# 0 0 0 0 0 1

def checktree(l, r):
    # check parent
    hasParent_l = [None] * n
    hasParent_r = [None] * n
    for i in range(n):
        if l[i] != 0:
            hasParent_l[l[i]] = True
        if r[i] != 0:
            hasParent_r[r[i]] = True
    if hasParent_l.count(False) > 1 or hasParent_r.count(False) > 1:
        return False


    return True

checktree()

# 자기 자신 위치는 갈 수 없음

# 올바른 이진트리
# - 부모가 없는 노드 1개 : 루트 노드, 나머지는 부모 있음
# 모든 노드에서 부모로 갈 수 있음



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
