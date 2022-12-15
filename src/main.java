import java.util.Scanner;

public class main {


    public static void Policy_Iteration(double[][]gridArr,char[][]grid_policy,double[][]PrevGrid,double discount,int[][]rewardArr)
    {
        char[][] PrevPolicy=new char[3][3];
        while( true){
            HelpingMethods.print(grid_policy);
            HelpingMethods.copyPolicy(PrevPolicy,grid_policy);
            HelpingMethods.copyVal(gridArr,PrevGrid);
            gridArr[0][0]=rewardArr[0][0];
            gridArr[0][2]=rewardArr[0][2];
            for (int i=0;i<3 ;i++) {
                for (int j=0;j<3;j++) {
                    if(!(i==0 &&j==0 || i==0&&j==2)) {
                        gridArr[i][j] = Math.max(HelpingMethods.Q_value(i, j, grid_policy, PrevGrid, discount, rewardArr), gridArr[i][j]);
                    }
                    System.out.print(String.format("%.2f",gridArr[i][j])+" ");
                }
                System.out.println();
            }
            HelpingMethods.policyExtraction(gridArr,grid_policy,rewardArr);
            if(HelpingMethods.checkPolicy(PrevPolicy,grid_policy)){
                break;
            }
        }
    }

    public static void Value_Iteration(double[][]gridArr,char[][]grid_policy,double[][]PrevGrid,double discount,int[][]rewardArr)
    {

        while( true){
            double d=0;
            HelpingMethods.copyVal(gridArr,PrevGrid);
           for (int i=0;i<3 ;i++) {
             for (int j=0;j<3;j++) {
                 if(!(i==0 &&j==0 || i==0&&j==2)) {
                     gridArr[i][j] = Math.max(HelpingMethods.Q_value(i, j, grid_policy, PrevGrid, discount, rewardArr), gridArr[i][j]);
                     d = Math.max(d, Math.abs(gridArr[i][j] - PrevGrid[i][j]));
                     grid_policy[i][j]=HelpingMethods.findDirection(i,j,gridArr,rewardArr,grid_policy[i][j]);
                 }
             }
           }
        if(d<0.0001) {
            break;
        }
        HelpingMethods.print(grid_policy);
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

       // Value_Iteration(gridArr,grid_policy,PrevGrid,0.99,rewardArr);
        Policy_Iteration(gridArr,grid_policy,PrevGrid,0.99,rewardArr);
//        for(int i=0;i<3;i++)
//        {
//            for(int j=0;j<3;j++)
//            {
//                if((i==0 &&j==0 || i==0&&j==2)) {
//                    System.out.print(rewardArr[i][j]+" ");
//                }else {
//                    System.out.print(gridArr[i][j] + " ");
//                }
//            }
//            System.out.println();
//        }
    }
}
