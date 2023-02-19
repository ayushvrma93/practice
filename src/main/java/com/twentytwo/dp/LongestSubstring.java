package com.twentytwo.dp;

public class LongestSubstring {

    // Wrong method
    private int byRecursionUtil(String A, String B, int m, int n){

        if(m == 0 || n == 0) return 0;

        int firstString = byRecursionUtil(A, B, m-1, n);
        int secondString = byRecursionUtil(A, B, m, n-1);

        if(A.charAt(m-1) == B.charAt(n-1))
            return 1 + Math.max(firstString, secondString);

        else return 0;


    }

    // Wrong method
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

                else dp[i][j] = 0;
            }
        }
        return result;
    }


    public static void main(String[] args) {

        String str1 = "acdfn";
        String str2 = "zcdeqlp";

        String str3 = "zacdfn";
        String str4 = "zcdeqlp";

        LongestSubstring longestSubstring = new LongestSubstring();

        System.out.println("By recursion "+ longestSubstring.byRecursion(str1, str2));
        System.out.println("By recursion "+ longestSubstring.byRecursion(str3, str4));

        System.out.println("By tabulation "+ longestSubstring.byTabulation(str1, str2));
        System.out.println("By tabulation "+ longestSubstring.byTabulation(str3, str4));
    }
}
