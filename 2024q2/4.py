
def calc_s(start, s, t, visited = [], found = False):
  ret = 0
  found_l = found

  if found == True:
    if start in s:
      return 0
    else:
      ret += 1
  else:
    if start in s:
      ret += 1
      found_l = True

  for neighbor in reversed(t.get(start, [])):
    if neighbor not in visited:
      ret += calc_s(neighbor, s, t, [start] + visited, found_l)
  return ret


#3 1		# n m(s)
#2		# s
#1 3		# n-1: t
#2 3		# n-1: t
# t[0] = [0], t[1] = [3], t[2] = [3], t[3] = [1,2]
# calc_s(1,) = 1, calc_s(2,) = 3, calc_s(3,) = 1
# ((2))-(1)-(4)-((3)) 에서 4 나 1 로 시작할 때, 양쪽을 따로 계산하지 못함
def calc_s_stack(start, n, s, t):
  visited = []
  stack = [start]
  counter = 0
  found = False

  while stack:
    node = stack.pop()
    if node in s:
      if found:
        continue
      found = True
    if found:
      counter += 1
    if node not in visited:
      visited.append(node)
#      if node not in s:
        # Add neighbors in reverse order to ensure correct order traversal
      for neighbor in reversed(t.get(node, [])):
        if neighbor not in visited:
          stack.append(neighbor)
    
  return counter

def solve(na, sa, ta, nb, sb, tb):
  calcDicta = {}
  calcDictb = {}
  total = 0
  for nai in range(1, na+1):
    calcDicta[nai] = calc_s(nai, sa, ta)
    for nbi in range(1, nb+1):
      calcDictb[nbi] = calc_s(nbi, sb, tb)
      total += calcDicta.get(nai) * calcDictb.get(nbi) * (nai + nbi)

#  for nai in range(1, na+1):
#    for nbi in range(1, nb+1):
#      total += calcDicta.get(nai) * calcDictb.get(nbi) * (nai + nbi)
#      print("total:", total)

#  print()
  print(total % (10**9+7))

def addtoDict(edge, tree):
  if edge[0] not in tree:
    tree[edge[0]] = []
  if edge[1] not in tree:
    tree[edge[1]] = []
  tree[edge[0]].append(edge[1])
  tree[edge[1]].append(edge[0])


t = int(input())

for _ in range(t):
  na, ma = map(int, input().split(" "))
  sa = list(map(int, input().split(" ")))
  ta = {}
  for ni in range(na-1):
    ta_node = list(map(int, input().split(" ")))
    addtoDict(ta_node, ta)
  nb, mb = map(int, input().split(" "))
  sb = list(map(int, input().split(" ")))
  tb = {}
  for ni in range(nb-1):
    tb_node = list(map(int, input().split(" ")))
    addtoDict(tb_node, tb)
  solve(na, sa, ta, nb, sb, tb)

