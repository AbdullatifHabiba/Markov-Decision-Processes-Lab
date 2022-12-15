import java.util.Scanner;

public class main {

    public static void print(char[][] policy)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(policy[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
    public static boolean compare2Grid(double[][] gridArr,double[][] PrevGrid) {
        int d=0;
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                d += Math.abs(gridArr[i][j]-PrevGrid[i][j]);
            }
        }
        if(d< 0.0001)
            return true;
        else
            return false;
    }

    public static void copyVal(double[][] gridArr,double[][] prev) {
       for(int i=0;i<3;i++)
       {
           for(int j=0;j<3;j++)
           {
               prev[i][j] = gridArr[i][j];
           }
       }
    }

    public static void Value_Iteration(double[][]gridArr,char[][]grid_policy,double[][]PrevGrid,double discount,int[][]rewardArr)
    {

        while( true){
            double d=0;
            copyVal(gridArr,PrevGrid);
            print(grid_policy);
           for (int i=0;i<3 ;i++) {
             for (int j=0;j<3;j++) {
                gridArr[i][j] = Math.max(HelpingMethods.Q_value(i, j, grid_policy, PrevGrid, discount, rewardArr), gridArr[i][j]);
                 System.out.print( gridArr[i][j]+" ");
                d =Math.max(d, Math.abs(gridArr[i][j]-PrevGrid[i][j]));
             }
               System.out.println("\n");
           }
            print(grid_policy);
            HelpingMethods.edit_printPolicy(gridArr,grid_policy);
        if(d<0.0001)
            break;


         }
    }
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int r =sc.nextInt();
        char[][] grid_policy={{'-','R','-'},{'R','R','R'},{'R','R','R'}};
        int[][]rewardArr={{r,-1,10},{-1,-1,-1},{-1,-1,-1}};
        double[][]gridArr={{r,0,10},{0,0,0},{0,0,0}};
        double[][]PrevGrid ={{1,1,1},{1,1,1},{1,1,1}};
        double discount=0.99;

        Value_Iteration(gridArr,grid_policy,PrevGrid,discount,rewardArr);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(gridArr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
