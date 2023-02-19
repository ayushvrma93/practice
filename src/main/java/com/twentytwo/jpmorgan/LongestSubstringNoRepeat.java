package com.twentytwo.jpmorgan;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringNoRepeat {

    public int getLongestSubstringWithout(String str){

        Map<Character, Integer> occurrence = new HashMap<>();
        int currLength = 0;
        int maxLength = 0;

        char[] chars = str.toCharArray();

        for(int i=0; i<chars.length; i++){

            if(!occurrence.containsKey(chars[i])){
                currLength++;
            }

            else{
                currLength = i - occurrence.get(chars[i]);
            }
            occurrence.put(chars[i], i);
            maxLength = Math.max(maxLength, currLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {

        LongestSubstringNoRepeat longestSubstringNoRepeat = new LongestSubstringNoRepeat();

        String testCase1 = "abcabcbb";
        String testCase2 = "aaaaa";
        String testCase3 = "abcdef";
        String testCase4 = "pwwkew";

        System.out.println(longestSubstringNoRepeat.getLongestSubstringWithout(testCase1));
        System.out.println(longestSubstringNoRepeat.getLongestSubstringWithout(testCase2));
        System.out.println(longestSubstringNoRepeat.getLongestSubstringWithout(testCase3));
        System.out.println(longestSubstringNoRepeat.getLongestSubstringWithout(testCase4));
    }
}
