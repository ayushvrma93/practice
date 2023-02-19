package com.twentytwo.string;

public class LongestPalindromicSubstring {

    class MyObj{
        int length;
        int endIndex;

        MyObj(int length, int endIndex){
            this.length = length;
            this.endIndex = endIndex;
        }

        @Override
        public String toString() {
            return "MyObj{" +
                    "length=" + length +
                    ", endIndex=" + endIndex +
                    '}';
        }
    }

    private String byBruteForce(String mainString){

        if(mainString == null)
            return null;

        if(mainString.isBlank())
            return "";

        int stringLen = mainString.length();
        int longestStringSize = 1;
        int startIndex = 0;

        char[] charArr = mainString.toCharArray();

        for(int i=0; i< stringLen; i++){
            for (int j=i; j<stringLen; j++){

                if(isPalindrome(charArr, i, j) && j - i + 1 > longestStringSize) {
                    longestStringSize = j - i + 1;
                    startIndex = i;
                }
            }
        }
        return mainString.substring(startIndex, startIndex + longestStringSize);
    }

    private boolean isPalindrome(char[] charArr, int i, int j){
        for(int k=i, l=j; k<l; k++,l--){
            if(charArr[k] != charArr[l])
                return false;
        }
        return true;
    }


    public String byDP(String mainString){

        int maxLength = 1;
        int startIndex = 0;

        int stringLen = mainString.length();
        boolean[][] table = new boolean[stringLen][stringLen];

        for(int i=0; i<stringLen; i++){
            table[i][i] = true;
        }

        for(int i = 0; i<stringLen-1; i++){
            if(mainString.charAt(i) == mainString.charAt(i+1)) {
                table[i][i + 1] = true;
                startIndex = i;
                maxLength = 2;
            }
        }

        for (int k=3; k<=stringLen; k++){

            for(int i=0; i<stringLen-k+1; i++){

                int j = i+k-1;

                if(table[i+1][j-1] && mainString.charAt(i) == mainString.charAt(j)){
                    table[i][j] = true;
                    maxLength = k;
                    startIndex = i;
                }
            }
        }
        String result = mainString.substring(startIndex, startIndex + maxLength);
        return result;
    }


    public MyObj byEasyDP(String str){

        if(str == null) return new MyObj(0,-1);
        if(str.length() == 1) return new MyObj(1, 0);

        int length = str.length();

        String reverse = reverse(str);
        int maxLength = 1;
        int endIndex = -1;

        int[][] lengthArr = new int[length+1][length+1];

        for(int i=0; i<=length; i++){
            for(int j=0; j<=length; j++){

                if(i==0 || j==0){
                    lengthArr[i][j] = 0;
                }

                else if(str.charAt(i-1) == reverse.charAt(j-1)){
                    lengthArr[i][j] = 1+lengthArr[i-1][j-1];

                    if(maxLength < lengthArr[i][j]){
                        maxLength = lengthArr[i][j];
                        endIndex = i;
                    }
                }

                else {
                    lengthArr[i][j] = 0;
                }
            }
        }
        return new MyObj(maxLength, endIndex);
    }


    private String reverse(String str){

        if(str == null || str.length() == 1) return str;

        char[] chars = str.toCharArray();
        int length = chars.length;

        for(int i=0, j=length-1; i<j; i++, j--){

            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return String.valueOf(chars);
    }


    public static void main(String[] args) {

        String str1 = "geekskeeg";
        String str2 = "geeks";
        String str3 = "g";

        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();

        System.out.println(longestPalindromicSubstring.byBruteForce(str1));
        System.out.println(longestPalindromicSubstring.byBruteForce(str2));
        System.out.println(longestPalindromicSubstring.byBruteForce(str3));

        System.out.println("By DP -> " + longestPalindromicSubstring.byDP(str1));
        System.out.println("By DP -> " + longestPalindromicSubstring.byDP(str2));
        System.out.println("By DP -> " + longestPalindromicSubstring.byDP(str3));

        System.out.println("By easy DP -> " + longestPalindromicSubstring.byEasyDP(str1));
        System.out.println("By easy DP -> " + longestPalindromicSubstring.byEasyDP(str2));
        System.out.println("By easy DP -> " + longestPalindromicSubstring.byEasyDP(str3));
    }
}
