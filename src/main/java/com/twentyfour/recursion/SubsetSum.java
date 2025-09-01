package com.twentyfour.recursion;

import java.util.ArrayList;

public class SubsetSum {

     void subsetSumUtil(ArrayList<Integer> arr, int n, ArrayList<Integer> res, int sum, int i){
        if(i >= n){
            res.add(sum);
            return;
        }

        subsetSumUtil(arr, n, res, sum+arr.get(i), i+1);
        subsetSumUtil(arr, n, res, sum, i+1);
    }

    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> res = new ArrayList<>();
        subsetSumUtil(arr, n, res, 0, 0);
        return res;
    }

    public static void main(String[] args) {
        SubsetSum subsetSum = new SubsetSum();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(2);
        arr.add(3);
        arr.add(4);
        System.out.println(subsetSum.subsetSums(arr, 3));
    }
}
