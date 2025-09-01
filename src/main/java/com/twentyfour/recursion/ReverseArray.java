package com.twentyfour.recursion;

public class ReverseArray {
    public void reverseUtil(int[] arr, int i, int len){
        if(i == len) return;

        int ele = arr[i];

        reverseUtil(arr, i+1, len);

        arr[len - i - 1] = ele;
    }

    public int[] reverse(int[] arr){
        reverseUtil(arr, 0, arr.length);
        return arr;
    }

    public static void main(String[] args) {
        ReverseArray reverseArray = new ReverseArray();
        int[] arr = {3,1,7,9,5};

        reverseArray.reverse(arr);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
    }
}
