# 사탕골고루먹기
# data = [[사탕 순서, 사탕 갯수] ...]
# data_갯수order = [[사탕 순서, 사탕 갯수] ...]
# result = []
# if (사탕 갯수 합) - (가장 많은 사탕) < (가장 많은 사탕) - 1 then:
#     IMPOSSIBLE
# while (사탕갯수합) > 0:
#     if (사탕 갯수 합) - (가장 많은 사탕) < (가장 많은 사탕) then:
#         result.add(가장 많은 사탕 순서)
#         (가장많은사탕갯수)--
#         re-order
#     else:
#         result.add(가장 앞의 사탕 순서)
#         (가장앞의 사탕 갯수)--
#     (사탕갯수합)--

def find_max_and_index(arr):
    if not arr:  # 배열이 비어있는지 확인
        return None, None

    max_value = arr[0]
    max_index = 0

    for i in range(1, len(arr)):
        if arr[i] > max_value:
            max_value = arr[i]
            max_index = i

    return max_value, max_index

def find_first_non_zero_index(arr):
    for i, value in enumerate(arr):
        if value != 0:
            return i
    return None  # 0이 아닌 값이 없는 경우

t = int(input())

for _ in range(t):
  n = int(input()) # 사탕의 종류
  v = list(map(int, input().split(" "))) # 사탕 종류에 따른 갯수
  data = [] * n

  max_value, max_index = find_max_and_index(v)

#  data_ordered = [] * n
#  for i in range(n):
#    data.append([i, v[i]])
#  data_ordered = sorted(data, key=lambda candy_num: candy_num[1])
  result = []
  if sum(v) < max_value * 2 -1:
    print("IMPOSSIBLE")
    continue
  #tmp_sum = sum(list(map(lambda candy_num: candy_num[1], data_ordered)))
  while (sum(v) > 0):
    if sum(v) < max_value * 2:
      v[max_index] = v[max_index] - 1 
      result.append(max_index + 1)
      max_value, max_index = find_max_and_index(v)
    else:
       first_index = find_first_non_zero_index(v)
       result.append(first_index + 1)
       v[first_index] = v[first_index] - 1
  print(result)

  print(sum(index * value for index, value in enumerate(result)) % 987654323)
