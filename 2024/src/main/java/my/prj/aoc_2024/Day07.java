package my.prj.aoc_2024;

import java.util.Arrays;

/**
 * Day07
 */
public class Day07 extends AbstractSolution{
    String testInput = String.join("\n"
        ,"190: 10 19"
        ,"3267: 81 40 27"
        ,"83: 17 5"
        ,"156: 15 6"
        ,"7290: 6 8 6 15"
        ,"161011: 16 10 13"
        ,"192: 17 8 14"
        ,"21037: 9 7 18 13"
        ,"292: 11 6 16 20"
    );

    String input;
    String[] inputLines;

    long partOne = 0;
    long partTwo = 0;

    private void partOne() {
        for (String line : inputLines) {
            String[] a = line.split(":");
            long result = Long.parseLong(a[0]);
            long[] operands = Arrays.stream(a[1].split(" ")).filter(r -> !r.equals("")).mapToLong(Long::parseLong)
                    .toArray();
            if (solveWithAM(1, operands, operands[0], result, false)) {
                partOne += result;
                partTwo += result;
            }
            else if (solveWithAM(1, operands, operands[0], result, true)) {
                partTwo += result;
            }
        }
    }

    boolean solveWithAM(int cIx, long[] operands, long result, long actualResult, boolean club) {
        if (cIx == operands.length) {
            if (result == actualResult)
                return true;
            return false;
        }
        return solveWithAM(cIx + 1, operands, result + operands[cIx], actualResult, club)
                || solveWithAM(cIx + 1, operands, result * operands[cIx], actualResult, club)
                || (club && solveWithAM(cIx + 1, operands, Long.parseLong(result+""+operands[cIx]), actualResult, club));
    }


    @Override
    public void solve() {
        input = getInput();
        inputLines = input.split("\n");
        partOne();
        displayVal(partOne+"", partTwo+"");
    }
}
