package com.twentyfour.practice;

import java.util.PriorityQueue;

public class KthLargestInArray {

    public int get(int[] arr, int k){
        PriorityQueue<Integer> minpq = new PriorityQueue<>();

        for(int i=0; i< k; i++){
            minpq.add(arr[i]);
        }

        for(int i=k; i< arr.length; i++){
            int peek = minpq.peek();

            if(arr[i] > peek){
                minpq.remove();
                minpq.add(arr[i]);
            }
        }

        return minpq.peek();
    }

    public static void main(String[] args) {
        KthLargestInArray largestInArray = new KthLargestInArray();
        int[] arr1 = {3,2,1,5,6,4};
        int[] arr2 = {3,2,3,1,2,4,5,5,6};
        System.out.println(largestInArray.get(arr1, 2));
        System.out.println(largestInArray.get(arr2, 4));
    }
}
