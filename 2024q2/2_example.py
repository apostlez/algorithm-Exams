# Held-Karp algorithm

import math

# Example distances between cities (cost matrix)
distances = [
    [0, 20, 42, 35],
    [20, 0, 30, 34],
    [42, 30, 0, 12],
    [35, 34, 12, 0]
]

n = len(distances)  # Number of cities
dp = [[math.inf] * n for _ in range(1 << n)]
parent = [[None] * n for _ in range(1 << n)]

# Base case: starting from city 0
dp[1][0] = 0

# Fill DP table
for mask in range(1 << n):
    for last in range(n):
        if not (mask & (1 << last)):
            continue
        for next in range(n):
            if mask & (1 << next):
                continue
            new_mask = mask | (1 << next)
            new_dist = dp[mask][last] + distances[last][next]
            if new_dist < dp[new_mask][next]:
                dp[new_mask][next] = new_dist
                parent[new_mask][next] = last

# Find the optimal tour and minimum cost
min_cost = math.inf
end_city = None
full_mask = (1 << n) - 1

for last in range(1, n):
    cost = dp[full_mask][last] + distances[last][0]
    if cost < min_cost:
        min_cost = cost
        end_city = last

# Reconstruct the optimal tour
tour = []
mask = full_mask
last = end_city
while mask:
    tour.append(last)
    new_last = parent[mask][last]
    mask ^= (1 << last)
    last = new_last
tour = tour[::-1]
tour.append(0)  # Add the starting city at the end to complete the loop

# Print the final dp table values
print("Final DP Table:")
for mask in range(1 << n):
    print(f"mask = {mask:04b}: {dp[mask]}")

# Print the optimal tour and minimum cost
print("\nOptimal Tour:", tour)
print("Minimum Cost:", min_cost)