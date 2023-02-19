package com.twentytwo.dp;

public class UnboundedKnapSack {

    public int byMatrix(int[] wt, int[] val, int W){

        int size = val.length;
        int[][] dp = new int[size + 1][W + 1];

        for(int i = 0; i<= size; i++){
            for(int j = 0; j <= W; j++){

                if(i == 0 || j == 0)
                    dp[i][j] = 0;

                else if(wt[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }

                else {
                    //for 0-1 knapsack put => dp[i][j] = Math.max(val[i-1] + dp[i-1][j - wt[i-1]], dp[i-1][j]);
                    dp[i][j] = Math.max(val[i-1] + dp[i][j - wt[i-1]], dp[i-1][j]);
                }
            }
        }
        return dp[size][W];
    }


    public static void main(String[] args) {

        UnboundedKnapSack unboundedKnapSack = new UnboundedKnapSack();

        int[] val1 = {10, 40, 50, 70};
        int[] wt1 = {1, 3, 4, 5};
        int W1 = 8;

        int[] val2 = {1, 30};
        int[] wt2 = {2, 50};
        int W2 = 100;

        System.out.println(unboundedKnapSack.byMatrix(wt1, val1, W1));
        System.out.println(unboundedKnapSack.byMatrix(wt2, val2, W2));
    }
}
