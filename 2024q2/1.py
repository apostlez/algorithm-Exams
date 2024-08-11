from itertools import combinations

def insert_plus_1(s):
  # gen all expr
  n = len(s)
  result = []
  
  for i in range(n, -1, -1):
    for comb in combinations(range(1, n), i):
      expr = list(s)
      for idx in comb:
        expr.insert(idx + comb.index(idx), '+')
      result.append(''.join(expr))
  
  return result

def split_array(s):
  # gen all expr
  n = len(s) # 123
  result = []
  
  for i in range(n, -1, -1): # 2
    for comb in combinations(range(1, n), i): # 1 2
      expr = []
      if len(comb) == 0:
        result.append(([s]))
        continue
      if len(comb) == 1:
        expr.append((s[0:comb[idx]]))
        expr.append((s[comb[idx]:n]))
        result.append(expr)
        continue
      expr.append((s[0:comb[0]]))
      for idx in range(len(comb)-1):
        expr.append((s[comb[idx]:comb[idx+1]]))
      expr.append((s[comb[idx+1]:n]))
      result.append(expr)
  
  return result

def myEval(expr):
  nums = expr.split("+")
  nums = [int(num) for num in nums]
  return sum(nums)

def eval_plus(expr):
  ret = 0
  ni = 1
  for i in range(1, len(expr)):
    if expr[i] == '+':
      ret = ret + 2**(global_m-1-ni)
    else:
      ni = ni + 1
  return ret

def eval_array(expr):
  ret = 0
  ni = 1
  local_m = global_m-1
  for i in range(len(expr)-1):
    ret = ret + 2**(local_m-len(expr[i]))
    local_m = local_m - len(expr[i])
  return ret

def solve(s, v):
  #all_expr = insert_plus(s)
  n = len(s)
  all_expr = split_array(s)
  
  # calc all to (value, expr, +)
  #ev_expr = [(myEval(expr), expr, expr.count('+')) for expr in all_expr]
  ev_expr = [(sum(map(int, expr)), expr, len(expr)-1) for expr in all_expr]
  
  #ev_expr.sort(key=lambda x: (x[0], x[2], x[1].find('+')))
  ev_expr.sort(key=lambda x: (x[0], -eval_array(x[1])))

  for ev in ev_expr:
    result_value, expr, plus_count = ev
    print("==", expr, "(", eval_array(expr), ")", result_value, plus_count)
  
  ws = 0
  wp = 0
  for vi in v:
    value_at_position = ev_expr[vi - 1]
    result_value, expr, plus_count = value_at_position
    #print("==", expr, "(", eval_plus(expr), ")", result_value, plus_count)
    ws += result_value
    wp += plus_count
  
  print(ws, wp)

t = int(input())
global_m = 0

for _ in range(t):
  strn, x = input().split(" ")
  global_m = len(x)
  v = list(map(int, input().split(" ")))
  solve(x, v)

#어떤 숫자 문자열에 + 기호를 숫자 사이에 추가해 수식들을 만들고, 수식의 계산 결과가 작은 순서, 수식의 계산 결과가 같으면, + 기호가 더 앞쪽에 있는 순서로 수식들을 정렬했을 때,
#입력받은 순서의 수식의 계산 결과와 사용된 + 기호의 숫자를 출력하는 프로그램을 파이썬으로 작성

#예를 들어, 숫자 문자열이 [2, 0, 2, 4]이면 8개의 수식을 만들 수 있고,
#정렬된 수식의 계산 결과와 사용된 + 기호는 [(8, 3), (8, 2), (26, 2), (26, 1), (26, 2), (44, 1), (206, 1), (2024, 0)] 이므로
#7번째 값을 출력하는 경우 (206, 1) 이다.

