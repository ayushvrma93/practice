package com.twentythree.practice.dp;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {

        int len = s.length();

        int[][] dp = new int[len+1][len+1];
        int max = 0;
        int endIndex = -1;

        String rev = reverse(s);
        //System.out.println(rev);

        for(int i=0; i<=len; i++){
            dp[i][0] = 0;
        }

        for(int i=0; i<=len; i++){
            dp[0][i] = 0;
        }

        for(int i=1; i<=len; i++){
            for(int j=1; j<=len;j++){

                if(s.charAt(i-1) == rev.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(dp[i][j] > max){
                        max = dp[i][j];
                        endIndex = i-1;
                    }
                }
                else dp[i][j] = 0;
            }
        }
        print(dp);
        int startIndex = endIndex - max + 1;
        return s.substring(startIndex, endIndex+1);
    }

    private void print(int[][] arr){

        for(int i=0; i<arr.length; i++){
            System.out.println();
            for(int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
        }
    }

    private String reverse(String s){

        char[] chars = s.toCharArray();
        int i=0, j= s.length()-1;

        while(i < j){

            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++; j--;
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {

        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        //System.out.println(lps.longestPalindrome("babad"));
        //System.out.println(lps.longestPalindrome("cbbd"));
        System.out.println(lps.longestPalindrome("aacabdkacaa"));
    }
}
