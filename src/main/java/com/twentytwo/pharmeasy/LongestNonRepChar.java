package com.twentytwo.pharmeasy;

import java.util.HashMap;
import java.util.Map;

public class LongestNonRepChar {

    /*
    str = pwswagbhighh
     */

    public int getMaxLen(String str){

        Map<Character, Integer> pos = new HashMap<>();
        int currLen = 0;
        int maxLen = 0;
        int start = 0;
        int end;

        int maxStart = 0;
        int maxEnd = 0;

        for(int i=0; i<str.length(); i++){

            Character currChar = str.charAt(i);

            if(!pos.containsKey(currChar)){
                currLen++;
            }

            else {
                currLen = i - pos.get(currChar);
                start = pos.get(currChar)+1;

            }
            pos.put(currChar, i);
            end = i;

            if(currLen > maxLen) {
                maxLen = currLen;
                maxStart = start;
                maxEnd = end;
            }
        }
        System.out.println(str.substring(maxStart, maxEnd+1));
        return maxLen;
    }


    public int getMaxLenCorrect(String str){

        Map<Character, Integer> pos = new HashMap<>();
        int start = 0;
        int maxLen = 0;

        for(int i=0; i<str.length(); i++){

            Character curr = str.charAt(i);

            if(pos.containsKey(curr)){
                start = Math.max(start, pos.get(curr) + 1);
            }

            pos.put(curr, i);
            maxLen = Math.max(maxLen, i-start+1);
        }
        return maxLen;
    }

    public static void main(String[] args) {

        LongestNonRepChar longestNonRepChar = new LongestNonRepChar();
        System.out.println(longestNonRepChar.getMaxLenCorrect("pwswagwbhighh"));
        System.out.println(longestNonRepChar.getMaxLenCorrect("ayushverma"));
        System.out.println(longestNonRepChar.getMaxLenCorrect("wwwww"));
        System.out.println(longestNonRepChar.getMaxLenCorrect("letstrybinarytreeproblem"));
    }
}
