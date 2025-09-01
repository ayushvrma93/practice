package com.twentyfour.recursion;

import java.util.ArrayList;

public class GenerateSubsequences {

    public void printSubsequence(int i, int n, String s, String arr[]){
        if(i == n) {
            System.out.println(s);
            return;
        }

        printSubsequence(i+1, n, s+arr[i], arr);
        printSubsequence(i+1, n, s, arr);
    }

    public void printSubsequence(int n, String s, String arr[]){
        if(n<0){
            System.out.println(s);
            return;
        }

        printSubsequence(n-1, s+arr[n], arr);
        printSubsequence(n-1, s, arr);
    }

    public void printSubsequenceWithSumK(int i, int[] arr, int k, ArrayList<Integer> res, int sum){
        if(i == arr.length){        //sum check condition inside this as pick/not-pick till end
            if(sum == k) {
                System.out.println(res);
            }
            return;
        }

        // below does not work:
//        if(i >= arr.length){
//            return;
//        }
//
//        if(sum == k){
//            System.out.println(res);
//            return;
//        }

        res.add(arr[i]);
        printSubsequenceWithSumK(i+1, arr, k, res, sum+arr[i]);

        res.remove(res.size()-1);
        printSubsequenceWithSumK(i+1, arr, k, res, sum);
    }

    public static void main(String[] args) {
        GenerateSubsequences generateSubsequences = new GenerateSubsequences();
        String arr[] = {"1", "2", "3"};
        generateSubsequences.printSubsequence(0, arr.length, "", arr);

        int[] arr2 = {1,3,2,5,6};
        ArrayList<Integer> res = new ArrayList<>();
        System.out.println("Required sum = 3:");
        generateSubsequences.printSubsequenceWithSumK(0, arr2, 3, res, 0);

        System.out.println("Subsequence are: ");
        generateSubsequences.printSubsequence(2, "", arr);

        System.out.println("Required sum = 6:");
        generateSubsequences.printSubsequenceWithSumK(0, arr2, 6, res, 0);

        int[] arr3 = {1,2,1};
        System.out.println("Required sum = 2:");
        ArrayList<Integer> res1 = new ArrayList<>();
        generateSubsequences.printSubsequenceWithSumK(0, arr3, 2, res1, 0);

    }
}
