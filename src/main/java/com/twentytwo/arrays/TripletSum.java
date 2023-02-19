package com.twentytwo.arrays;

import java.util.*;

public class TripletSum {

    public List<List<Integer>> getTriplet(int[] a, int sum){

        List<List<Integer>> finalResult = new ArrayList<>();

        for(int i = 0; i < a.length - 1; i++){

            Set storedNums = new HashSet();
            int sumReq = sum - a[i];

            for(int j = i+1; j < a.length; j++){

                if(storedNums.contains(sumReq - a[j])){
                    List<Integer> result = Arrays.asList(a[i], a[j], sum - a[i] - a[j]);
                    finalResult.add(result);
                }
                storedNums.add(a[j]);
            }
        }
        return finalResult;
    }


    public static void main(String[] args) {

        TripletSum tripletSum = new TripletSum();
        int[] arr1 = { 1, 4, 45, 6, 10, 8 };
        int sum1 = 22;

        System.out.println(tripletSum.getTriplet(arr1, sum1));
    }
}
