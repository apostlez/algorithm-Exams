import sys
def solve():
    n = int(sys.stdin.readline())
    v = list(map(int, sys.stdin.readline().split()))
 
    sum_of_num = sum(v)
    v_max = max(v)
 
    if v_max > (sum_of_num + 1) // 2:
        print("IMPOSSIBLE")
        return
 
 
    result = [0 for _ in range(sum_of_num)]
 
    # 예외 처리
    # O(m)
    index = [0, 1]
    current = 0
    for i in range(n):
        index[current] += 2 * v[i]
        if index[current] > sum_of_num:
            for j in range(v[i]):
                result[sum_of_num - j * 2 - 1] = i + 1
            v[i] = 0
            break
        if index[current] > index[1-current]:
            current = 1 - current
 
    # 규칙에 따라 풀기
    # O(m)
    index = [0, 1]
    current = 0
    meet = False
    for i in range(n):
        for j in range(v[i]):
            if index[current] < sum_of_num and result[index[current]] and not meet:
                meet = True
                index[current] += 1
            while index[current] < sum_of_num and result[index[current]]:
                index[current] += 2
            result[index[current]] = i + 1
            index[current] += 2
 
        if index[current] > index[1-current]:
            current = 1 - current
 
        if meet:
            index[1-current] = sum_of_num
 
    # print(result)
 
    total = 0
    for i in range(sum_of_num):
        total = (total + (i+1) * result[i]) % 987654323
    print(total)

t = int(sys.stdin.readline())
for _ in range(t):
    solve()