package my.prj.aoc_2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Day01
 */
public class Day01 extends AbstractSolution {
    List<Integer> left = new ArrayList<Integer>();
    List<Integer> right = new ArrayList<Integer>();

    void binarySearchInsert(int val, List<Integer> list) {
        int len = list.size();
        int low = 0, high = len - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int mid_val = list.get(mid).intValue();
            if (mid_val == val) {
                low = mid;
                break;
            } else if (val > mid_val)
                low = mid + 1;
            else
                high = mid - 1;
        }

        if (list.size() == 0) {
            list.add(val);
        } else {
            list.add(low, val);
        }
    }

    public void solve() {
        String input = getInput();
        String[] inputlines = input.split("\n");

        Map <Integer , Integer> counter = new HashMap<>();

        for (String line : inputlines) {
            String[] ln = line.split("   ");
            int r = Integer.parseInt(ln[1]);

            binarySearchInsert(Integer.parseInt(ln[0]), left);
            binarySearchInsert(r, right);

            Integer count = counter.get(r);
            if (count == null) {
               counter.put(r, r);
            }else{
               counter.put(r, count + r);
            }
        }

        long total = 0;
        long similarityScore = 0;
        for (int i = 0; i < left.size(); i++) {
            int l = left.get(i);

            int v = l - right.get(i);

            if (v < 0) {
                v *= -1;
            }
            total += v;

            Integer count = counter.get(l);
            if (count != null) {
                similarityScore += count;
            }

        }

        displayVal(total+"", similarityScore+"");
    }

}
