package com.twentythree.jio;

import java.util.HashMap;
import java.util.Map;

public class SmallestStringContainingPattern {

    public String getShortestSubstring(String str, String pattern){

        int len1 = str.length();
        int len2 = pattern.length();
        int count=0;

        if(len1 < len2){
            return null;
        }

        int[] str_arr = new int[256];
        int[] pat_arr = new int[256];

        for(int i=0; i<len2; i++){
            pat_arr[pattern.charAt(i)]++;
        }

        int start=0, startIndex=-1;
        int min_len = Integer.MAX_VALUE;

        for(int j=0; j<str.length(); j++){
            str_arr[str.charAt(j)]++;
        }

        for(int i=0; i<len1;i++){

            if(pat_arr[i] <= str_arr[i]){
                count++;
            }

            if(count == len2){

                while(str_arr[str.charAt(start)] > pat_arr[str.charAt(start)] || pat_arr[str.charAt(start)] ==0){
                    if(str_arr[str.charAt(start)] > str_arr[str.charAt(start)]){
                        str_arr[str.charAt(start)]--;
                    }
                    start++;
                }
            }

            int currLen = i-start+1;

            if(min_len < currLen){
                min_len = currLen;
                startIndex = start;
            }
        }

        return str.substring(startIndex, startIndex + min_len);
    }

    public String own(String str, String pat){

        int[] strArr = new int[256];
        int count = pat.length();
        int maxLen = Integer.MIN_VALUE;
        int start = 0;

        for(int i=0; i<str.length(); i++){
            strArr[str.charAt(i)]++;
        }

        Map<Character, Integer> map = populateMap(pat);

        int i=0;

        for(int j=0; j< str.length(); j++){

            Character key = (char) j;
            int occ = strArr[j];

            if(occ > 0 && map.containsKey(key)){
                int rep = map.get(key);
                rep--;
                map.put(key, rep);
                if(rep ==0) count--;
            }

            if(count == 0){

                int len = j-i+1;

                if(len > maxLen){
                    maxLen = len;
                    start = i;
                }

                while(i < j && !map.containsKey(str.charAt(i))){
                    i++;
                }

                if(map.containsKey(str.charAt(i))){
                    Integer c = map.get(key);
                    c--;
                    map.put(key, c);
                    count++;
                    start = i+1;
                }
            }
        }

        return str.substring(start, start+maxLen);
    }

    private Map<Character, Integer> populateMap(String pat) {

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i< pat.length(); i++){

            Character curr = pat.charAt(i);
            if(map.containsKey(curr)) {
                Integer val = map.get(curr);
                val++;
                map.put(curr, val);
            }

            else{
                map.put(curr, 1);
            }
        }
        return map;
    }

    public static void main(String[] args) {

        SmallestStringContainingPattern substring = new SmallestStringContainingPattern();
        String str = "this meeting is in progress";
        String pat = "s gn";
        //System.out.println(substring.getShortestSubstring(str, pat));
        System.out.println(substring.own(str, pat));
    }

}
