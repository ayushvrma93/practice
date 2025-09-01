package com.twentyfour.binarysearch;

import com.twentytwo.Utility;

public class FirstAndLastOccurrence {

    public int[] searchRange(int[] nums, int target){
        int[] res = {-1, -1};

        int first = findFirst(nums, target);
        int last = findLast(nums, target);

        res[0] = first;
        res[1] = last;

        return res;
    }

    public int findFirst(int[] nums, int target){
        int l = 0;
        int h = nums.length-1;
        int index = -1;

        while(l <= h){
            int mid = (l+h)/2;
            if(nums[mid] == target){
                index = mid;
                h = mid -1;
            }

            else if(nums[mid] < target){
                l = mid +1;
            }

            else{
                h = mid-1;
            }

        }
        return index;
    }

    public int findLast(int[] nums, int target){
        int l = 0;
        int h = nums.length-1;
        int index = -1;

        while(l <= h){
            int mid = (l+h)/2;
            if(nums[mid] == target){
                index = mid;
                l = mid +1;
            }

            else if(nums[mid] < target){
                l = mid +1;
            }

            else{
                h = mid-1;
            }

        }
        return index;
    }

    public static void main(String[] args) {
        FirstAndLastOccurrence occurrence = new FirstAndLastOccurrence();
        int[] nums1 = {5,7,7,8,8,10};
        int[] nums2 = {};
        int target1 = 8;
        int target2 = 6;
        int target3 = 0;

        int[] res1 = occurrence.searchRange(nums1, target1);
        int[] res2 = occurrence.searchRange(nums1, target2);
        int[] res3 = occurrence.searchRange(nums2, target3);
        Utility.printIntArray(res1);
        Utility.printIntArray(res2);
        Utility.printIntArray(res3);
    }
}
