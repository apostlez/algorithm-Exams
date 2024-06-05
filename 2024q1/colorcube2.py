
t = int(input())

def checkChanged(data, ccs, i, arr):
    if len(data) > 1:
        color, value = data[i]
        # current
        if i < len(data) - 2:
            # check nextover
            nextcolor, nextvalue = data[i+1]
            nextovercolor, nextovervalue = data[i+2]
            if nextvalue == 1 and color == nextovercolor:
                ccs[color] += 2
                arr.append(ccs)
                return
        if i == 0:
            # expand right
            nextcolor, nextvalue = data[i+1]
            ccs[color] += 1
            if nextvalue > 1:
                ccs[nextcolor] -= 1
            arr.append(ccs)
        elif i == len(data) - 1:
            # expand left
            prevcolor, prevvalue = data[i-1]
            if i >= 2 and prevvalue == 1 and color == data[i-2][1]:
                return
            ccs[color] += 1
            if prevvalue > 1:
                ccs[prevcolor] -= 1
            arr.append(ccs)
        else:
            nextcolor, nextvalue = data[i+1]
            prevcolor, prevvalue = data[i-1]
            if i >= 2 and prevvalue == 1 and color == data[i-2][1]:
                return
            if nextvalue == 1 or prevvalue == 1:
                ccs[color] += 1
                arr.append(ccs)
            elif nextcolor == prevcolor:
                ccs[color] += 1
                ccs[nextcolor] -= 1
                arr.append(ccs)
            else:
                ccs[color] += 1
                ccs[nextcolor] -= 1
                arr.append(ccs)
                ccs[nextcolor] += 1
                ccs[prevcolor] -= 1
                arr.append(ccs)


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

    #print(colorsum)
    result = 0

    x_arr = []
    y_arr = []
    z_arr = []
    x_arr.append(colorsum[0])
    y_arr.append(colorsum[1])
    z_arr.append(colorsum[2])
    # for x
    data_x = translated[0]
    for i in range(len(data_x)):
        xccs = [colorsum[0][0], colorsum[0][1], colorsum[0][2]]
        checkChanged(translated[0], xccs, i, x_arr)
    # process y
    data_y = translated[1]
    for j in range(len(data_y)):
        yccs = [colorsum[1][0], colorsum[1][1], colorsum[1][2]]
        checkChanged(translated[1], yccs, j, y_arr)
    # process z
    data_z = translated[2]
    for k in range(len(data_z)):
        zccs = [colorsum[2][0], colorsum[2][1], colorsum[2][2]]
        checkChanged(translated[2], zccs, k, z_arr)

    for xccs in x_arr:
        for yccs in y_arr:
            for zccs in z_arr:
                #print(xccs, yccs, zccs)
                tmp = 0
                tmp += xccs[0] * yccs[1] * zccs[2] # R * G * B
                tmp += xccs[0] * yccs[2] * zccs[1] # R * B * G
                tmp += xccs[1] * yccs[2] * zccs[0] # G * B * R
                tmp += xccs[1] * yccs[0] * zccs[2] # G * R * B
                tmp += xccs[2] * yccs[0] * zccs[1] # B * R * G
                tmp += xccs[2] * yccs[1] * zccs[0] # B * G * R
                result = max(result, tmp)
                #print("tmp", tmp)

    print(result)
