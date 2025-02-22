package my.prj.aoc_2024;

import java.util.ArrayList;

/**
 * Day09
 */
public class Day09 extends AbstractSolution{

    class FS extends ArrayList<Integer> {
    }

    int length;
    String input;
    long part1;
    long part2;
    String testInput = "2333133121414131402";
    FS fs = new FS();

    public Day09() {
        input = testInput;
        length = input.length();
    }

    @Override
    public void solve() {
        buildFS();
        FS fsClone = cloneFs();
        defragmentFS(fsClone);
        part1 = checkSum(fsClone);
        defragMove(fs);
        part2 = checkSum(fs);

        printFS(fs);
        displayVal(part1 + "", part2 + "");
    }

    private void defragMove(FS fs) {
        int freeBegin = 0;
        int freeEnd = 0;
        int fileBegin = 0;
        int fileEnd = fs.size() -1;
        while (freeBegin < fs.size() && freeEnd < fs.size()) {
            for (; freeBegin < fs.size() && fs.get(freeBegin) != -1; freeBegin++) {
            }
            for (freeEnd = freeBegin; freeEnd + 1 < fs.size() && fs.get(freeEnd + 1) == -1; freeEnd++) {
            }

            int freeSize = freeEnd - freeBegin + 1;

            System.out.println(freeBegin + " -- " + freeEnd + " == " + freeSize);
            freeBegin = freeEnd + 1;
        }
    }

    private FS cloneFs() {
        FS fsc = new FS();
        for (int i : fs)
            fsc.add(i);
        return fsc;
    }

    void buildFS() {
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            int num = Integer.parseInt(c + "");
            boolean isFile = i % 2 == 0;
            int fileId = -1;
            if (isFile) {
                fileId = i / 2;
            }
            for (; num > 0; num--) {
                fs.add(fileId);
            }
        }
    }

    void defragmentFS(FS fs) {
        int freep = 0;
        int filep = fs.size() - 1;
        while (true) {
            for (; freep < fs.size(); freep++) {
                if (fs.get(freep) == -1) {
                    break;
                }
            }
            for (; filep > -1; filep--) {
                if (fs.get(filep) != -1) {
                    break;
                }
            }
            if (freep >= filep) {
                break;
            }

            fs.set(freep, fs.get(filep));
            fs.set(filep, -1);
        }
    }

    long checkSum(FS fs) {
        long cs = 0;
        for (int i = 0; i < fs.size(); i++) {
            if (fs.get(i) == -1)
                break;
            cs += (i * fs.get(i));
        }
        return cs;
    }

    void printFS(FS fs) {
        for (Integer i : fs) {
            if (i == -1)
                System.out.print(".");
            else
                System.out.print(i);
        }
        System.out.println();
    }

}
