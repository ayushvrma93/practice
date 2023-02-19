package com.twentytwo.rudderstack;

public class DoesNumExist {

    public int getIndex(int[] arr, int key){
        return getIndexUtil(arr, key, 0, arr.length-1);
    }

    private int getIndexUtil(int[] arr, int key, int low, int high){

        if(low > high) return -1;

        int mid = (low + high)/2;

        if(arr[mid] == key && (((mid < high && arr[mid] != arr[mid+1]) || mid == high))) return mid;

        if (arr[mid] > key) return getIndexUtil(arr, key, low, mid -1);

        return getIndexUtil(arr, key, mid+1, high);
    }


    public static void main(String[] args) {

        DoesNumExist doesNumExist = new DoesNumExist();
        int[] arr = {12,12,12,12,12,12,12};
        int[] arr1 = {3,3,12,12,12,12,12,12,12,15,17};
        int[] arr2 = {12};
        int key = 3;
        System.out.println(doesNumExist.getIndex(arr2, key));
    }
}
