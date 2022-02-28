package com.piramal;

public class SubArray {

    /*
    find the max sub array with equal no of 0s and 1s

    e.g- 0 1 0 0 1 0 0 1
     */

    public int getLength(int[] arr){

        int maxLen = 0;

        for(int i=0; i<arr.length -1; i++){

            int zeroCount = 0;
            int oneCount = 0;

            int currLen = 0;

            int iVal = arr[i];
            if(iVal == 0) { zeroCount++; }
            else oneCount++;

            for(int j =i+1; j< arr.length; j++){

                if(arr[j] == 0) { zeroCount++; }
                else oneCount++;

                if(oneCount == zeroCount){
                    currLen = j-i+1;
                }

                maxLen = Math.max(maxLen, currLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {

        SubArray subArray = new SubArray();
        System.out.println(subArray);
    }
}
