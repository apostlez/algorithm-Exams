
t = int(input())

for _ in range(t):
    n = int(input())
    d = list(map(int, input().split(" ")))
    s = input()
    r = list(map(int, input().split(" ")))

    x = d[n-1] + 1
#    rounded = False
#    print("[Debug]", x)
    for i in range(n-2, -1, -1):
        if s[i] == '+':
            x = x - r[i]
            if x <= 0:
                x = 1
#            print("[Debug]", x)
            x = x + d[i]
#            print("[Debug]", x)
        else:
            x, mod = divmod(x, r[i])
#            print("[Debug]", x)
            if mod > 0:
#                rounded = True
                x += 1
#                print("[Debug]", x)
            x = x + d[i]
#            print("[Debug]", x)
#    if rounded == False:
#        x += 1
    print(x)
        
