package com.twentyfour;

public class MinLengthSubArrayOfSumK {

    public int minSubArrayLen(int target, int[] nums) {
        int sum=0;
        int min = (int) (10e9);

        int n = nums.length;
        int i=0, j=0;

        while(j<n && sum<target){
            sum+=nums[j];

            if(sum==target){
                min = j-i+1;
            }
            j++;
        }

        while(j<n){
            while(i<j && sum > target){
                sum-=nums[i];
                i++;
            }

            if(sum == target){
                min = Math.min(min, j-i+1);
            }

            while(j<n && sum <= target){
                sum+=nums[j];
                j++;
            }
        }
        return min == (int) (10e9) ? 0 : min;
    }

    public static void main(String[] args) {
        MinLengthSubArrayOfSumK minLength = new MinLengthSubArrayOfSumK();
        int[] arr1 = {2,3,1,2,4,3};
        System.out.println(minLength.minSubArrayLen(7, arr1));
    }
}
