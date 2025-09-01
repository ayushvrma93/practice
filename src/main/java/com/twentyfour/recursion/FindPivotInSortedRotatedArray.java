package com.twentyfour.recursion;

public class FindPivotInSortedRotatedArray {
    public int findMin(int[] nums) {
        int n = nums.length - 1;
        int index = findMinUtil(nums, 0, n);
        return nums[index];
    }

    public int findMinUtil(int[] nums, int low, int high){
        if(high-low == 0){
            return low;
        }

        if(high-low == 1){
            return nums[low]>nums[high] ? high:low;
        }

        if(low > high){
            return -1;
        }

        if(high == low){
            return low;
        }

        int mid = (low + high)/2;

        if(mid > low && nums[mid] < nums[mid-1] && mid < high && nums[mid] < nums[mid+1]){
            return mid;
        }

        if(nums[high] < nums[low]){
            return findMinUtil(nums, mid+1, high);
        }

        return findMinUtil(nums, low, mid-1);
    }

    public static void main(String[] args) {
        FindPivotInSortedRotatedArray findPivotInSortedRotatedArray = new FindPivotInSortedRotatedArray();
        int[] nums = {4,5,6,7,0,1,2};
        int[] nums1 = {0,1,2,4,5,6,7};
        int[] nums2 = {1,2,3,4,5,-1};
        int[] nums3 = {11,13,15,17};
        int[] nums4 = {3,4,5,1,2};
        System.out.println(findPivotInSortedRotatedArray.findMin(nums));
        System.out.println(findPivotInSortedRotatedArray.findMin(nums1));
        System.out.println(findPivotInSortedRotatedArray.findMin(nums2));
        System.out.println(findPivotInSortedRotatedArray.findMin(nums3));
        System.out.println(findPivotInSortedRotatedArray.findMin(nums4));
    }
}
