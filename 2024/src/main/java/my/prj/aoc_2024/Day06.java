package my.prj.aoc_2024;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;

import my.prj.aoc_2024.Utils.Pair;

/**
 * Day06
 */
public class Day06 extends AbstractSolution{

    String input;
    String[] inputLines;
    int totalRow = -1;
    int totalCol = -1;
    Set<Pair<Integer,Integer>> visited = new HashSet<>();

    String testInput = String.join("\n",
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#...");

    public Pair<Integer,Integer> getGuard() {
        int x = 0, y = 0;
        for (x = 0; x < totalRow; x++) {
            boolean b = false;
            for (y = 0; y < totalCol; y++) {
                if (inputLines[x].charAt(y) == '^') {
                    b = true;
                    break;
                }
            }
            if (b)
                break;
        }
        return new Pair<Integer,Integer>(x, y);
    }

    public void partOne() {
        Pair<Integer,Integer> guard = getGuard();
        AtomicReference<String> guradDirection = new AtomicReference<>();
        guradDirection.set("UP");
        String[] rotations = new String[] { "UP", "RIGHT", "DOWN", "LEFT" };
        int ri = 0;
        BiFunction<Integer, Integer, Pair<Integer,Integer>> move = (x, y) -> {
            switch (guradDirection.get()) {
                case "UP":
                    x--;
                    break;
                case "DOWN":
                    x++;
                    break;
                case "LEFT":
                    y--;
                    break;
                case "RIGHT":
                    y++;
                    break;
            }
            return new Pair<Integer,Integer>(x, y);
        };
        System.out.println(guard);
        Integer x = guard.x, y = guard.y;
        while (x > -1 && x < totalRow && y > -1 && y < totalCol) {
            Pair<Integer,Integer> n = move.apply(x, y);
            if (n.x > -1 && n.x < totalRow && n.y > -1 && n.y < totalCol && inputLines[n.x].charAt(n.y) == '#') {
                ri = (ri + 1) % 4;
                //System.out.print("(" + x + "," + y + ") {{" + inputLines[x] + "}}    ");

                //System.out.println(inputLines[x].charAt(y) + "  =>  ROTATING : " + rotations[ri]);
                guradDirection.set(rotations[ri]);
            }
            //System.out.print("(" + x + "," + y + ")  =>  MOVING : ");
            Pair<Integer,Integer> p = new Pair<Integer,Integer>(x, y);
            if (!visited.contains(p)) {
                visited.add(p);
            }
            n = move.apply(x, y);

            x = n.x;
            y = n.y;
        }

        System.out.println("-> " + visited.size());

    }

    @Override
    public void solve() {
        input = getInput();
        inputLines = input.split("\n");
        totalRow = inputLines.length;
        totalCol = inputLines[0].length();
        displayVal("", "");
        partOne();
    }

}
