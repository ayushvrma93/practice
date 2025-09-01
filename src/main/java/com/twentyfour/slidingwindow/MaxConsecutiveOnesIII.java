package com.twentyfour.slidingwindow;

public class MaxConsecutiveOnesIII {

    public int longestOnes(int[] nums, int k) {
        int i=0,j=0,len=0, max=0, count=0;
        int n = nums.length;
        int zc = k;

        while(i<n && j<n){
            while(j<n && nums[j] == 1){
                count = j-i+1;
                max = Math.max(max, count);
                j++;
            }

            while(j<n && nums[j] == 0 && zc > 0){
                count = j-i+1;
                max = Math.max(max, count);
                zc--;
                j++;
            }

            while(j<n && nums[j] == 1){
                count = j-i+1;
                max = Math.max(max, count);
                j++;
            }

            while(i<n && i<j && nums[i] == 1){
                i++;
            }

            while(i<n && i<=j && nums[i] == 0 && zc==0){
                zc++;
                i++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesIII maxConsecutiveOnesIII = new MaxConsecutiveOnesIII();
        int[] arr1 = {0,0,0,1};
        int[] arr2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int[] arr3 = {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(maxConsecutiveOnesIII.longestOnes(arr1, 4));
        System.out.println(maxConsecutiveOnesIII.longestOnes(arr2, 3));
        System.out.println(maxConsecutiveOnesIII.longestOnes(arr3, 2));
    }
}
