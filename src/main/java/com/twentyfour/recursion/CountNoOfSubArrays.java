package com.twentyfour.recursion;

public class CountNoOfSubArrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int i = 0,j = 0,count = 0;
        int res = 0;

        while(i < nums.length) {
            if (nums[i] % 2 == 1) {
                count++;
            }

            if (count == k) {
                res++;
            }
            while (j < i && count >= k) {
                if (nums[j] % 2 == 1) {
                    count--;
                    if (count == k) {
                        res++;
                    }
                }
                else {
                    res++;
                }
                j++;
            }
            i++;
        }

//        while (j < nums.length) {
//            if (nums[j] % 2 == 0) {
//                res++;
//            }
//            else {
//                count--;
//            }
//            if (count == k) {
//                res++;
//            }
//            j++;
//        }
        return res;
    }

    public static void main(String[] args) {
        CountNoOfSubArrays countNoOfSubArrays = new CountNoOfSubArrays();
        int[] nums = {1,1,2,1,1};
        int[] nums2 = {2,4,6};
        int[] nums3 = {2,2,2,1,2,2,1,2,2,2};
        System.out.println(countNoOfSubArrays.numberOfSubarrays(nums, 3));
        System.out.println(countNoOfSubArrays.numberOfSubarrays(nums2, 1));
        System.out.println(countNoOfSubArrays.numberOfSubarrays(nums3, 2));
    }
}