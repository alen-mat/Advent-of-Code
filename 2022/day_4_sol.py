def contains(sections: tuple[tuple[int,int],tuple[int,int]]) -> bool:
    sec_1_bounds = sections[0]
    sec_2_bounds = sections[1]
    sec_2_in_1 = sec_1_bounds[0] <= sec_2_bounds[0]<=sec_1_bounds[1] and sec_1_bounds[0] <= sec_2_bounds[1] <= sec_1_bounds[1]
    sec_1_in_2 = sec_2_bounds[0] <= sec_1_bounds[0]<=sec_2_bounds[1] and sec_2_bounds[0] <= sec_1_bounds[1] <= sec_2_bounds[1]
    return sec_1_in_2 or sec_2_in_1  

def overlap(sections: tuple[tuple[int,int],tuple[int,int]]) -> bool:
    sec_1_bounds = sections[0]
    sec_2_bounds = sections[1] 
    return sec_1_bounds[0] <= sec_2_bounds[0]<=sec_1_bounds[1] or sec_2_bounds[0] <= sec_1_bounds[0]<=sec_2_bounds[1]

def main(file: str ) -> list[int]:
    value = [0,0]
    with open(file) as f:
        for line in f.readlines():
            sections = line.split(',')
            a , b = sections[0].split('-')
            x , y = sections[1].split('-')
            sections = ((int(a),int(b)),(int(x),int(y)))
            if contains(sections):
                value[0] += 1
            if overlap(sections):

                print(sections)
                value[1] += 1
    return value

if __name__ == '__main__':
    v = main('day_4_sample.txt')
    print(v)
    assert(v[0] == 2 and v[1] == 4)
    print("----------")
    v = main('day_4_input.txt')
    print(v)
