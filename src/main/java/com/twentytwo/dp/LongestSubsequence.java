package com.twentytwo.dp;

public class LongestSubsequence {

    private int byRecursionUtil(String A, String B, int m, int n){

        if(m == 0 || n == 0) return 0;

        if(A.charAt(m-1) == B.charAt(n-1))
            return 1 + byRecursionUtil(A, B, m-1, n-1);

        return Math.max(byRecursionUtil(A, B, m-1, n), byRecursionUtil(A, B, m, n-1));
    }

    public int byRecursion(String A, String B){
        return byRecursionUtil(A, B, A.length(), B.length());
    }


    public int byTabulation(String A, String B){

        int lenA = A.length();
        int lenB = B.length();

        int[][] dp = new int[lenA+1][lenB+1];
        int result = 0;

        for(int i = 0; i <= lenA; i++){
            for(int j = 0; j <= lenB; j++){

                if(i == 0 || j == 0)
                    dp[i][j] = 0;

                else if(A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }

                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        String str1 = "acdfn";
        String str2 = "zcdeqlp";

        String str3 = "zacdfn";
        String str4 = "zcdeqlp";

        LongestSubsequence longestSubsequence = new LongestSubsequence();

        System.out.println("By recursion "+ longestSubsequence.byRecursion(str1, str2));
        System.out.println("By recursion "+ longestSubsequence.byRecursion(str3, str4));

        System.out.println("By tabulation "+ longestSubsequence.byTabulation(str1, str2));
        System.out.println("By tabulation "+ longestSubsequence.byTabulation(str3, str4));
    }
}
