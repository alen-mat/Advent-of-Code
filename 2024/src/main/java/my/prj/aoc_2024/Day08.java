package my.prj.aoc_2024;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Day08
 * --- Redo
 */
public class Day08 extends AbstractSolution{

    String testInput = String.join("\n",
            "............",
            "........0...",
            ".....0......",
            ".......0....",
            "....0.......",
            "......A.....",
            "............",
            "............",
            "........A...",
            ".........A..",
            "............",
            "............");

    int width = 0;
    int length = 0;

    String input;
    String[] inputLines;

    public Day08() {
        input = getInput();
        inputLines = input.split("\n");
        width = inputLines[0].length();
        length = inputLines.length;
    }

    @Override
    public void solve() {
        Map<Character, List<Map.Entry<Integer, Integer>>> antPos = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                char c = inputLines[i].charAt(j);
                if (c != '.') {
                    List<Map.Entry<Integer, Integer>> pos = antPos.get(c);
                    if (pos == null) {
                        pos = new ArrayList<Map.Entry<Integer, Integer>>();
                        antPos.put(c, pos);
                    }
                    pos.add(new AbstractMap.SimpleEntry<Integer, Integer>(i, j));
                }
            }
        }

        Iterator<Map.Entry<Character, List<Map.Entry<Integer, Integer>>>> it = antPos.entrySet().iterator();
        Set<Map.Entry<Integer, Integer>> antiNodesPart1 = new HashSet<>();
        Set<Map.Entry<Integer, Integer>> antiNodesPart2 = new HashSet<>();
        while (it.hasNext()) {
            Map.Entry<Character, List<Map.Entry<Integer, Integer>>> v = it.next();
            List<Map.Entry<Integer, Integer>> pos = v.getValue();
            for (Map.Entry<Integer, Integer> p1 : pos) {
                int x1 = p1.getKey();
                int y1 = p1.getValue();
                for (Map.Entry<Integer, Integer> p2 : pos) {
                    if (p1 != p2) {
                        int x2 = p2.getKey();
                        int y2 = p2.getValue();

                        int xv = (x2 - x1);
                        int yv = (y2 - y1);
                        {

                            int x = x2 + xv;
                            int y = y2 + yv;
                            if (0 <= x && x < length && 0 <= y && y < width) {
                                antiNodesPart1.add(new AbstractMap.SimpleEntry<Integer, Integer>(x, y));
                            }
                        }

                        int gcd = BigInteger.valueOf(xv).gcd(BigInteger.valueOf(yv)).intValue();
                        xv /= gcd;
                        yv /= gcd;

                        for (int i = 0;; i++) {
                            int x = x2 + (i * xv);
                            int y = y2 + (i * yv);
                            if (0 <= x && x < length && 0 <= y && y < width) {
                                Map.Entry<Integer, Integer> p = new AbstractMap.SimpleEntry<Integer, Integer>(x, y);
                                if (!p.equals(p1))
                                    antiNodesPart2.add(p);
                            } else
                                break;
                        }
                    }
                }
            }
        }

        // for (int i = 0; i < length; i++) {
        // for (int j = 0; j < width; j++) {
        // char c = inputLines[i].charAt(j);
        // if (antiNodes.contains(new AbstractMap.SimpleEntry<Integer, Integer>(i, j)))
        // {
        // if (c != '.') {
        // System.out.print(ConsoleColors.RED + c + ConsoleColors.RESET);
        // } else {
        // System.out.print('#');
        // }
        // } else {
        // System.out.print(c);
        // }
        // }
        // System.out.println("");
        // }
        displayVal(antiNodesPart1.size()+"", antiNodesPart2.size()+"");
    }

}
