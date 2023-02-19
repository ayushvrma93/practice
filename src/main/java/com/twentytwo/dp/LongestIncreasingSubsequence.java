package com.twentytwo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

    public int getLength(int[] arr, int size, int currMax){

        if(size == 0) return 0;

        if(arr[size-1] >= currMax) return getLength(arr, size-1, currMax);

        return Math.max(getLength(arr, size-1, arr[size-1]) +1, getLength(arr, size-1, currMax));
    }


    //TODO: Wrong implementation, need to work on it.
    private int byMemoizationUtil(int[] arr, int size, int currMax, int[] lis){

        if(size == 0) return 0;

        if(lis[size] != -1)
            return lis[size];

        if(arr[size-1] >= currMax) return lis[size] = byMemoizationUtil(arr, size-1, currMax, lis);

        return lis[size] = Math.max(byMemoizationUtil(arr, size-1, arr[size-1], lis) +1, byMemoizationUtil(arr, size-1, currMax, lis));
    }

    public int byMemoization(int[] arr){

        int size = arr.length;
        int currMax = Integer.MAX_VALUE;
        int[] lis = new int[size + 1];
        Arrays.fill(lis, 1);

        int lisSize = byMemoizationUtil(arr, size, currMax, lis);
        return lisSize;
    }


    public List<Integer> byLoop(int[] arr){

        int result = 1;
        List<Integer> finalSequence = new ArrayList<>();
        finalSequence.add(arr[0]);

        for(int i = 0; i < arr.length; i++){
            List<Integer> sequence = new ArrayList<>();
            sequence.add(arr[i]);
            int currResult = 1;
            int currMax = arr[i];
            for(int j = i+1; j < arr.length; j++){
                if(arr[j] > currMax){
                    currResult++;
                    currMax = arr[j];
                    sequence.add(arr[j]);
                }
            }
            if(currResult > result) {
                result = currResult;
                finalSequence = sequence;
            }
        }
        return finalSequence;
    }

    public static void main(String[] args) {

        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();

        int[] arr1 = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        int[] arr2 = {10, 9, 8, 7, 6, 5};
        int[] arr3 = { 10, 22, 9, 33, 21, 50, 41, 60 };

        System.out.println(longestIncreasingSubsequence.getLength(arr1, arr1.length, Integer.MAX_VALUE));
        System.out.println("by memoization: " + longestIncreasingSubsequence.byMemoization(arr1));
        System.out.println(longestIncreasingSubsequence.getLength(arr2, arr2.length, Integer.MAX_VALUE));
        System.out.println(longestIncreasingSubsequence.getLength(arr2, arr2.length, Integer.MAX_VALUE));
        System.out.println("by loop: " + longestIncreasingSubsequence.byLoop(arr1));
        System.out.println("by loop: " + longestIncreasingSubsequence.byLoop(arr2));
        System.out.println("by loop: " + longestIncreasingSubsequence.byLoop(arr3));
    }
}
