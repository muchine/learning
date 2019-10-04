import math


def validate(numbers, length):
    # print numbers
    expected = "".join(str(x) for x in range(1, length + 1))
    numbers.sort()
    string = "".join(str(x) for x in numbers)

    return expected == string


def perform(sudoku):
    data = sudoku.data
    length = len(data)
    print data

    for x in range(0, length):
        if not validate(data[x][:], length):
            return False

    for y in range(0, len(data)):
        column = [data[x][y] for x in range(0, len(data))]
        if not validate(column, length):
            return False

    rows = data[0:3]
    grid = [r[0:3] for r in rows]
    # print grid

    n = int(math.sqrt(len(data)))
    for x in range(0, len(data), n):
        rows = data[x:x+n]
        for y in range(0, len(data), n):
            grid = [r[y:y + n] for r in rows]
            # print "x: " + str(x) + ", y: " + str(y)
            # print "subgrid: " + str(grid)
            flattened = [j for i in grid for j in i]
            # print flattened
            if not validate(flattened, length):
                return False

    return True


class Sudoku(object):

    def __init__(self, data):
        self.data = data

    def is_valid(self):
        return perform(self.data)


# Valid Sudoku
goodSudoku1 = Sudoku([
    [7, 8, 4, 1, 5, 9, 3, 2, 6],
    [5, 3, 9, 6, 7, 2, 8, 4, 1],
    [6, 1, 2, 4, 3, 8, 7, 5, 9],

    [9, 2, 8, 7, 1, 5, 4, 6, 3],
    [3, 5, 7, 8, 4, 6, 1, 9, 2],
    [4, 6, 1, 9, 2, 3, 5, 8, 7],

    [8, 7, 6, 3, 9, 4, 2, 1, 5],
    [2, 4, 3, 5, 6, 1, 9, 7, 8],
    [1, 9, 5, 2, 8, 7, 6, 3, 4]
])

goodSudoku2 = Sudoku([
    [1, 4, 2, 3],
    [3, 2, 4, 1],

    [4, 1, 3, 2],
    [2, 3, 1, 4]
])

# Invalid Sudoku
badSudoku1 = Sudoku([
    [0, 2, 3, 4, 5, 6, 7, 8, 9],
    [1, 2, 3, 4, 5, 6, 7, 8, 9],
    [1, 2, 3, 4, 5, 6, 7, 8, 9],

    [1, 2, 3, 4, 5, 6, 7, 8, 9],
    [1, 2, 3, 4, 5, 6, 7, 8, 9],
    [1, 2, 3, 4, 5, 6, 7, 8, 9],

    [1, 2, 3, 4, 5, 6, 7, 8, 9],
    [1, 2, 3, 4, 5, 6, 7, 8, 9],
    [1, 2, 3, 4, 5, 6, 7, 8, 9]
])

badSudoku2 = Sudoku([
    [1, 2, 3, 4, 5],
    [1, 2, 3, 4],
    [1, 2, 3, 4],
    [1]
])

print Sudoku(goodSudoku1).is_valid()
print Sudoku(goodSudoku2).is_valid()
print Sudoku(badSudoku1).is_valid()
print Sudoku(badSudoku2).is_valid()
