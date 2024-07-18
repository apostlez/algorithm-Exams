
def solve():
  n, l, f = map(int, input().split(" "))
  w = input().split(" ")
  counter = {}
  max = 0
  for wn in w:
    suffix = wn[l-f:l]
    if counter.get(suffix) == None:
      counter[suffix] = 1
    else:
      counter[suffix] = counter[suffix] + 1
#    if counter[suffix] > max:
#      max = counter[suffix]
  for key, value in counter.items():
    max = max + value // 2
  print(max)



t = int(input())

for _ in range(t):
  solve()
