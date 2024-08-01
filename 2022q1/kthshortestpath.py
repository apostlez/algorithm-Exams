
# https://codejam.lge.com/problem/24436
from itertools import permutations
import math

def unique_permutations(elements):
  # permutations 함수를 사용하여 모든 순열을 생성합니다.
  all_permutations = permutations(elements)
  # set을 사용하여 중복된 순열을 제거합니다.
  unique_perms = set(all_permutations)
  return unique_perms

def sum_of_digit_differences(num_str1, num_str2):
  # 각 자리 수의 차이를 계산하고 합을 구합니다.
  total_difference = 0
  for digit1, digit2 in zip(num_str1, num_str2):
    total_difference += abs(int(digit1) - int(digit2))
  # 결과를 출력합니다.
  
  print("각 자리 수의 차이의 합:", total_difference)

# https://namu.wiki/w/%EA%B2%BD%EC%9A%B0%EC%9D%98%20%EC%88%98/%EA%B3%B5%EC%8B%9D#s-3.6
# 같은 것이 있는 순열 == 조합 공식으로 경우의 수 계산
def solve():
  l, k, x, y = map(int, input().split(" "))
  # 두 문자열의 길이를 맞추기 위해 짧은 쪽을 0으로 채웁니다.
  x = str(x).zfill(l)
  y = str(y).zfill(l)

  # calc each element
  element = []
  for digit1, digit2, lenth in zip(x, y, range(l-1, -1, -1)):
    #diff = abs(int(digit1) - int(digit2)) # 2, 2, 2
    diff = int(digit2) - int(digit1) # 2, -2, 2(20)
    for d in range(abs(diff)):
      if diff < 0:
        element.append(-1 * 10**(lenth)) # 1, 1, -1, -1, 10, 10
      elif diff > 0:
        element.append(10**(lenth)) # 1, 1, -1, -1, 10, 10
    #print (digit1, digit2, element)

  # unique_perms = unique_permutations(element) # time over for "4 100000000000000 0000 9999"

  # calc 같은것이 있는 순열(조합)
  a = 0
  b = 1
  for digit1, digit2, lenth in zip(x, y, range(l-1, -1, -1)):
    diff = abs(int(digit1) - int(digit2)) # 2, 2, 2
    a += diff
    if diff != 0:
      b *= math.factorial(diff)
  print (math.factorial(a)/b)
  allcases = math.factorial(a) / b
  print(int(allcases))
  # 값을 정렬하고 순서대로 조합 값을 찾고자 하는 k 번째 가 있는지 확인한다.

t = int(input())

for _ in range(t):
  solve()
