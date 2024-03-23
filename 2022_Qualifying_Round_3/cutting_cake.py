# SBRSBR => SBR
# S|B|R S B R
# S|B R|S B R
# S|B R S|B R
# S|B R S B|R
# S B|R S B|R
# S B R|S B|R
# S B R S|B|R
# 7

# S|B|R S B R => 1
# S(B R|S|B)R => 3
#(S B|R|S)B|R => 3

divide = int(1e9) + 7

def check_v(r, c, n, cake, friend, x, y, cakenum):
    for xx in range(r-x):
        if cake[xx+x][y] == friend[cakenum]:
            return True
    return False

def check_h(r, c, n, cake, friend, x, y, cakenum):
    for yy in range(c-y):
        if cake[x][yy+y] == friend[cakenum]:
            return True
    return False

def vertical(r, c, n, cake, friend, x, y, cakenum):
    ret = 0
    found = False
    if r == x or c == y:
        return 0
    for yy in range(c-y):
        if check_v(r, c, n, cake, friend, x, yy + y, cakenum) == True:
            found = True
        if found:
            if n - 1 == cakenum:
                #print("cut", cake[x][yy+y:c], friend[cakenum], "found!")
                return 1
            #print("cut", cake[x][y:yy+1], friend[cakenum])
            if n - 2 != cakenum:
                ret += horizontal(r, c, n, cake, friend, x, yy + y + 1, cakenum + 1)
            ret += vertical(r, c, n, cake, friend, x, yy + y + 1, cakenum + 1)
    return ret

def horizontal(r, c, n, cake, friend, x, y, cakenum):
    ret = 0
    found = False
    if r == x or c == y:
        return 0
    for xx in range(r-x):
        if check_h(r, c, n, cake, friend, xx + x, y, cakenum) == True:
            found = True
        if found:
            if n - 1 == cakenum:
                return 1
            if n - 2 != cakenum:
                ret += vertical(r, c, n, cake, friend, xx + x + 1, y, cakenum + 1)
            ret += horizontal(r, c, n, cake, friend, xx + x + 1, y, cakenum + 1)
    return ret

t = int(input())
for i in range(t):
    r, c, n = map(int, input().split(" "))
    cake = [] * (r)
    for j in range(r):
        cake.append(input()) # r * c
    friend = input() # n
    count = 0
    if n != 1:
        count += horizontal(r, c, n, cake, friend, 0, 0, 0)
    count += vertical(r, c, n, cake, friend, 0, 0, 0)
    print(count % divide)
