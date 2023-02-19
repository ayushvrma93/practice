package com.twentytwo.dp;

public class LongestPalindromicSubstring {

    LongestSubstring longestSubstring = new LongestSubstring();

    public int getByTabulation(String str){
        String reverseString = reverse(str);
        return longestSubstring.byTabulation(str, reverseString);

    }

    private String reverse(String str) {
        if(str.isEmpty()) return "";

        char[] charArray = str.toCharArray();

        for(int i=0, j= charArray.length-1; i<j; i++, j--){
            swap(charArray, i, j);
        }
        return String.valueOf(charArray);
    }

    private void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }


    public static void main(String[] args) {

        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();

        String str1 = "bpadaqrdgggd";
        System.out.println(longestPalindromicSubstring.getByTabulation(str1));
    }
}
