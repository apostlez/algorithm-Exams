

def zigzag(k, j, a) -> int:
    ret = 0
    length = 0
    if k>=j:
        return 0
    if a[k] == a[k+1]:
        return zigzag(k+1, j, a)
    if a[k] < a[k+1]:
        length = isNextSmall(k+1, j, a) + 1
    else:
        length = isNextBig(k+1, j, a) + 1
    ret += int((length * (length + 1)) / 2)
    ret += zigzag(k + length, j, a)
    return ret

def isNextSmall(k, j, a):
    if k < j and a[k] > a[k + 1]:
        return isNextBig(k + 1, j, a) + 1
    return 0

def isNextBig(k, j, a):
    if k < j and a[k] < a[k + 1]:
        return isNextSmall(k + 1, j, a) + 1
    return 0

def zigzagFor(k, j, a) -> int:
    ret = 0
    length = 1
    i = 1
    if j < 1 or (j == 1 and a[0] == a[1]):
        return 0
    if a[0] < a[1]:
        inc = 1
    elif a[0] > a[1]:
        inc = -1
    else:
        inc = 0
        length = 0
    while i < j:
        if a[i] < a[i+1]:
            if inc == -1:
                length = length + 1
            else:
                ret += int((length * (length + 1)) / 2)
                length = 1
            inc = 1
        elif a[i] > a[i+1]:
            if inc == 1:
                length = length + 1
            else:
                ret += int((length * (length + 1)) / 2)
                length = 1
            inc = -1            
        else:
            ret += int((length * (length + 1)) / 2)
            length = 0
            inc = 0
        i = i + 1
    ret += int((length * (length + 1)) / 2)
    return ret

t = int(input())
for i in range(t):
    n = int(input())
    a = list(map(int, input().split()))
    print(zigzagFor(0, n - 1, a))