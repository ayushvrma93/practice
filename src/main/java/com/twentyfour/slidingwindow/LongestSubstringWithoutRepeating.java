package com.twentyfour.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeating {

    public int getLongestSubstringLength(String str){
        if(str == null || str.isEmpty()) return 0;

        Map<Character, Integer> pos = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int start = 0;
        int currLen = 0;

        for(int i=0; i<str.length(); i++){
            Character curr = str.charAt(i);

            if(pos.containsKey(str.charAt(i))){
                start = Math.max(start, pos.get(curr) + 1);
                currLen = i - start + 1;
            } else {
                currLen++;
            }
            max = Math.max(max, currLen);
            pos.put(curr, i);
        }

        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating longest = new LongestSubstringWithoutRepeating();
        String str1 = "abcabcbb";
        String str2 = "bbbbb";
        String str3 = "pwwkew";
        System.out.println(longest.getLongestSubstringLength(str1));
        System.out.println(longest.getLongestSubstringLength(str2));
        System.out.println(longest.getLongestSubstringLength(str3));
    }
}
