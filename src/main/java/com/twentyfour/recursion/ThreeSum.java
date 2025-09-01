package com.twentyfour.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> getTriplets(int[] arr){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);

        for(int i=0; i<arr.length-1;i++){
            if(i==0 ||arr[i] != arr[i-1]){
                twoSum(arr, i, result);
            }
        }
        return result;
    }

    public void twoSum(int[] arr, int i, List<List<Integer>> result){
        int low = i+1;
        int high = arr.length-1;

        while(low < high){
            if(arr[low] + arr[i] + arr[high] == 0){
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[low]);
                temp.add(arr[i]);
                temp.add(arr[high]);
                result.add(temp);
                low++;
            }

            while(low<high && arr[low] == arr[low-1]){
                low++;
            }

            if(arr[low] + arr[i] + arr[high] < 0){
                low++;
            }

            else{
                high--;
            }
        }
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] arr1 = {-1,0,1,2,-1,-4};
        int[] arr2 = {0,1,1};
        int[] arr3 = {0,0,0,0};
        int[] arr4 = {2,2,-4, 0};
        System.out.println(threeSum.getTriplets(arr1));
        System.out.println(threeSum.getTriplets(arr2));
        System.out.println(threeSum.getTriplets(arr3));
        System.out.println(threeSum.getTriplets(arr4));
    }
}
