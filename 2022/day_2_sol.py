# pass in as numbers
# sissor - 
# papper - 
# stone  - 
def translate (sign):
    return {'A':0,'X':0,'B':1,'Y':1,'C':2,'Z':2}[sign]

def evaluvate(player_a,player_b):
    if ((player_a + 1) % 3) == player_b:
        return [player_a + 1, 6 + player_b + 1]
    elif player_a == ((player_b + 1)%3):
        return [6 + player_a + 1, player_b + 1]
    else:
        return [3 + (player_a + 1),3 + (player_b + 1)]

def main(file, part):
    g_a_score = 0
    g_b_score = 0
    with open(file) as f:
        for line in f.readlines():
            sign = line.split(' ')
            a_score , b_score = 0, 0
            if part == 1:
                a_score, b_score = evaluvate(translate(sign[0]),translate(sign[1].strip()))
            else :
                a_score, b_score =  plan(translate(sign[0]),translate(sign[1].strip()))
            g_a_score += a_score
            g_b_score += b_score

    return [g_a_score, g_b_score]

def plan(player_a, strat):
    if strat == 1:
        return [ 3 + player_a + 1] * 2
    elif strat == 0 : 
        return [6 + player_a + 1 , 3 if player_a == 0 else player_a ]
    else:
        return [player_a + 1 , 6 + (player_a + 1)%3 + 1  ]


if __name__ == '__main__':
    val = main('day_2_sample.txt',1)
    assert(val[1] == 15)
    val = main('day_2_input.txt',1)
    print(f"Part 1 :  {val[1]}")
    val = main('day_2_sample.txt',2)
    assert(val[1] == 12)
    val = main('day_2_input.txt',2)
    print(f"Part 2 :  {val[1]}")
    assert(val[1] == 13022)
