package my.prj.aoc_2024;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public interface ISolution {
    public default String getInput() {
        String file = "/" + this.getClass().getSimpleName() + ".txt";
        InputStream is = Day01.class.getResourceAsStream(file);

        @SuppressWarnings("resource")
        String input = new BufferedReader(
                new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));

        return input;
    }

    public void solve();

    public default void displayVal(String part1, String part2) {
        System.out.println(
                this.getClass().getSimpleName() + "\n" + "  - Part 1 : " + part1 + "\n" + "  - Part 2 : " + part2);
    }

}
