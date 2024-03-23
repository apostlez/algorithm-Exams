# 9
# 1 1
# 1 => 1
# 5 1
# 01010 => 1
# 5 2
# 01010 => 2
# 5 3
# 01010 => 0
# 8 2
# 01100110 => 3
# 14 2
# 00100100100100 => 3
# 13 3
# 0111010100100 => 2
# 61 21
# 1001001001001001001001001001001001001001001001001001001001001 => 486784380
# 3 2
# 000 => 2

# 1001 => 1 001, 10 01, 100 1 => 3
divide = int(10e8) + 7


def cal():
    n, k = map(int, input().split(" "))
    a = input()

    # count 1
    counter = a.count("1")
    if counter % k != 0:
        print(0)
        return
    toping = int(counter / k)
    total = 1
    if counter == 0:
        for index in range(k - 1):  
           total = total * (n - index - (k - 1))
        print((total%divide))
        return
    # count 0
    tc = 0 # toping count
    nt = 0 # no toping
    split = False
    for index in range(len(a)):
        if split:
            if a[index] == "1":
                total = total * (nt + 1)
                nt = 0
                split = False
            if a[index] == "0":
                nt = nt + 1
        if not split and a[index] == "1":
            tc = tc + 1
            if tc == toping:
                split = True
                tc = 0
    print( (total % divide) )

def cal2():
    n, k = map(int, input().split(" "))
    a = input()
    new_a = a.strip("0")
    list = new_a.split("1")
    if (len(list)-1) % k != 0:
        print(0)
        return
    total = 1
    for item in list:
        total = total * len(item)
    print(list)

t = int(input())
for i in range(t):
    cal()

# 5 3
# 00000 
# 0 0 000
# 0 00 00
# 0 000 0
# 00 0 00
# 00 00 0
# 000 0 0
# (5-1) + (4-1)
# 6 = 3 * 2 * 1
#   = (5 - (3 -1)) * ((5 - 1) - (3 -1))
