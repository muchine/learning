def color_number(c):
    if c == 'R':
        return 0
    elif c == 'G':
        return 1
    elif c == 'B':
        return 2
    else:
        raise Exception('Invalid argument')


def lucas3(dig_n, len_n, k):
    dig_k = base3(k)
    # print dig_k
    len_k = len(dig_k)

    prod = 1
    for i in range(0, len_n):
        ni = dig_n[i]
        ki = dig_k[i] if i < len_k else 0
        prod = prod * bino_max(ni, ki)

    return prod % 3


def base3(n):
    if n == 0:
        return [0]
    nums = []
    while n:
        n, r = divmod(n, 3)
        nums.append(r)
    return nums


def bino_max(n, k):
    if n < k:
        return 0

    if n == 0 or n == 1:
        return 1
    if n == 2:
        return 2 if k == 1 else 1

    return 0


def triangle(row):
    colours = {
        'RR': 'R', 'GG': 'G', 'BB': 'B',
        'GB': 'R', 'BR': 'G', 'RG': 'B',
        'BG': 'R', 'RB': 'G', 'GR': 'B',
    }

    for lc in [59050, 6562, 2188, 730, 244, 82, 28, 10, 4, 2]:
        while len(row) >= lc:
            row = map(lambda i: colours[row[i] + row[i + lc - 1]], range(0, len(row) - lc + 1))
            print row

    return row[0]

    # colors = ['R', 'G', 'B']
    # sum_index = 0
    #
    # dig_n = base3(len(row) - 1)
    # len_n = len(dig_n)
    #
    # print len(row)
    # for i in range(0, len(row)):
    #     sum_index = sum_index + color_number(row[i]) * lucas3(dig_n, len_n, i)
    #
    # if len(row) % 2 == 0:
    #     sum_index = -sum_index
    #
    # return colors[sum_index % 3] if sum_index >= 0 else colors[((sum_index % 3) + 3) % 3]


import random

s = ""
for i in range(0, 100000):
    s += "RGB"[random.randint(0, 2)]


print triangle("BGBGGBBGRGGBRGGB")
print triangle(s)
