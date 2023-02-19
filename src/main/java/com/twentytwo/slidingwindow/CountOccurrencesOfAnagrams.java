package com.twentytwo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class CountOccurrencesOfAnagrams {

    public int count(String str, String pattern){

        if(pattern.length() > str.length()) return -1;

        char[] strArr = str.toCharArray();
        char[] patternArr = pattern.toCharArray();
        int count = 0;

        Map<Character, Integer> freq = new HashMap<>();

        for(Character ch : patternArr){
            freq = incrementCharacter(freq, ch);
        }

        int i = 0;
        int j = 0;

        while(j < patternArr.length){
            decrementCharacter(freq, strArr[j]);
            j++;
        }

        if(areAllZero(freq)) count++;

        while(j < strArr.length){
            freq = incrementIfPresent(freq, strArr[i]);
            freq = decrementCharacter(freq, strArr[j]);
            if(areAllZero(freq)) count++;
            i++;
            j++;
        }
        return count;
    }

    private Map<Character, Integer> incrementCharacter(Map<Character, Integer> freq, Character ch){

        if(!freq.containsKey(ch)){
            freq.put(ch, 1);
        }

        else{
            Integer occurrence = freq.get(ch);
            occurrence++;
            freq.put(ch, occurrence);
        }
        return freq;
    }

    private Map<Character, Integer> incrementIfPresent(Map<Character, Integer> freq, Character ch){

        if(freq.containsKey(ch)){
            Integer occurrence = freq.get(ch);
            occurrence++;
            freq.put(ch, occurrence);
        }
        return freq;
    }


    private Map<Character, Integer> decrementCharacter(Map<Character, Integer> freq, Character ch){

        if(freq.containsKey(ch)){
            Integer occurrence = freq.get(ch);
            occurrence--;
            freq.put(ch, occurrence);
        }
        return freq;
    }

    private boolean areAllZero(Map<Character, Integer> freq){

        for(Map.Entry<Character, Integer> entry : freq.entrySet()){
            if(entry.getValue() != 0)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {

        CountOccurrencesOfAnagrams countOccurrencesOfAnagrams = new CountOccurrencesOfAnagrams();
        String str1 = "amnacdname";
        String pat1 = "man";

        System.out.println("occurrence of " + pat1 + "= " + countOccurrencesOfAnagrams.count(str1, pat1));
    }
}
