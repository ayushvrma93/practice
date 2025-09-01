package com.twentyfour.slidingwindow;

/*
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/?envType=study-plan-v2&envId=leetcode-75
 */
public class MaxLengthOfOne {

    public int longestSubarray(int[] nums) {
//        int zc=1;
//        int n = nums.length;
//        int max=0;
//        int len=0;
//
//        int l=0, r=0;
//
//        while(r<n){
//            while(r<n && zc >0 && nums[r] ==0){
//                zc--;
//                r++;
//            }
//
//            while(r<n && l<=r && nums[l] ==0 && zc==0){
//                zc++;
//                l++;
//            }
//
//            while(r<n && nums[r] == 1){
//                len++;
//                max=Math.max(max, len);
//                r++;
//            }
//        }
//        if(max==n) return max-1;
//        return max;

        int zc = 0;  // Zero count, tracking how many zeros are in the current window
        int n = nums.length;
        int max = 0; // Max length of the subarray
        int l = 0;   // Left pointer

        for (int r = 0; r < n; r++) { // Right pointer moves through the array
            if (nums[r] == 0) { // If a zero is found, increment zero count
                zc++;
            }

            // If more than one zero is found, shift the left pointer to reduce zeros
            while (zc > 1) {
                if (nums[l] == 0) {
                    zc--; // Reduce zero count as we slide past a zero
                }
                l++; // Slide the left pointer to the right
            }

            // Calculate max as r-l because we want the max length of 1s subarray after possibly removing a zero
            max = Math.max(max, r - l);
        }

        // Since we are allowed to remove at most one zero, max could be length of the whole array minus 1 if the whole array is 1s
        return max;
    }


    public static void main(String[] args) {
        MaxLengthOfOne maxLengthOfOne = new MaxLengthOfOne();
        int[] arr1 = {1,1,1};
        int[] arr2 = {0,1,1,1,0,1,1,0,1};
        int[] arr3 = {1,1,0,1};
        System.out.println(maxLengthOfOne.longestSubarray(arr1));
        System.out.println(maxLengthOfOne.longestSubarray(arr2));
        System.out.println(maxLengthOfOne.longestSubarray(arr3));
    }
}
