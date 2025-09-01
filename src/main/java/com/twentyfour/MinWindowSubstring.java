package com.twentyfour;

public class MinWindowSubstring {

    public String minWindow(String s, String t) {

        char[] chas = s.toCharArray();
        char[] chat = t.toCharArray();

        int n = chas.length;

        int[] hasht = new int[256];
        int[] hashs = new int[256];

        int min = (int) (10e9);
        int minStart = -1;
        int minEnd = -1;

        for(char c : chat){
            hasht[c - 'A']++;
        }

        int l=0, r=0;

        while(r<n){

            hashs[chas[r] - 'A']++;

            while(l<=r && containsAll(hasht, hashs)){
                int currLen = r-l+1;
                if(currLen < min){
                    minStart = l;
                    minEnd = r;
                    min = currLen;
                }
                // min = Math.min(min, r-l+1);
                hashs[chas[l] - 'A']--;
                l++;
            }
            r++;
        }

        return minStart == -1 ? "" : s.substring(minStart,minEnd+1);
    }

    public boolean containsAll(int[] hasht, int[] hashs){
        for(int i=0; i<hasht.length; i++){
            if(hashs[i] < hasht[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinWindowSubstring minWindowSubstring = new MinWindowSubstring();
        String s = "ADOBECODEBANC", t = "ABC";

        System.out.println(minWindowSubstring.minWindow(s, t));
        System.out.println(minWindowSubstring.minWindow("a", "a"));
        System.out.println(minWindowSubstring.minWindow("a", "aa"));
    }
}
