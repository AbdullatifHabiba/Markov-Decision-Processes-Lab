public class HelpingMethods {

    public static char findDirection(int i,int j,double[][]gridArr,int[][] reward,char prevpolicy) {
        if((i==0 && j==2)||(i==0 && j==0))
        {
            return '-';
        }
        double maximumV = gridArr[i][j];
        char maxChar =prevpolicy ;
        if (i + 1 < gridArr.length && (maximumV<gridArr[i + 1][j])) {
            maximumV = gridArr[i + 1][j];
            maxChar = 'D';
        }
        if (i - 1 >= 0 && (maximumV<gridArr[i - 1][j]||maximumV<reward[i-1][j])) {
            maximumV = Math.max(gridArr[i - 1][j],reward[i-1][j]);
            maxChar = 'U';
        }
        if (j+1<3 && (maximumV<gridArr[i][j+1]||maximumV<reward[i][j+1])) {
            maximumV = Math.max(gridArr[i][j+1],reward[i][j+1]);
            maxChar = 'R';

        } if (j-1 >=0 && (maximumV<gridArr[i][j-1]||maximumV<reward[i][j-1])) {
            maximumV = Math.max(gridArr[i][j-1],reward[i][j-1]);
            maxChar = 'L';

        }
        return maxChar;
    }

    public static void edit_printPolicy(double[][]gridArr,char[][]grid_policy,int [][] reward) {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                grid_policy[i][j]=findDirection(i,j,gridArr,reward,grid_policy[i][j]);
            }
        }
    }

    public static double Q_value(int i, int j,char[][] grid_policy,double[][] gridArr,double discount,int[][] rewardArr){

        double q=0;
        if (grid_policy[i][j] == 'R') {
            if (j < 2){q = .8 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);}
            else{q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
            if (i > 0){ q += .1 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]); }
            else{q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
            if(i<2){q += .1 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);}
            else{q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
        }
        else if (grid_policy[i][j]=='U') {
            if (i < 2){  q = .8 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);}
            else{q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
            if (j > 0){q += .1 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]);}
            else{q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
            if (j < 2){q += .1 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);}
            else{q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
        }
        else if (grid_policy[i][j]=='D') {
            if (i > 0){ q = .8 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]) ;}
            else{q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
            if (j > 0){q += .1 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]);}
            else{q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
            if (j < 2){ q += .1 * (rewardArr[i][j + 1] + discount * gridArr[i][j + 1]);}
            else{q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
        }
        else if (grid_policy[i][j]=='L') {
            if (j > 0){q = .8 * (rewardArr[i][j - 1] + discount * gridArr[i][j - 1]) ;}
            else{q = .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
            if (i > 0) {q += .1 * (rewardArr[i - 1][j] + discount * gridArr[i - 1][j]);}
            else{q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
            if (i < 2){q += .1 * (rewardArr[i + 1][j] + discount * gridArr[i + 1][j]);}
            else{q += .1 * (rewardArr[i][j] + discount * gridArr[i][j]);}
        }
        return q;
    }
}
