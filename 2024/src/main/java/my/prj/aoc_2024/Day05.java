package my.prj.aoc_2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Day05
 * --- Day 5: Print Queue ---
 * https://adventofcode.com/2024/day/5
 */
public class Day05 extends AbstractSolution{
    int partOneTotal = 0;
    int partTwoTotal = 0;

    String testInput = String.join("\n",
            "47|53",
            "97|13",
            "97|61",
            "97|47",
            "75|29",
            "61|13",
            "75|53",
            "29|13",
            "97|29",
            "53|29",
            "61|53",
            "97|53",
            "61|29",
            "47|13",
            "75|47",
            "97|75",
            "47|61",
            "75|61",
            "47|29",
            "75|13",
            "53|13",
            "",
            "75,47,61,53,29",
            "97,61,53,29,13",
            "75,29,13",
            "75,97,47,61,53",
            "61,13,29",
            "97,13,75,29,47");

    String input;

    String[] inputLines;

    HashMap<Integer, Set<Integer>> rules = new HashMap<Integer, Set<Integer>>();

    public int ruleBuilder() {
        int i = 0;
        for (String line : inputLines) {
            if (line.length() == 0) {
                break;
            }
            String[] l = line.split("\\|");
            int left = Integer.parseInt(l[0]);
            int right = Integer.parseInt(l[1]);
            Set<Integer> b4 = rules.get(right);
            if (b4 == null) {
                b4 = new HashSet<Integer>();
                rules.put(right, b4);
            }
            if (!b4.contains(left)) {
                b4.add(left);
            }
            i++;
        }
        return i;
    }

    void partOne(int pageUpdateLine) {
        for (int i = pageUpdateLine; i < inputLines.length; i++) {
            int[] pages = Arrays.stream(inputLines[i].split(",")).mapToInt(Integer::parseInt).toArray();

            boolean ok1 = true;
            int k = pages.length - 1;
            for (int j = pages.length - 1; j > 0 && ok1; j--) {
                Set<Integer> s = rules.get(pages[j]);
                if (s != null) {
                    k = j - 1;
                    for (; k > -1; k--) {
                        if (!s.contains(pages[k])) {
                            ok1 = false;
                            break;
                        }
                    }
                } else {
                    ok1 = false;
                }
            }
            if (ok1) {
                partOneTotal += pages[(pages.length - 1) / 2];
            } else {
                partTwoTotal += getMidByRules(pages, (k==-1 ? pages.length -1 : k));
            }
        }

    }
    //topological sort - but adjecency matrix inverse(inbound)
    int getMidByRules(int[] pages, int failedIndex) {
        int midIndex = (pages.length - 1) / 2;
        if (failedIndex <=midIndex){
            return pages[midIndex];
        }
        int l = failedIndex;
        for (; l > -1; l--) {
            System.out.print(pages[l] + " ,");
        }
        System.out.println();
        return 0;
    }
    
    //int topo(int a,List<Integer> order, Map<Integer,Integer> inDegree){
    //    order.add(a);
    //    for(int b :  edges[a]){
    //        if (present.count(b)) {
    //            if (--in[b] == 0) {
    //               topo(b,order,inDegree);
    //            }
    //        }
    //    }
    //}


    @Override
    public void solve() {
        // input = getInput();
        input = testInput;

        inputLines = input.split("\n");
        int pageUpdateLine = ruleBuilder() + 1;
        partOne(pageUpdateLine);
        displayVal(partOneTotal + "", partTwoTotal + "");
    }

}
