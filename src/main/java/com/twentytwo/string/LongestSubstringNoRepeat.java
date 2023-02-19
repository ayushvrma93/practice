package com.twentytwo.string;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringNoRepeat {

    public int get(String str){

        int maxLength = 1;

        String currLongestString = "";

        for(char ch : str.toCharArray()){

            String chStr = String.valueOf(ch);

            if(currLongestString.contains(chStr)){
                currLongestString = currLongestString.substring(currLongestString.indexOf(ch) + 1);
            }

            currLongestString = currLongestString + chStr;
            maxLength = Math.max(currLongestString.length(), maxLength);
        }

        return maxLength;
    }

    public int byON(String str){

        int currLen = 0;
        int maxLen = 1;
        int length = str.length();

        Map<Character, Integer> chars = new HashMap<>();

        for(int i=0; i<length; i++){

            char curr = str.charAt(i);

            if(!chars.containsKey(curr)){
                chars.put(curr, i);
                currLen++;
            }

            else {
                currLen = i - chars.get(curr);
                chars.put(curr, i);
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }

    public static void main(String[] args) {

        LongestSubstringNoRepeat longestSubstringNoRepeat = new LongestSubstringNoRepeat();

        int res = longestSubstringNoRepeat.get("abcabcbb");
        int res1 = longestSubstringNoRepeat.get("bbbbb");
        int res2 = longestSubstringNoRepeat.get("pwwkew");
        int res3 = longestSubstringNoRepeat.get("aab");

        int res4 = longestSubstringNoRepeat.byON("abcabcbb");
        int res5 = longestSubstringNoRepeat.byON("bbbbb");
        int res6 = longestSubstringNoRepeat.byON("pwwkew");
        int res7 = longestSubstringNoRepeat.byON("aab");

        System.out.println(res);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println("By O(N) now: ");
        System.out.println(res4);
        System.out.println(res5);
        System.out.println(res6);
        System.out.println(res7);
    }
}
