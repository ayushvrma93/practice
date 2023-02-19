package com.twentytwo.stacks;

import com.twentytwo.Utility;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterNumber {

    public int[] toRight(int[] arr){

        Stack<Integer> value = new Stack<>();
        Stack<Integer> pos = new Stack<>();
        int[] nge = new int[arr.length];
        Arrays.fill(nge, -1);

        value.push(arr[0]);
        pos.push(0);

        for(int i = 1; i < arr.length; i++){

            int currTop = value.peek();

            while(i< arr.length && !value.isEmpty() && currTop <= arr[i]){
                nge[pos.pop()] = arr[i];
                value.pop();
            }
            value.push(arr[i]);
            pos.push(i);
        }
        return nge;
    }

    public static void main(String[] args) {

        NextGreaterNumber nextGreaterNumber = new NextGreaterNumber();
        int[] arr1 = {11, 13, 21, 3};
        int[] arr2 = {11, 12, 13, 14};
        int[] arr3 = {11, 10, 9, 8};
        int[] result1 = nextGreaterNumber.toRight(arr1);
        int[] result2 = nextGreaterNumber.toRight(arr2);
        int[] result3 = nextGreaterNumber.toRight(arr3);
        Utility.printIntArray(result1);
        Utility.printIntArray(result2);
        Utility.printIntArray(result3);
    }
}
