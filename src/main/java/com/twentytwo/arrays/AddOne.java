package com.twentytwo.arrays;

import com.twentytwo.Utility;

public class AddOne {

    public int[] add(int[] arr){

        if((arr.length==1 && arr[0]==0) || arr.length == 0){
            int result[] = new int[1];
            result[0]=1;
            return result;
        }

        int size = arr.length;
        int[] result = new int[size + 1];

        int lastSum = arr[size - 1] + 1;
        int carry = lastSum/10;
        result[size] = lastSum % 10;

        for(int i = size - 2; i >= 0; i--){
            int currSum = arr[i] + carry;
            result[i+1] = currSum % 10;
            carry = currSum / 10;
        }

        result[0] = carry;

        return result;
    }

    public static void main(String[] args) {

        AddOne addOne = new AddOne();

        int arr1[] = {9,9,9};
        int arr2[] = {9,5,9};

        Utility.printIntArray(addOne.add(arr1));
        Utility.printIntArray(addOne.add(arr2));
    }
}
