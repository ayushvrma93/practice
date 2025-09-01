package com.twentyfour.dp;

public class CoinChange {
    public int minCoins(int[] arr, int t, int n){
        if(n == 0){
            if(t % arr[n] == 0){
                return t/arr[n];
            }
            return (int)1e9;
        }

        int take = Integer.MAX_VALUE -1;

        if(arr[n] <= t){
            take = 1 + minCoins(arr, t-arr[n], n);
        }

        int notTake = minCoins(arr, t, n-1);

        return Math.min(take, notTake);
    }

    public static void main(String[] args) {
        CoinChange change = new CoinChange();
        int[] coins = {1,2,5};
        int[] coins2 = {2};
        System.out.println(change.minCoins(coins, 11, 2));
        System.out.println(change.minCoins(coins2, 3, 0));
    }
}
