package codejam2018_1st;

import java.util.*;

public class Problem_5_editorial {
    public static void main(String[] args) {
    }
}
/*


Subtask1, 2
앞의 2개의 Subtask에는 중복이 존재하지 않는다.

그렇기 때문에 숫자의 크기 순서대로 지우고, 다음 숫자로 이동한 다음에 지워나가는 과정을 반복하기만 하면 된다.



이 때 쉽게 계산하기 위해서 i보다 작은 숫자가 모두 지워졌을 때의 인덱스를 미리 계산해준다. 이 때 숫자 i의 보정된 인덱스를 adj(i)라고 하고, 최초 상태의 인덱스는 idx(i)라고 하자.

예를 들어 1 5 3 2 4 라고 수열이 있다고 했을 때

adj(1) = 0이고 (index는 0부터 시작한다고 생각하자)

앞에서 1이 지웠기 때문에 adj(2) = 2이다.

같은 방식으로 adj(3) = 1, adj(4) = 1, adj(5) = 0이다.



그렇다면 n - 1번째 숫자을 지운 상태에서 n번째 숫자를 지우는데 드는 비용은 얼마일까?

adj(n) - adj(n-1)와 adj(n) - (adj(n - 1) - 1) 의 절대값 중 작은 값에서 1을 더한 값이다. 왜냐면 블럭을 제거하고 오른쪽으로 갈 경우 기계의 위치는 adj(n - 1)이고, 왼쪽으로 갈 경우 adj(n-1) - 1이기 때문이다.

1을 더하는 이유는 숫자를 제거하는데에도 비용이 필요하기 때문이다.



그렇다면 이 값을 cost(n)이라고 하였을 때, 

cost(n) = min(abs(adj(n) - adj(n-1)), abs(adj(n) - (adj(n-1) - 1))) + 1   ( n > 1)

cost(1) = adj(1) + 1 => 이동하는데 adj(1), 제거하는데 1이 비용 발생



그럼 n개의 숫자가 있다고 했을 때,

totalCost = cost(1) + cost(2) + ... + cost(n - 1) + cost(n) 이다.



그렇다면 adj(n)을 어떻게 구할 수 있을까?

처음으로 생각할 수 있는 방법은 배열에서 가장 작은 수를 찾아서 그 수의 인덱스를 구하고, 배열에서 그 숫자를 지운다.

그 다음에 다시 앞의 과정을 반복하며, 배열이 빌때까지 반복하면 된다.



하지만 이는 비효율적이다. O(n^2)의 복잡도를 가지고, 배열을 계속 쓰고 지우는 것도 시간 소모가 많다.



그렇다면 어떻게 쉽게 해결할 수 있을까?

우선 sub라는 map을 하나 선언한다.

그리고 좌측부터 숫자를 탐색한다.

이때 숫자가 i이면, sub[1]부터 sub[i-1]까지의 합을 구한 다음 idx(i)에서 빼주면 adj(i)이다.

그 다음에, sub[i]++ 해준다.

즉 adj(i) = idx(1) - (sub[1] + sub[2] + ... sub[i - 2] + sub[i-1]) 이다.

왜냐면 sub의 부분합은 idx(i) 이전에 나온 i 보다 작은 숫자의 총 갯수이기 때문이다.



이 때 Fenwick tree 같은 자료 구조를 사용하면 그냥 하는 것보다 훨씬 빠르게 구현할 수 있다.(O(n * log h))



이제 adj(i)를 구할 수 있기 때문에 cost는 쉽게 구할 수 있다.


class Fenwick:
    def __init__(self):
        self.n = 100001
        self.tree = [0] * self.n
 
    def sum(self, idx):
        ans = 0
        while idx > 0:
            ans += self.tree[idx]
            idx -= (idx & -idx)
 
        return ans
 
    def update(self, idx, num):
        while idx <= self.n:
            self.tree[idx] += num
            idx += (idx & -idx)
 
 
def indices(l):
    tree = Fenwick()
    from collections import defaultdict
    adj = defaultdict(list)
    for i, v in enumerate(l):
        adj[v].append(i - tree.sum(v - 1))
        tree.update(v, 1)
    result = []
    for i in sorted(adj.keys()):
        result.append(adj[i])
    return result
 
 
def cost(i, adj):
    if i == 0:
        return adj[0][0] + 1
    else:
        return min(
            abs(adj[i][0] - adj[i-1][0]),
            abs(adj[i][0] - (adj[i-1][0] - 1))
        ) + 1
 
def total_cost(adj):
    return sum([cost(i, adj) for i in range(len(adj))])
 
 
def solve(hs):
    adj = indices(hs)
    return total_cost(adj)


Subtask3
Subtask 3의 경우는 문제가 조금 더 복잡해진다.

중복일 때는 위의 전략을 쓸 수 없기 때문이다.



여기서 가장 중요한 아이디어는 n번째 숫자를 지울 때 가장 좌측 또는 가장 우측에서 끝나는 것만이 최적해가 될 수 있다는 점이다.

단순히 생각해봐도 알 수 있는데 기계 앞에 지워야 되는 숫자가 있는 경우, 지우는 것이 무조건 이득이다.

왜냐면 지우고 좌측, 우측 중에 원하는 곳으로 이동할 수 있기 때문이다.



그럼 어떻게 이동하는게 좋을까?

n-1번째 숫자를 다 제거한 경우, 기계는 n-1번째 숫자 중 가장 좌측 또는 가장 우측에 위치하고 있다.

이 때 기계의 위치 기준으로 n번째 숫자들이 좌측에 몇개, 우측에 몇개 인지 세어본다.



그럼 케이스가 3가지로 나뉜다.

좌측에만 있는 경우 => 항상 가장 왼쪽으로만 이동하는게 이익이다.

우측에만 있는 경우 => 항상 가장 오른쪽으로만 이동하는게 이익이다.

좌우측 둘다 있는 경우 => 우측으로 갔다가 좌측으로 가는경우, 좌측으로 갔다가 우측으로 가는 경우 중 비용이 적은 것을 선택한다.



이런 식으로 좌우측으로만 이동하면서 누적 cost가 적은 값을 찾으면 최적해가 된다.

이 때 빠른 검색을 위해서 이진검색을 하면 더욱 빠르게 결과를 얻을 수 있다.




from bisect import bisect_left, bisect_right, bisect

class Fenwick:
    def __init__(self):
        self.n = 100001
        self.tree = [0] * self.n
 
    def sum(self, idx):
        ans = 0
        while idx > 0:
            ans += self.tree[idx]
            idx -= (idx & -idx)
 
        return ans
 
    def update(self, idx, num):
        while idx <= self.n:
            self.tree[idx] += num
            idx += (idx & -idx)
 
 
def indices(l):
    tree = Fenwick()
    from collections import defaultdict
    adj = defaultdict(list)
    for i, v in enumerate(l):
        adj[v].append(i - tree.sum(v - 1))
        tree.update(v, 1)
    result = []
    for i in sorted(adj.keys()):
        result.append(adj[i])
    return result
 
 
 
 
 
def cost(prev, current):
    result = {}
 
    def acc(pos, cost):
        if pos not in result:
            result[pos] = cost
        else:
            result[pos] = min(result[pos], cost)
 
    for (prev_pos, prev_cost) in prev.items():
        for bi in [bisect(current, prev_pos), bisect_left(current, prev_pos)]:
            left, right = current[:bi], current[bi:]
            leftmost, rightmost = current[0], current[-1]
            rightmost_to_next = rightmost - len(current) + 1
            if len(left) == 0:
                new_cost = prev_cost + (rightmost - prev_pos) + 1
                acc(rightmost_to_next, new_cost)
                acc(rightmost_to_next - 1, new_cost)
            elif len(right) == 0:
                new_cost = prev_cost + (prev_pos - leftmost) + 1
                acc(leftmost, new_cost)
                acc(leftmost - 1, new_cost)
            else:
                # right -> left
                to_right = (rightmost + 1 - prev_pos)
                to_left = (rightmost + 1 - len(right) - leftmost)
                new_cost = prev_cost + to_right + to_left
                acc(leftmost, new_cost)
                acc(leftmost - 1, new_cost)
 
                # left -> right
                to_left = (prev_pos + 1 - leftmost)
                to_right = (rightmost + 1 - len(left) - leftmost)
                new_cost = prev_cost + to_left + to_right
                acc(rightmost_to_next, new_cost)
                acc(rightmost_to_next - 1, new_cost)
    return result
 
 
def total_cost(adj):
    status = {0: 0}
    for current in adj:
        status = cost(status, current)
 
    return min(status.values())
 
 
def solve(hs):
    adj = indices(hs)
    return total_cost(adj)
*/