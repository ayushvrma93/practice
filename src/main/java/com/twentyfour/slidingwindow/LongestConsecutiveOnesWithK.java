package com.twentyfour.slidingwindow;

public class LongestConsecutiveOnesWithK {

    public int get(int[] nums, int k){

        int l=0, r=0;
        int zc=0;
        int n = nums.length;
        int count=0;
        int max=0;

        while(r<n){

            while(r<n && nums[r] == 1){
                count++;
                r++;
                max = Math.max(max, count);
            }

            while(r<n && nums[r] == 0 && zc < k){
                count++;
                max = Math.max(max, count);
                zc++;
                r++;
            }

            while(l<r && zc > k){
                if(nums[l] == 0){
                    zc--;
                }
                l++;
            }

        }
        return max;
    }

    public static void main(String[] args) {
        LongestConsecutiveOnesWithK longestConsecutiveOnesWithK = new LongestConsecutiveOnesWithK();
        int[] arr1 = {1,1,0,1};
        System.out.println(longestConsecutiveOnesWithK.get(arr1, 1));

    }

}
