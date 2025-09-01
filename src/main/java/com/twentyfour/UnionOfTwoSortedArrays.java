package com.twentyfour;

import java.util.ArrayList;

public class UnionOfTwoSortedArrays {
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m)
    {
        // add your code here
        int i=0, j=0;
        // int n = arr1.length;
        // int m = arr2.langth;

        ArrayList<Integer> result = new ArrayList<>();

        while(i<n && j<m){

            while(i<n && i>0 && arr1[i] == arr1[i-1]){
                i++;
            }

            while(j<m && j>0 && arr2[j] == arr2[j-1]){
                j++;
            }

            if(i<n && j<m && arr1[i] == arr2[j]){
                result.add(arr1[i]);
                i++;
                j++;
            }
            else if (i<n && j<m && arr1[i] < arr2[j]){
                result.add(arr1[i]);
                i++;
            } else if(i<n && j<m && arr1[i] > arr2[j]){
                result.add(arr2[j]);
                j++;
            }
        }

        while(i<n && i>0){
            if(arr1[i] == arr2[m-1] || arr1[i] == arr1[i-1]){
                i++;
                continue;
            }
            result.add(arr1[i]);
            i++;
        }

        while(j<m && j>0){
            if(arr2[j] == arr1[n-1] || arr2[j] == arr2[j-1]){
                j++;
                continue;
            }
            result.add(arr2[j]);
            j++;
        }

        return result;
    }

    public static void main(String[] args) {
        UnionOfTwoSortedArrays unionOfTwoSortedArrays = new UnionOfTwoSortedArrays();
        int[] arr1 = {-8, -3, -3, -2, 0, 1, 2, 2, 6};
        int[] arr2 = {-9, -9, 0};
        ArrayList<Integer> list1 = findUnion(arr1, arr2, 9, 3);
        System.out.println(list1);
    }
}
