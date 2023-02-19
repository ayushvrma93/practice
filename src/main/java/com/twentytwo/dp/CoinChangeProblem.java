package com.twentytwo.dp;

public class CoinChangeProblem {


    public int getNoOfWays(int[] coins, int target){

        int size = coins.length;
        int[][] dp = new int[size+1][target+1];

        for(int i=1; i<=target; i++){
            dp[0][i] = 0;
        }

        for(int i=0; i<=size; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<=size; i++){
            for(int j=1; j<=target; j++){

                if(j<coins[i-1]){
                    dp[i][j] = dp[i-1][j];
                }

                else {
                    dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j];
                }
            }
        }
        return dp[size][target];
    }


    public int getMinCoins(int[] coins, int target){

        int size = coins.length;
        int[][] dp = new int[size+1][target+1];

        for(int i=1; i<= target; i++){
            dp[0][i] = Integer.MAX_VALUE - 1;
        }

        for(int i=0; i<=size; i++){
            dp[i][0] = 0;
        }

        for(int i=1; i<=target; i++){
            if(i % coins[0] == 0)
                dp[1][i] = i/coins[0];

            else dp[1][i] = Integer.MAX_VALUE - 1;
        }

        for(int i=2; i<=size; i++){
            for(int j=1; j<=target; j++){

                if(j < coins[i-1])
                    dp[i][j] = dp[i-1][j];

                else dp[i][j] = Math.min(dp[i][j-coins[i-1]] + 1, dp[i-1][j]);
            }
        }
        return dp[size][target];
    }



    public static void main(String[] args) {

        CoinChangeProblem coinChangeProblem = new CoinChangeProblem();

        int sum1 = 4;
        int sum2 = 4;
        int[] coins1 = {1, 2, 3};
        int[] coins2 = {2, 4, 6};

        System.out.println(coinChangeProblem.getNoOfWays(coins1, sum1));
        System.out.println(coinChangeProblem.getNoOfWays(coins2, sum2));

        System.out.println(coinChangeProblem.getMinCoins(coins1, sum1));
        System.out.println(coinChangeProblem.getMinCoins(coins2, sum2));
    }

}
