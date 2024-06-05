#  0   1   1   3   3   3
# (1) (2) (3) (4) (5) (6)
#  1 - 2
#    - 3 - 4
#        - 5
#        - 6

#  1(100) - 2(8)
#         - 3(5) - 4(-20)
#                - 5(-30)
#                - 6(15) // - 인 경우?

#  1(100) - 2(8)
#         - 3(5) - 4(-20)
#                - 5(-30)
#                - 6(-15) // - 인 경우?
# 93

def calcMax(p, child_list, v, root, scoreIn, scoreOut):
    scoreIn[root] = v[root] # 1 / 3
    scoreOut[root] = 0 # not 1 / 3 -> least 1 of child have to add

    hasChild = False
    added = False
    largest_change = 0
    scoreOutTmp = [0] * len(child_list[root])
    tmpIndex = 0
    # find child
    for i in child_list[root]:
        hasChild = True
        child = i
        # 2, 3 / 4 5 6
        calcMax(p, child_list, v, child, scoreIn, scoreOut)
        # need to check to add c is good or bad
        # if root is in, child are out
        scoreIn[root] += scoreOut[child]

        # if root is out, least 1 of child in
        # if adding scoreIn[child] is good(increase score), then add to scoreOut[root]
        #if scoreIn[child] >= scoreOut[child]:
        #if (scoreIn[child] > 0 and scoreIn[child] >= scoreOut[child]) or (scoreIn[child] < 0 and scoreIn[child] <= scoreOut[child]):
        #if scoreIn[child] >= 0:
        if scoreIn[child] >= scoreOut[child]:
            scoreOut[root] += scoreIn[child]
            added = True # least 1 added
        # if not, adding scoreIn[child] is bad(descrease score), then hold to add
        else:
            # case of minus: -4 : -3 + -1 vs -2
            # backup changes
            scoreOutTmp[tmpIndex] = scoreIn[child]
            tmpIndex += 1

    if hasChild and not added:
        # add change
        #scoreOut[root] += sum(scoreOutTmp) - min(scoreOutTmp)
        scoreOut[root] += max(scoreOutTmp) # score 가 음수인 경우 오답
        #print("[debug]", scoreOut)
        #print("[Debug]", scoreOutTmp, sum(scoreOutTmp), min(scoreOutTmp))
    else:
        scoreOut[root] += sum(scoreOutTmp)

t = int(input())
for i in range(t):
    n = int(input())
    v = list(map(int, input().split()))
    p = list(map(int, input().split()))

    scoreIn = [0] * n
    scoreOut = [0] * n
    child_list = [[] for i in range(n)]

    # make tree and find root
    for i in range(n):
        if p[i] == 0:
            root = i
        else:
            parent_index = p[i]-1
            child_list[parent_index].append(i)

    calcMax(p, child_list, v, root, scoreIn, scoreOut)
    
    print(max(scoreIn[root], scoreOut[root]))
