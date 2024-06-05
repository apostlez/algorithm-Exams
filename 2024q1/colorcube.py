
t = int(input())

for _ in range(t):
    n = list(map(int, input().split(" ")))

    translated = [[] for _ in range(3)]
    colorsum = [[0 for _ in range(3)] for _ in range(3)]
    rgb = ['R', 'G', 'B']

    for xyz in range(3):
        s = input()
        # translate RRRGGG => [R, 3], [G, 3]
        #findByGroup()
        # or find replace case?
        count = 1
        tr = translated[xyz]
        cr = colorsum[xyz]
        for i in range(1, n[xyz]+1):
            prev = i-1
            prevColor = s[prev]
            if prevColor == s[i]:
                count += 1
            else:
                rgbIndex = rgb.index(prevColor)
                tr.append([rgbIndex, count])
                cr[rgbIndex] += count - 1
                count = 1
        rgbLastIndex = rgb.index(s[n[xyz]])
        tr.append([rgbLastIndex, count])
        cr[rgbLastIndex] += count - 1

    longest = [[0 for _ in range(3)] for _ in range(3)]

    #print(translated)
    for xyz in range(3):
        data = translated[xyz]
        if len(data) == 1:
            longest[xyz][data[0][0]] = data[0][1]
        elif len(data) >= 2:
            for i in range(len(data)):
                color, value = data[i]
                # find longest R
                longest[xyz][color] = max(longest[xyz][color], value + 1)
#            if colorsum[xyz][0] > 0:
#                longest[xyz][0] = max(longest[xyz][0], colorsum[xyz][0] + 2)
#            if colorsum[xyz][1] > 0:
#                longest[xyz][1] = max(longest[xyz][1], colorsum[xyz][1] + 2)
#            if colorsum[xyz][2] > 0:
#                longest[xyz][2] = max(longest[xyz][2], colorsum[xyz][2] + 2)
        if len(data) >= 3:
            found = False
            for i in range(len(data) - 2):
                color, value = data[i]
                valuei = data[i+1][1]
                colorpp = data[i+2][0]
                valuepp = data[i+2][1]
                # find longest R
                if color == colorpp and valuei == 1:
                    found = True
                    newm = value + valuei + valuepp
                    #newm = max(longest[xyz][color], value + valuei + valuepp)
                    # sum rest
                    tmp = colorsum[xyz][color] - value - valuepp + 2
                    longest[xyz][color] = max(longest[xyz][color], newm + tmp)
            if found == False:
                # sum rest
                if colorsum[xyz][0] > 0:
                    longest[xyz][0] = max(longest[xyz][0], colorsum[xyz][0] + 2)
                if colorsum[xyz][1] > 0:
                    longest[xyz][1] = max(longest[xyz][1], colorsum[xyz][1] + 2)
                if colorsum[xyz][2] > 0:
                    longest[xyz][2] = max(longest[xyz][2], colorsum[xyz][2] + 2)
            #print(colorsum)
    print(longest)

    nm = 0
    arr = [0, 1, 2]
    for i in arr:
        arr2 = list(set(arr) - {i})
        for j in arr2:
            arr3 = list(set(arr2) - {j})
            if longest[0][i] != 0 and longest[1][j] != 0 and longest[2][arr3[0]] != 0:
                nm = max(nm, (longest[0][i]-1) * (longest[1][j]-1) * (longest[2][arr3[0]]-1))
    print(nm)

