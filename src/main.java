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
//        gridArr[0][0]=rewardArr[0][0];
//        gridArr[0][2]=rewardArr[0][2];
//        HelpingMethods.edit_printPolicy(gridArr,grid_policy);
        while( true){
            double d=0;
            copyVal(gridArr,PrevGrid);
           for (int i=0;i<3 ;i++) {
             for (int j=0;j<3;j++) {
                 if(!(i==0 &&j==0 || i==0&&j==2)) {
                     gridArr[i][j] = Math.max(HelpingMethods.Q_value(i, j, grid_policy, PrevGrid, discount, rewardArr), gridArr[i][j]);
                     d = Math.max(d, Math.abs(gridArr[i][j] - PrevGrid[i][j]));
                 }
             }
           }
        if(d<0.0001)
            break;
        HelpingMethods.edit_printPolicy(gridArr,grid_policy,rewardArr);
        print(grid_policy);
         }
    }
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int r =sc.nextInt();
        char[][] grid_policy={{'-','R','-'},{'R','R','R'},{'R','R','R'}};
        int[][]rewardArr={{r,-1,10},{-1,-1,-1},{-1,-1,-1}};
        double[][]gridArr={{0,0,0},{0,0,0},{0,0,0}};
        double[][]PrevGrid ={{1,1,1},{1,1,1},{1,1,1}};
        double discount=0.99;

        Value_Iteration(gridArr,grid_policy,PrevGrid,0.99,rewardArr);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if((i==0 &&j==0 || i==0&&j==2)) {
                    System.out.print(rewardArr[i][j]+" ");
                }else {
                    System.out.print(gridArr[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
