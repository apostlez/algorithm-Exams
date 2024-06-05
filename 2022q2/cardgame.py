

t = int(input())

for i in range(t):
  n = int(input())
  b = input()
  a = input()

  rev = b[::-1]
  min_b = min(int(b), int(rev))
  sorted_arr = sorted(a)
  sorted_a = ''.join(sorted_arr)
  num_a = int(sorted_a)
  if min_b <= num_a:
    sorted_arr.reverse()
    sorted_arr.pop()
    print(''.join(sorted_arr))
  else:
    # bubble
    for j in range(n-1):
      tmp = sorted_arr[j]
      sorted_arr[j] = sorted_arr[j+1]
      sorted_arr[j+1] = tmp
      if min_b <= int(''.join(sorted_arr)):
        tmp = sorted_arr[j]
        sorted_arr[j] = sorted_arr[j+1]
        sorted_arr[j+1] = tmp
        continue
    print(''.join(sorted_arr))


