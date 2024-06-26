

t = int(input())

for _ in range(t):
  n, m = map(int, input().split(" "))
  a = map(int, input().split(" "))
  counter = [0] * n
  for i in range(m):
    x, y = map(int, input().split(" "))
    for j in range(x-1, y):
      counter[j] = counter[j] + 1

  sorted_a = sorted(a, reverse = True)
  sorted_counter = sorted(counter, reverse = True)
  sum = sorted_counter[0] * sorted_a[0]
  case_num = 1
  total = 1
  for i in range(1, n):
    sum += sorted_counter[i] * sorted_a[i]
    if sorted_counter[i] == sorted_counter[i-1]:
      case_num += 1
    else:
      total *= case_num
      case_num = 1
    if sorted_counter[i] == 0:
      total *= case_num
      case_num = 1
      break
  total *= case_num
  print(sum, total)
