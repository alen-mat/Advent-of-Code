package my.prj.aoc_2024;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import my.prj.aoc_2024.Utils.Cordinate;
import my.prj.aoc_2024.Utils.Pair;

/**
 * Day04
 * --- Day 4: Ceres Search ---
 * https://adventofcode.com/2024/day/4
 */
public class Day04 implements ISolution {

    int part2Total = 0;

    String[] inputLines;
    int rowNum;
    int colNum;

    Map<Pair, Set<Pair>> validPair = new HashMap<Pair, Set<Pair>>();

    String testInput = String.join("\n",
            "MMMSXXMASM",
            "MSAMXMSMSA",
            "AMXSXMAAMM",
            "MSAMASMSMX",
            "XMASAMXAMM",
            "XXAMMXXAMA",
            "SMSMSASXSS",
            "SAXAMASAAA",
            "MAMMMXMMMM",
            "MXMXAXMASX");

    void hasValidXmas(Pair start) {
        for (int[] c : Cordinate.getNeighbourMatrix(false)) {
            Pair end = null;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int xe = start.x + (c[0] * 0);
            int ye = start.y + (c[1] * 0);
            while (i < 4 && xe > -1 && xe < rowNum && ye > -1 && ye < colNum) {
                sb.append(inputLines[xe].charAt(ye) + "");
                i++;
                xe = start.x + (c[0] * i);
                ye = start.y + (c[1] * i);
            }
            if (sb.length() == 4 && "XMAS".equals(sb.toString())) {
                end = new Pair(xe, ye);
            }

            if (end != null) {
                Set<Pair> cordSet = validPair.get(start);
                if (cordSet == null) {
                    cordSet = new HashSet<Pair>();
                    validPair.put(start, cordSet);
                }
                if (!cordSet.contains(end)) {
                    cordSet.add(end);
                }
            }
        }
    }

    void hasValidMas(Pair start) {
        Pair end = null;
        StringBuilder sbMainDiag = new StringBuilder();
        StringBuilder sbAltDiag = new StringBuilder();
        int i = 0;

        int xe = start.x - 1;
        int ye = start.y - 1;
        while (i < 3 && xe > -1 && xe < rowNum && ye > -1 && ye < colNum) {
            sbMainDiag.append(inputLines[xe].charAt(ye) + "");
            i++;
            xe++;
            ye++;
        }

        if (sbMainDiag.length() == 3 && ("MAS".equals(sbMainDiag.toString()) || "SAM".equals(sbMainDiag.toString()))) {
            i = 0;
            xe = start.x - 1;
            ye = start.y + 1;
            while (i < 3 && xe > -1 && xe < rowNum && ye > -1 && ye < colNum) {
                sbAltDiag.append(inputLines[xe].charAt(ye) + "");
                i++;
                xe++;
                ye--;
            }
            if (sbAltDiag.length() == 3 && ("SAM".equals(sbAltDiag.toString()) || "MAS".equals(sbAltDiag.toString())
                    || "SAM".equals(sbAltDiag.toString()))) {
                part2Total++;
            }
        }
    }

    /**
     * @param inputLines
     * @return
     */
    public int part1() {
        int count = 0;
        for (int i = 0; i < inputLines.length; i++) {
            for (int j = 0; j < inputLines[i].length(); j++) {
                char s = inputLines[i].charAt(j);
                if (s == 'X') {
                    Pair start = new Pair(i, j);
                    hasValidXmas(start);
                }
            }
        }

        for (Map.Entry<Pair, Set<Pair>> e : validPair.entrySet()) {
            count += e.getValue().size();
        }
        return count;
    }

    public int part2() {
        int count = 0;
        for (int i = 0; i < inputLines.length; i++) {
            for (int j = 0; j < inputLines[i].length(); j++) {
                char s = inputLines[i].charAt(j);
                if (s == 'A') {
                    Pair start = new Pair(i, j);
                    hasValidMas(start);
                }
            }
        }

        for (Map.Entry<Pair, Set<Pair>> e : validPair.entrySet()) {
            count += e.getValue().size();
        }
        return count;
    }

    @Override
    public void solve() {
        String input = getInput();
        inputLines = input.split("\n");
        rowNum = inputLines.length;
        colNum = inputLines[0].length();
        part2();
        displayVal(part1() + "", part2Total + "");
    }

}
