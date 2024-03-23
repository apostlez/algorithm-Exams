# 7
# 4 2 10
# 2 4 11
# 5 3 15
# 20 22 2022
# 6 9 2000000000
# 1 1 2000000000
# 4 7 2

# 11 17 5 -> 5
# 11 + 11 - 17 = 5
# 17 - (11 + 11 - 17) = 12
# 17 - (11 + 11 - 17) - 11 = 1
# |11 - 17| = 6
# 11 % 6 = 5
# 17 % 6 = 5

# 17 % 11 = 6
# 11 % 6 = 5
# 6 % 5 = 1
# 5 % 1 = 0

t = int(input())
for i in range(t):
    a, b, x = map(int, input().split(" "))
    if a == 1 or b == 1:
        print(x)
        continue
    big = max(a, b)
    small = min(a,b)
    rest = big % small
    while rest != 0:
        big = small
        small = rest
        rest = big % small
    print(int(x/small))
