package com.twentytwo.string;

public class RegularExpressionMatching {

    public boolean perform(char[] text, char[] pattern){

        boolean[][] table = new boolean[text.length +1][pattern.length +1];

        table[0][0] = true;

        //for 0th row
        for(int i=1; i<table[0].length; i++){
            if(pattern[i-1] == '*'){
                table[0][i] = table[0][i-2];
            }
        }

        for(int i=1; i<table.length; i++){
            for (int j=1; j<table[0].length; j++){

                if(text[i-1] == pattern[j-1] || pattern[j-1] == '.')
                    table[i][j] = true;

                else if(pattern[j-1] == '*'){
                    table[i][j] = table[i][j-2];

                    if(pattern[j-2] == '.' || pattern[j-2] == text[i-1])
                        table[i][j] = table[i][j] | table[i-1][j];
                }
            }
        }
        return table[text.length][pattern.length];
    }

    public static void main(String[] args) {

        String text1 = "xaabyc";
        String pattern1 = "xa*b.c";

        String text2 = "";
        String pattern2 = "a*b*";

        String text3 = "";
        String pattern3 = "a*b*c";

        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();

        System.out.println(regularExpressionMatching.perform(text1.toCharArray(), pattern1.toCharArray()));
        System.out.println(regularExpressionMatching.perform(text2.toCharArray(), pattern2.toCharArray()));
        System.out.println(regularExpressionMatching.perform(text3.toCharArray(), pattern3.toCharArray()));
    }
}
