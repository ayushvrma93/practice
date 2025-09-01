package com.twentyfour.recursion;

import java.util.Arrays;

public class CountNoOfWays {

    public int findWays(int n){
        if(n < 0){
            return 0;
        }

        if(n == 0){
            return 1;
        }

        return findWays(n-1) + findWays(n-2);
    }

    public int climbStairs(int n) {
        int[] memo = new int[n+1];
        Arrays.fill(memo, 0);
        return climbStairs(0, n, memo);
    }

    public int climbStairs(int i, int n, int[] memo) {
        if(i>n) return 0;

        if(memo[i] != 0){
            return memo[i];
        }

        memo[i] = memo[i]++;

        if(i==n){
            return 1;
        }

        return climbStairs(i+1, n, memo) + climbStairs(i+2, n, memo);

    }

    public static void main(String[] args) {
        CountNoOfWays countNoOfWays = new CountNoOfWays();
//        System.out.println(countNoOfWays.findWays(3));
        System.out.println(countNoOfWays.climbStairs(3));
        System.out.println(countNoOfWays.climbStairs(2));
        System.out.println(countNoOfWays.climbStairs(44));
    }
}
