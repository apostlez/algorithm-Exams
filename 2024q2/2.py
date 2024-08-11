from itertools import permutations

def solve(n, d):
  min_t = float('inf')
  max_t = float('-inf')
  found = False

  # 0 -> permutations for all city -> 0
  c_orders = permutations(list(range(1, n)))
  for c_order in c_orders:
#    print("ct:", c_order)
    # 0 -(tr)> permutations for all tr -(tr)> 0
    tr_orders = permutations(range(n))
    for tr_order in tr_orders:
#      print("tr:", tr_order)
      t = 0
      isValid = True
#      path = []
      
      # start 0-> n
      from_city = 0
      to_city = c_order[0]
      tr = tr_order[0]
      if d[tr][from_city][to_city] == 0:
        isValid = False
      else:
        t += d[tr][from_city][to_city]
#      path.append([from_city, tr, to_city])
      if isValid == False:
        continue
      # n -> n+1
      for i in range(n-2):
        from_city = c_order[i]
        to_city = c_order[i+1]
        tr = tr_order[i+1]
        if d[tr][from_city][to_city] == 0:
          isValid = False
          break
        t += d[tr][from_city][to_city]
      if isValid == False:
        continue

#        path.append([from_city, tr, to_city])
      
      # n -> 0
      from_city = c_order[-1]
      to_city = 0
      tr = tr_order[-1]
      if d[tr][from_city][to_city] == 0:
        isValid = False
      else:
        t += d[tr][from_city][to_city]
#      path.append([from_city, tr, to_city])
      
      if isValid:
        found = True
        min_t = min(min_t, t)
        max_t = max(max_t, t)
#        print("path:", path, "(", t, ")")
  
  if not found:
    print(0, 0)
  else:
    print(min_t, max_t)

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
  solve(n, d)
