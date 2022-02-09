package com.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringOfKUniqueChars {

    public int get(String str, int k){

        char[] chars = str.toCharArray();
        Map<Character, Integer> freq = new HashMap<>();
        int longestSubstringSize = -1;

        int i = 0, j = 0;

        while(j < chars.length){

            if (!freq.containsKey(chars[j])) {
                freq.put(chars[j], 1);
            } else {
                Integer occurrence = freq.get(chars[j]);
                occurrence++;
                freq.put(chars[j], occurrence);
            }

            if (freq.size() < k) j++;

            else if (freq.size() == k) {
                longestSubstringSize = Math.max(longestSubstringSize, j - i + 1);
                j++;
            }

            else if(freq.size() > k) {

                while (i < chars.length && freq.size() > k) {

                    if(freq.containsKey(chars[i])) {
                        Integer occurrence = freq.get(chars[i]);
                        occurrence--;
                        if (occurrence == 0) freq.remove(chars[i]);
                        else freq.put(chars[i], occurrence);
                    }
                    i++;
                }
                j++;
            }
        }
        return longestSubstringSize;
    }


    public static void main(String[] args) {

        LongestSubstringOfKUniqueChars longestSubstringOfKUniqueChars = new LongestSubstringOfKUniqueChars();

        String str1 = "aabacbebebe";
        String str2 = "hq";
        String str3 = "abcdefgh";
        String str4 = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
        System.out.println(longestSubstringOfKUniqueChars.get(str1, 3));
        System.out.println(longestSubstringOfKUniqueChars.get(str2, 2));
        System.out.println(longestSubstringOfKUniqueChars.get(str2, 3));
        System.out.println(longestSubstringOfKUniqueChars.get(str3, 3));
        System.out.println(longestSubstringOfKUniqueChars.get(str4, 5));
    }
}
