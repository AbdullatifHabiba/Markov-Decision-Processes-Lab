import java.util.Scanner;

public class Main {

    public static void Policy_Iteration(double[][] gridArr, char[][] grid_policy, double[][] PrevGrid, double discount, int[][] rewardArr) {
        char[][] PrevPolicy = new char[3][3];
        do {
            HelpingMethods.copyPolicy(PrevPolicy, grid_policy);
            HelpingMethods.copyVal(gridArr, PrevGrid);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!(i == 0 && j == 0 || i == 0 && j == 2)) {
                        gridArr[i][j] = Math.max(HelpingMethods.Q_value(i, j, grid_policy, gridArr, discount, rewardArr), gridArr[i][j]);
                    }
                }
            }
            HelpingMethods.policyExtraction(gridArr, grid_policy, rewardArr);
        } while (!HelpingMethods.checkPolicy(PrevPolicy, grid_policy));
    }

    public static void Value_Iteration(double[][] gridArr, char[][] grid_policy, double[][] PrevGrid, double discount, int[][] rewardArr) {
        while (true) {
            double d = 0;
            HelpingMethods.copyVal(gridArr, PrevGrid);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!(i == 0 && j == 0 || i == 0 && j == 2)) {
                        gridArr[i][j] = Math.max(HelpingMethods.maxvalue(i, j, gridArr, discount, rewardArr), gridArr[i][j]);
                        d = Math.max(d, Math.abs(gridArr[i][j] - PrevGrid[i][j]));
                        grid_policy[i][j] = HelpingMethods.findDirection(i, j, gridArr, rewardArr, grid_policy[i][j]);
                    }
                }
            }
            if (d < 0.0001) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int[][] rewardArr = {{r, -1, 10}, {-1, -1, -1}, {-1, -1, -1}};

        char[][] grid_policy = {{'-', 'R', '-'}, {'R', 'R', 'R'}, {'R', 'R', 'R'}};
        double[][] gridArr = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double[][] PrevGrid = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double discount = 0.99;
        long t = System.nanoTime();
        Value_Iteration(gridArr, grid_policy, PrevGrid, discount, rewardArr);
        long t2 = System.nanoTime();
        HelpingMethods.print(grid_policy);
        HelpingMethods.printGrid(gridArr, rewardArr);
        System.out.println("time of value iteration =" + (t2 - t) / 1000000.0 + "ms\n");

        char[][] grid_policy2 = {{'-', 'R', '-'}, {'R', 'R', 'R'}, {'R', 'R', 'R'}};
        double[][] gridArr2 = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double[][] PrevGrid2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        long t3 = System.nanoTime();
        Policy_Iteration(gridArr2, grid_policy2, PrevGrid2, discount, rewardArr);
        long t4 = System.nanoTime();
        HelpingMethods.print(grid_policy2);
        HelpingMethods.printGrid(gridArr2, rewardArr);
        System.out.println("time of policy iteration =" + (t4 - t3) / 1000000.0 + "ms");
    }
}
