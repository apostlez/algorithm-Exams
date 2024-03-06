#  0   1   1   3   3   3
# (1) (2) (3) (4) (5) (6)
#  1 - 2
#    - 3 - 4
#        - 5
#        - 6

#  1(100) - 2(8)
#         - 3(5) - 4(-20) - 7(10)
#                - 5(-30) - 8(20)
#                - 6(15)  - 9(-10)

def calcMax(p, child_list, v, root, scoreIn, scoreOut):
    scoreIn[root] = v[root] # 1 / 3
    scoreOut[root] = 0 # not 1 / 3 -> least 1 of child have to add

    hasChild = False
    added = False
    largest_change = 0
    scoreOutTmp = [0] * len(child_list[root])
    tmpIndex = 0
    changeMaxDiff = False
    maxDiff = 0
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
        #if (scoreIn[child] > 0 and scoreIn[child] >= scoreOut[child]) or (scoreIn[child] < 0 and scoreIn[child] <= scoreOut[child]):
#  1(100) - 2(8)
#         - 3(5) - 4(-20) - 7(10) : 4 in -20 out 10
#                - 5(-30) - 8(20) : 5 in -30 out 20
#                - 6(15)  - 9(20) : 6 in 15  out 20
        # max 3 : out(3) =            out(4) + out(5) +  in(6) =     10 + 20 + 15 = 45
        #          in(3) = value(3) + out(4) + out(5) + out(6) = 5 + 10 + 20 + 20 = 55
        diff = scoreIn[child] - scoreOut[child]
        if diff > 0:
            scoreOut[root] += scoreIn[child]
            added = True # least 1 added
        # if not, adding scoreIn[child] is bad(descrease score), then hold to add
        else:
            # case of minus: -5 : -4 : -3 : -2 : -1
            # backup changes
            scoreOut[root] += scoreOut[child]
            tmpIndex += 1
            if not changeMaxDiff or diff > maxDiff:
                maxDiff = diff
                changeMaxDiff = True


    if hasChild and not added:
        # add change
        #scoreOut[root] += sum(scoreOutTmp) - min(scoreOutTmp)
        scoreOut[root] += maxDiff 
        #print("[debug]", scoreOut)
        #print("[Debug]", scoreOutTmp, sum(scoreOutTmp), min(scoreOutTmp))

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
