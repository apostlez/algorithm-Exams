# 25 24
# ........................
# .+---+---+---+..........
# .|x.x|..x|...|..........
# .|x.x|.x.|.x.|..........
# .|x.x|x..|...|..........
# .+---+---+---+---+---+..
# .........|...|x.x|x.x|..
# .........|x.x|...|.x.|..
# .........|...|x.x|x.x|..
# .........+---+---+---+..
# ........................
# .....+---+..............
# .....|x.x|..............
# .....|.x.|..............
# .....|x.x|..............
# .+---+---+---+---+......
# .|..x|...|x.x|x.x|......
# .|.x.|.x.|...|x.x|......
# .|x..|...|x.x|x.x|......
# .+---+---+---+---+......
# .....|...|..............
# .....|x.x|..............
# .....|...|..............
# .....+---+..............
# ........................

from enum import Enum

class dice_face:
    def __init__(self, loc, pos, pattern) -> None:
        pass
        self.loc = loc
        self.pos = pos
        self.pattern = pattern
        self.left = None
        self.up = None
        self.down = None
        self.right = None
        self.across = None
    def setLeft(self, item):
        self.left = item
        item.right = self
        item.up = self.up
        self.up.setLeft(item)
        item.down = self.down
        self.down.setLeft(item)
        item.left = self.across
        self.across.setLeft(item)
        item.across = self.right
        self.right.setAcross(item)
        return item
    def setRight(self, item):
        self.right = item
        item.right = self.across
        item.up = self.up
        item.down = self.down
        item.left = self
        item.across = self.left
        return item
    def setUp(self, item):
        self.up = item
        item.right = self.right
        item.up = self.across
        item.down = self
        item.left = self.left
        item.across = self.down
        return item
    def setDown(self, item):
        self.down = item
        item.right = self.right
        item.up = self
        item.down = self.across
        item.left = self.left
        item.across = self.up
        return item
    # 방향이 안맞는다, 위아래로 회전이냐 좌우로 회전이냐에 따라 좌우, 상하가 바뀌어 버림
    def setAcross(self, item):
        self.across = item
        return item

class direction(Enum):
    LEFT = [0, -1]
    RIGHT = [0, 1]
    UP = [-1, 0]
    DOWN = [1, 0]
    ACROSS = [2, 0]

# dice = graph
# face = node
class dice:
    def __init__(self, x = 0, y = 0) -> None:
        self.faces = [] * 6
        self.faces_loc = [] * 6
        self.faces_pos = [] * 6
        self.x = x
        self.y = y
    def __delattr__(self, __name: str) -> None:
        pass
    def addFace(self, loc_x, loc_y, x, y, prev_x, prev_y):
        #self.faces_loc.append([x, y])
        #self.faces.append([blueprint[x+1][y+1:y+4], blueprint[x+2][y+1:y+4], blueprint[x+3][y+1:y+4]])
        #self.faces_pos.append([loc_x, loc_y])
        self.faces_loc.append([x, y])
        newFace = dice_face([x, y], [loc_x, loc_y], [blueprint[x+1][y+1:y+4], blueprint[x+2][y+1:y+4], blueprint[x+3][y+1:y+4]])
        oldFace = None
        for face in self.faces:
            if face.pos == [prev_x, prev_y]:
                oldFace = face
        if prev_x == loc_x and prev_y-1 == loc_y:
            oldFace.setLeft(newFace)
        if prev_x == loc_x and prev_y+1 == loc_y:
            oldFace.setRight(newFace)
        if prev_x-1 == loc_x and prev_y == loc_y:
            oldFace.setUp(newFace)
        if prev_x+1 == loc_x and prev_y == loc_y:
            oldFace.setDown(newFace)
        self.faces.append(newFace)

    def isAdded(self, x, y):
        if [x, y] in self.faces_loc:
            return True
        return False
#    def isAdded(self, x, y):
#        for face in self.faces:
#            if face.loc == [x, y]:
#                return True
#        return False
    def print(self):
