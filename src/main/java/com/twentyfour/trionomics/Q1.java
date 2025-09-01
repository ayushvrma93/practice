package com.twentyfour.trionomics;


import java.util.Arrays;

/*
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

Example 1:
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:
Input: nums = [3,4,-1,1] -> -1, 0, 1
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.


Constraints:
1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
 */
public class  Q1{

    public int getMinimumMissing(int[] arr){
        Arrays.sort(arr);
        int max = (int) -1e10;
        int minPos = (int) 1e10;
        int maxNegative = (int) -1e10;
        boolean flag = true;

        for(int i=0; i<arr.length-1; i++){
            if(arr[i] < 0){
                maxNegative = Math.max(maxNegative, arr[i]);
            } else if(arr[i] > 0 && flag && maxNegative > (int) -1e10){
                flag = false;
                minPos = Math.min(minPos, arr[i]);
                if(minPos > 1 && maxNegative <= 0){
                    return 1;
                }
            }
            else {
                max = Math.max(max, arr[i]);
                int diff = arr[i+1] - arr[i];
                if(diff > 1){
                    return arr[i] + 1;
                }
            }
        }
        if(max <= 0) return 1;
        return max+1;
    }

    public static void main(String[] args) {
        Q1 q1 = new Q1();
        int[] arr = {3,4,-1,1};
        int[] arr1 = {-2, -1, 0};
        int[] arr2 = {-2, -1, 0};
        int[] arr3 = {-2, -1, 2};

        System.out.println(q1.getMinimumMissing(arr));
        System.out.println(q1.getMinimumMissing(arr1));
        System.out.println(q1.getMinimumMissing(arr2));
        System.out.println(q1.getMinimumMissing(arr3));

    }
}
