
def prio(c):
    return ord(c) - 96 if c.islower() else ord(c) - 64 +26

def part1(file):
    value = 0
    with open(file) as f:
        for line in f.readlines():
            leng = len(line)
            pocket = [line[0:leng//2] , line[leng//2:].strip()]
            for letter in pocket[0]:
                if letter in pocket[1]:
                    val = prio(letter) 
                    value += val
                    break
    return value

def part2(file):
    value = 0
    i = 0
    vals = []
    with open(file) as f:
        for line in f.readlines():
            vals.append(line)
            i +=1
            if i == 3:
                for letter in vals[0]:
                    if letter in vals[1] and letter in vals[2]:
                        value += prio(letter)
                        break
                vals = []
                i = 0
    return value


if __name__ == '__main__':
    val = part1('day_3_sample.txt')
    assert (val == 157)
    val = part1('day_3_input.txt')
    print(val)
    val = part2('day_3_sample.txt')
    assert(val == 70)
    val = part2('day_3_input.txt')
    print(val)