#        for i in range(len(self.faces_loc)):
#            print(self.faces_loc[i], self.faces_pos[i], self.faces[i])
        for face in self.faces:
            print(face.loc, face.pos, face.pattern)
    def compare(self, d):
        for fi in range(len(self.faces)):
            for direct in range(4):
                if self.faces[fi].pattern == d.faces[0].pattern:
                    if self.faces[fi].left.pattern == d.faces[0].left.pattern:
                        if self.faces[fi].right.pattern == d.faces[0].right.pattern:
                            if self.faces[fi].up.pattern == d.faces[0].up.pattern:
                                if self.faces[fi].down.pattern == d.faces[0].down.pattern:
                                    if self.faces[fi].across.pattern == d.faces[0].across.pattern:
                                        return True
                # compare center
                # compare left
                # compare right
                # compare up
                # compare down
                # compare across
                # turn direct
            # turn face
        return False
    def getFace(self, start_x, start_y, stop_x, stop_y):
        rotate = 0
        for xi in range(stop_x):
            for yi in range(stop_y):
              if [xi, yi] in self.faces_pos:
                  rotate += 90 
        return self.faces[i]
    def getFaceLeft(self, start_x, start_y):
        rotate = 0
        for xi in range(start_x):
            for yi in range(start_y):
              if [xi, yi] in self.faces_pos:
                  rotate += 90 
        return self.faces[i]


# dice graph
#           ┌------------┐
#     ┌--- (4) ---┐      |
#     |     |     |      |
# ┌--(1) - (0) - (3) --┐ |
# |   |     |     |    | |
# |   └--- (2) ---┘    | |
# |         |          | |
# └------- (5) --------┘ |
#           └------------┘

def checkDown(x, y):
    if x+9 < h and blueprint[x+5][y] == '|' and blueprint[x+8][y+1] == '-':
        return [x+4, y]
    return None

def checkLeft(x, y):
    if y > 0 and blueprint[x][y-1] == '-' and blueprint[x+1][y-4] == '|':
        return [x,y-4]
    return None

def checkRight(x, y):
    if y+5 < w and blueprint[x][y+5] == '-' and  blueprint[x+1][y+8] == '|':
        return [x,y+4]
    return None

# input: '.', 'x', '-', '|', '+'
h, w = map(int, input().split(" "))
blueprint = [] * h
dices = []
dicesfaces = []
for i in range(h):
    line = input()
    blueprint.append(line)
dices_index = []
for i in range(h):
    # 1. 다음 + 를 찾아서 인덱스 저장

    loc = 0
    while True:
        loc = blueprint[i].find('+', loc)
        if loc == -1:
            break
        found = False
        for diceItem in dices:
            if diceItem.isAdded(i, loc) == True:
                found = True
        if found == True:
            break
#        dices_index.append(loc)
        # check it is first
        if (i < 4 and loc < 4) or (blueprint[i][loc-1] != '-' and blueprint[i-1][loc] != '|'):
            newDice = dice(i, loc)
            #newDice.addFace(0, i, loc)
            dices.append(newDice)
        else:
            break
        # 그래프 형식으로 탐색하며 주사위 면을 추가
        # down -> down -> down -> X
        trav = []
        trav.append([i, loc, 0, 0])
        trav.append([i, loc, 0, 0])
        index = 0
        while len(trav) > index:
            prev = trav[index]
            index += 1
            togo = trav[index]
            down = checkDown(togo[0], togo[1])
            if down != None and [down[0], down[1], togo[2]+1, togo[3]] not in trav:
                trav.append([togo[0], togo[1], togo[2], togo[3]])
                trav.append([down[0], down[1], togo[2]+1, togo[3]])
            left = checkLeft(togo[0], togo[1])
            if left != None and [left[0], left[1], togo[2], togo[3]-1] not in trav:
                trav.append([togo[0], togo[1], togo[2], togo[3]])
                trav.append([left[0], left[1], togo[2], togo[3]-1])
            right = checkRight(togo[0], togo[1])
            if right != None and [right[0], right[1], togo[2], togo[3]+1] not in trav:
                trav.append([togo[0], togo[1], togo[2], togo[3]])
                trav.append([right[0], right[1], togo[2], togo[3]+1])
            newDice.addFace(togo[2], togo[3], togo[0], togo[1], prev[2], prev[3])
            index += 1
        loc += 1
print("------------------")
for dd in dices:
    dd.print()
    print("------------------")

result = []
for di in range(len(dices)-1):
    count = 1
    for dj in range(1, len(dices)):
        if dices[di].compare(dices[dj]) == True:
            count += 1
            print("found", di, dj)
    result.append(count)
print(count)

        # a space of dice has 512 case of dot. 2^9

    # 3. 다음 + 찾아서 주사위 구분
    # 4. 주사위 패턴 11개에 따라 가능한 좌표 확인


# dice: 
#        north
# west - center - east
#        south
#        across
