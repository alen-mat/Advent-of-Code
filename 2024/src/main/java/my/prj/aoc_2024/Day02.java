package my.prj.aoc_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Day02
 * --- Day 2: Red-Nosed Reports ---
 * `https://adventofcode.com/2024/day/2`
 */
public class Day02 extends AbstractSolution{
    Object[] isSafe(List<Integer> vals) {
        int i = 0, j = i + 1;
        Boolean shouldBePos = null;
        while (j < vals.size()) {
            int diff = vals.get(i) - vals.get(j);
            boolean isPositive = diff > 0;
            shouldBePos = (shouldBePos == null) ? isPositive : shouldBePos;

            if (shouldBePos != isPositive) {
                return new Object[] { false, j };
            }

            diff = Math.abs(diff);
            if (!(diff >= 1 && diff <= 3)) {
                return new Object[] { false, j };
            }
            i = j;
            j += 1;
        }
        return new Object[] { true, null };
    }

    @Override
    public void solve() {
        int safeCount = 0;
        int safeCountD = 0;
        String input = getInput();
        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            List<Integer> vals = Arrays.stream(lines[i].split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            Object[] safe = isSafe(vals);
            if ((boolean) safe[0]) {
                safeCount++;
                safeCountD++;
            } else {
                int j = (int) safe[1];
                AtomicBoolean ok = new AtomicBoolean(false);
                Function<Integer, Void> consider = (index) -> {
                    ArrayList<Integer> val_cp = new ArrayList<>(vals);
                    val_cp.remove((int) index);
                    if ((boolean) isSafe(val_cp)[0]) {
                        ok.set(true);
                    }
                    return null;
                };

                consider.apply(j);
                if (!ok.get() && j + 1 < vals.size() - 1) {
                    consider.apply(j + 1);
                }
                if (!ok.get() && j + 2 < vals.size() - 1) {
                    consider.apply(j + 2);
                }
                if (!ok.get()) {
                    consider.apply(0);
                }
                if (!ok.get()) {
                    consider.apply(vals.size() - 1);

                }
                if (ok.get()) {
                    safeCountD++;
                }
            }
        }

        displayVal(safeCount + "", safeCountD + "");
    }
}
