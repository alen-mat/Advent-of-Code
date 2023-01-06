
def main(input : str) -> tuple:
    length = len(input)
    part1 , part2 = None,None
    i , j = 0, 1
    while i < j and j < length:
        pos = input.find(input[j], i, j)
        if pos != -1:
            print(input[j],input[i:j])
            i = pos + 1
        if j-i == 3 and part1 == None:
            part1 = j+1
        if j - i == 13 and part2 ==None:
            part2 = j+1
        if part1 != None and part2 != None:
            break
        j += 1
    return (part1,part2)


if __name__ == '__main__':
    sample = [
        ('mjqjpqmgbljsphdztnvjfqwrcgsmlb',(7,19)),
        ('bvwbjplbgvbhsrlpgdmjqwftvncz',(5,23)),
        ('nppdvjthqldpwncqszvftbrmjlhg',(6,23)),
        ('nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg',(10,29)),
        ('zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw',(11,26))
    ]
    input = None
    with open('day_6_input.txt') as f:
        input = f.readline()
        print(input)

    for test,value in sample:
        v = main(test)
        assert v == value , f"Error in case << {test} >> \n  Expected : {str(value)} Got  : {str(v)}"
    print('=====',input)
    v  = main(input)
    print(f"Part 1 : {v}")

