package my.prj.aoc_2024;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day03
 */
public class Day03 extends AbstractSolution{
    final String PATTERN1 = ".*?mul\\((\\d+),(\\d+)\\).*?";
    final Pattern p1 = Pattern.compile(PATTERN1);

    final String PATTERN2 = "(mul\\((\\d{1,3}),(\\d{1,3})\\))|(do\\(\\))|(don't\\(\\))";
    final Pattern p2 = Pattern.compile(PATTERN2);

    long total1 = 0;
    long total2 = 0;

    public void part1() {
        long total = 0;
        String input = getInput();
        Matcher m1 = p1.matcher(input);
        while (m1.find()) {
            total = (Integer.parseInt(m1.group(1)) * Integer.parseInt(m1.group(2))) + total;
        }

        this.total1 = total;
    }

    public void part2() {
        long total = 0;
        String input = getInput();
        boolean d_o = true;
        Matcher m2 = p2.matcher(input);
        while (m2.find()) {
            if (m2.group(4) != null) {
                d_o = true;
            }
            else if (m2.group(5) != null) {
                d_o = false;
            }
            else if (d_o && m2.group(1) != null) {
                total += Integer.parseInt(m2.group(2)) * Integer.parseInt(m2.group(3));
            }
        }
        this.total2 = total;
    }

    @Override
    public void solve() {
        part1();
        part2();
        displayVal(total1 + "", total2 + "");
    }
}
