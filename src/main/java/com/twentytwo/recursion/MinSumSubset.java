package com.twentytwo.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinSumSubset {

    public Set getSubsetsUtil(int[] arr, HashSet set){

        int size = arr.length;

        for(int i=0; i<size; i++){

            for(int j=i;j<size;j++){
                List<Integer> temp = new ArrayList<>();
                for(int k=i;k<=j;k++){
                    temp.add(arr[k]);
                }
                set.add(temp);
            }
        }
        return set;
    }

    public static void main(String[] args) {

        MinSumSubset minSumSubset = new MinSumSubset();

        int[] arr1 = {1,2,3};
        int[] arr2 = {1,2,3,2};
        System.out.println(minSumSubset.getSubsetsUtil(arr1, new HashSet()));
        System.out.println(minSumSubset.getSubsetsUtil(arr2, new HashSet()));
    }
}
