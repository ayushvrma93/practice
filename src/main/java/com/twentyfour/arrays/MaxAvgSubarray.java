package com.twentyfour.arrays;

public class MaxAvgSubarray {
    public double findMaxAverage(int[] nums, int k) {
        double max = -Double.MAX_VALUE;
        int n = nums.length;
        int i=0,j=0;
        int sum=0;

        while(j<n && j<k){
            sum+=nums[j];
            j++;
        }

        double avg = (double)sum/k;
        max = avg;

        while(j<n){
            sum+= nums[j] - nums[i];
            avg = (double)sum/k;
            if(max < avg){
                max = avg;
            }
            i++;
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        MaxAvgSubarray sub = new MaxAvgSubarray();
        int[] arr1 = {1,12,-5,-6,50,3};
        System.out.println(sub.findMaxAverage(arr1, 4));
    }
}
