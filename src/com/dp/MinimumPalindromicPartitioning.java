package com.dp;

import java.util.Arrays;

public class MinimumPalindromicPartitioning {

    public int byRec(String str){
        char[] chars = str.toCharArray();
        return byRecUtil(chars, 0, str.length() - 1);
    }


    public int byMemo(String str){
        char[] chars = str.toCharArray();
        int strLen = str.length();
        int[][] dp = new int[strLen + 1][strLen + 1];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return byMemoUtil(chars, 0, strLen - 1, dp);
    }


    private int byRecUtil(char[] characters, int start, int end) {

        int minPart = Integer.MAX_VALUE;

        if(start >= end) return 0;

        if(isPalindrome(characters, start, end)) return 0;

        for(int k = start; k < end; k++){
           int minPartYet = byRecUtil(characters, start, k) + byRecUtil(characters, k+1, end) + 1;
           minPart = Math.min(minPartYet, minPart);
        }
        return minPart;
    }


    private int byMemoUtil(char[] characters, int start, int end, int[][] dp) {

        int minPart = Integer.MAX_VALUE;

        if(start >= end) return 0;

        if(dp[start][end] != -1) return dp[start][end];

        if(isPalindrome(characters, start, end)) return 0;

        for(int k = start; k < end; k++){
            int minPartYet = byMemoUtil(characters, start, k, dp) + byMemoUtil(characters, k+1, end, dp) + 1;
            minPart = Math.min(minPartYet, minPart);
        }
        return dp[start][end] = minPart;
    }


    private boolean isPalindrome(char[] characters, int start, int end) {

        while(start < end){
            if(characters[start] != characters[end]) return false;
            start++; end--;
        }
        return true;
    }

    public static void main(String[] args) {

        MinimumPalindromicPartitioning minimumPalindromicPartitioning = new MinimumPalindromicPartitioning();
        String str1 = "NITIN";
        String str2 = "abaded";
        String str3 = "nitik";
        String str4 = "aab";

        System.out.println("by rec: " + minimumPalindromicPartitioning.byRec(str1));
        System.out.println("by rec: " + minimumPalindromicPartitioning.byRec(str2));
        System.out.println("by rec: " + minimumPalindromicPartitioning.byRec(str3));
        System.out.println("by rec: " + minimumPalindromicPartitioning.byRec(str4));

        System.out.println("by dp: " + minimumPalindromicPartitioning.byMemo(str1));
        System.out.println("by dp: " + minimumPalindromicPartitioning.byMemo(str2));
        System.out.println("by dp: " + minimumPalindromicPartitioning.byMemo(str3));
        System.out.println("by dp: " + minimumPalindromicPartitioning.byMemo(str4));

    }
}