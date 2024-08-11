def solve_dp(n, d):
    # DP 테이블 초기화
    # dp[mask][i][k] : 현재까지 방문한 도시들의 집합이 mask이고, i번 도시에서 끝나며, k번째 교통수단을 마지막으로 사용한 경로의 최소 비용.
    dp = [[[float('inf')] * n for _ in range(1 << n)] for _ in range(n)]
    dp[0][1][0] = 0  # 1번 도시에서 시작, 첫 교통수단 사용 전
    
    # DP 테이블 채우기
    for mask in range(1 << n):
        print("mask:", mask)
        for last in range(n): # 도시 탐색
            if not (mask & (1 << last)): # last번 도시가 이미 방문한 도시가 아니면 건너뜀
                print("jump:", last)
                continue
            for k in range(n-1):  # k는 사용한 교통수단
                print("k mask i:", k, mask, last)
#                if dp[k][mask][last] == float('inf'): # 유효하지 않은 경로는 건너뜀
#                    print("k ignored:", k, mask, last)
#                    continue
                for next in range(n):
                    print("mask i k j:", mask, last, k, next)
                    if mask & (1 << next): # next 도시가 이미 방문한 도시이면 건너뜀
                        print("jump j:", next, 1 << next)
                        continue
                    next_mask = mask | (1 << next)
                    travel_time = d[k][last][next]
                    if travel_time > 0:  # 이동 가능할 때만 갱신
#                        dp[k+1][next_mask][next] = min(dp[k+1][next_mask][next], dp[k][mask][last] + travel_time)
                        dp[k][next_mask][next] = dp[k][mask][last] + travel_time # ?
                        print("dp[k+1][next_mask][j]", dp[k+1][next_mask][next])
    
            new_dist = dp[mask][last] + distances[last][next]
            if new_dist < dp[new_mask][next]:
                dp[new_mask][next] = new_dist
                parent[new_mask][next] = last


    # 최소/최대 비용 계산
    min_cost = float('inf')
    max_cost = float('-inf')
    
    full_mask = (1 << n) - 1  # 모든 도시를 방문한 상태
    for k in range(n):
        if dp[k][full_mask][0] != float('inf'):  # 모든 도시를 방문하고 1번 도시로 돌아온 경우
            for last_city in range(1, n):  # 1번 도시에서 출발했으므로, 마지막 도시가 1번 도시가 아닌 경우
                if full_mask & (1 << last_city) and D[k][last_city][0] > 0:  # 1번 도시로 돌아오는 경로
                    total_cost = dp[k][full_mask][last_city] + d[k][last_city][0]
                    min_cost = min(min_cost, total_cost)
                    max_cost = max(max_cost, total_cost)
    
    if min_cost == float('inf'):
        print(0, 0)
    else:
        print(min_cost, max_cost)

t = int(input())

for _ in range(t):
    n = int(input())
    d = []
    for indexi in range(n):
        di = []
        for indexj in range(n):
            dj = list(map(int, input().split(" ")))
            di.append(dj)
        d.append(di)
    solve_dp(n, d)
