public class HelpingMethods {

    public static char findDirection(int i, int j, double[][] gridArr, int[][] reward, char prevPolicy) {
        if ((i == 0 && j == 2) || (i == 0 && j == 0)) {
            return '-';
        }
        double maximumV = gridArr[i][j];
        char maxChar = prevPolicy;
        if (i + 1 < gridArr.length && (maximumV < gridArr[i + 1][j])) {
            maximumV = gridArr[i + 1][j];
            maxChar = 'D';
        }
        if (i - 1 >= 0 && (maximumV < gridArr[i - 1][j] || maximumV < reward[i - 1][j])) {
            maximumV = Math.max(gridArr[i - 1][j], reward[i - 1][j]);
            maxChar = 'U';
        }
        if (j + 1 < 3 && (maximumV < gridArr[i][j + 1] || maximumV < reward[i][j + 1])) {
            maximumV = Math.max(gridArr[i][j + 1], reward[i][j + 1]);
            maxChar = 'R';

        }
        if (j - 1 >= 0 && (maximumV < gridArr[i][j - 1] || maximumV < reward[i][j - 1])) {
            maxChar = 'L';
        }
        return maxChar;
    }

    public static void policyExtraction(double[][] gridArr, char[][] grid_policy, int[][] reward) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(i == 0 && j == 0 || i == 0 && j == 2)) {
                    gridArr[i][j] = maxvalue(i, j, gridArr, .99, reward);
                }
                grid_policy[i][j] = findDirection(i, j, gridArr, reward, grid_policy[i][j]);
            }
        }
    }

    public static double Q_value(int i, int j, char[][] grid_policy, double[][] gridArr, double discount, int[][] rewardArr) {
        double q = 0;
        if (grid_policy[i][j] == 'R') {
            if (j < 2) {
                q = .8 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);
            } else {
                q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
            if (i > 0) {
                q += .1 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]);
            } else {
                q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
            if (i < 2) {
                q += .1 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);
            } else {
                q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
        } else if (grid_policy[i][j] == 'U') {
            if (i < 2) {
                q = .8 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);
            } else {
                q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
            if (j > 0) {
                q += .1 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]);
            } else {
                q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
            if (j < 2) {
                q += .1 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);
            } else {
                q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
        } else if (grid_policy[i][j] == 'D') {
            if (i > 0) {
                q = .8 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]);
            } else {
                q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
            if (j > 0) {
                q += .1 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]);
            } else {
                q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
            if (j < 2) {
                q += .1 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);
            } else {
                q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
        } else if (grid_policy[i][j] == 'L') {
            if (j > 0) {
                q = .8 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]);
            } else {
                q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
            if (i > 0) {
                q += .1 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]);
            } else {
                q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
            if (i < 2) {
                q += .1 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);
            } else {
                q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
            }
        }

        return q;
    }

    static double maxvalue(int i, int j, double[][] gridArr, double discount, int[][] rewardArr) {
        double max = -1e9;
        double q;
        if (j < 2) {
            q = .8 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);
        } else {
            q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        if (i > 0) {
            q += .1 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]);
        } else {
            q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        if (i < 2) {
            q += .1 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);
        } else {
            q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        max = Math.max(q, max);
        if (i < 2) {
            q = .8 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);
        } else {
            q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        if (j > 0) {
            q += .1 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]);
        } else {
            q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        if (j < 2) {
            q += .1 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);
        } else {
            q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        max = Math.max(q, max);
        if (i > 0) {
            q = .8 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]);
        } else {
            q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        if (j > 0) {
            q += .1 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]);
        } else {
            q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        if (j < 2) {
            q += .1 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);
        } else {
            q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        max = Math.max(q, max);
        if (j > 0) {
            q = .8 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]);
        } else {
            q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        if (i > 0) {
            q += .1 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]);
        } else {
            q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        if (i < 2) {
            q += .1 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);
        } else {
            q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);
        }
        max = Math.max(q, max);
        return max;
    }

    public static boolean checkPolicy(char[][] prevPolicy, char[][] grid_policy) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (prevPolicy[i][j] != grid_policy[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void print(char[][] policy) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(policy[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printGrid(double[][] grid, int[][] reward) {
        System.out.print("-------------------------------\n");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                if ((i == 0 && j == 0 || i == 0 && j == 2)) {
                    System.out.printf(String.format(java.util.Locale.US," %.6g |", (double) reward[i][j]));
                } else {
                    System.out.print(String.format(java.util.Locale.US," %.6g |", grid[i][j]));
                }
            }
            System.out.println("\n-------------------------------");
        }
    }

    public static void copyVal(double[][] gridArr, double[][] prev) {
        for (int i = 0; i < 3; i++) {
            System.arraycopy(gridArr[i], 0, prev[i], 0, 3);
        }
    }

    public static void copyPolicy(char[][] prev, char[][] gridArr) {
        for (int i = 0; i < 3; i++) {
            System.arraycopy(gridArr[i], 0, prev[i], 0, 3);
        }
    }
}