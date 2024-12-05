package my.prj.aoc_2024;

/**
 * Utils
 */
public class Utils {
    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Pair[] getNeighbours() {
            return new Pair[] {
                    new Pair(x - 1, y),
                    new Pair(x, y - 1),
                    new Pair(x, y + 1),
                    new Pair(x + 1, y),
                    new Pair(x - 1, y - 1),
                    new Pair(x - 1, y + 1),
                    new Pair(x + 1, y - 1),
                    new Pair(x + 1, y + 1),
            };
        }

    }

    public static class Cordinate {
        public static int[][] getNeighbourMatrix(boolean onlyDiagonal) {
            if (onlyDiagonal) {
                return new int[][] {
                        new int[] { -1, -1 },
                        new int[] { -1, 1 },
                        new int[] { 1, -1 },
                        new int[] { 1, 1 },
                };
            } else {
                return new int[][] {
                        new int[] { -1, -1 },
                        new int[] { -1, 0 },
                        new int[] { -1, 1 },
                        new int[] { 0, -1 },
                        new int[] { 0, 1 },
                        new int[] { 1, -1 },
                        new int[] { 1, 0 },
                        new int[] { 1, 1 },
                };
            }
        }
    }
}
