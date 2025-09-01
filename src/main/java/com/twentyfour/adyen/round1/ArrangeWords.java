package com.twentyfour.adyen.round1;

import java.util.*;
import java.util.stream.Collectors;

public class ArrangeWords {
    public static String arrange(String sentence) {
        // Write your code here
        int n = sentence.length();
        String withoutFullStop = sentence.substring(0, n-1);

        //split on space
        String[] strArr = withoutFullStop.split(" ");

        strArr[0] = strArr[0].toLowerCase();

        Comparator comp = new MyComparator();

        Arrays.sort(strArr, comp);

        String first = strArr[0];

        String firstChar = first.substring(0, 1);
        String rest = first.substring(1);
        firstChar = firstChar.toUpperCase();

        String concatenated = firstChar + rest;

        strArr[0] = concatenated;

        StringBuilder result = new StringBuilder();

        for(String str : strArr ){
            result.append(str);
            result.append(" ");
        }

        result.setCharAt(result.length()-1, '.');

        return result.toString();
    }


    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b){
            int n = a.length();
            int m = b.length();

            if(n==m){
                return 0;
            }

            return n-m;
        }
    }

    public String arrangeOptimised(String sentence){
        String withoutFullStop = sentence.substring(0, sentence.length()-1);
        StringBuilder sb = new StringBuilder();

        String[] splitted = withoutFullStop.split(" ");
        Map<Integer, List<String>> lengthToWordMap = new HashMap<>();
        int maxLength = Integer.MIN_VALUE;

        for(String word : splitted){
            int len = word.length();
            lengthToWordMap.computeIfAbsent(len, k -> new ArrayList<>()).add(word);
            maxLength = Math.max(maxLength, len);
        }

        for(int i=1; i<=maxLength; i++){
            if(lengthToWordMap.containsKey(i)){
                List<String> list = lengthToWordMap.get(i);
                sb.append(list.stream().collect(Collectors.joining(" ")));
                sb.append(" ");
            }
        }
        sb.replace(sb.length()-1, sb.length(), ".");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrangeWords arrangeWords = new ArrangeWords();
        System.out.println(arrangeWords.arrangeOptimised("my name pikachu is ayush verma."));
    }
}
