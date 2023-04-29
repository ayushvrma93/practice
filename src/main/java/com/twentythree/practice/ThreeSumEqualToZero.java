package com.twentythree.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumEqualToZero {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> results = new ArrayList<>();
        int len = nums.length;

        for(int i=0; i<len-2; i++){
            if(i==0 || nums[i] != nums[i-1] && nums[i] <=0)
                twoSum(nums, i, results);
        }
        return results;
    }

    private void twoSum(int[] nums, int i, List<List<Integer>> result){

        int low = i+1;
        int high = nums.length-1;

        while(low < high){

            var sum = nums[i] + nums[low] + nums[high];

            if(sum == 0){

                List<Integer> currRes = new ArrayList<>();
                currRes.add(nums[i]);
                currRes.add(nums[low]);
                currRes.add(nums[high]);
                result.add(currRes);

                low++;
                high--;

                while(low < high && nums[low] == nums[low-1]){
                    low++;
                }
            }

            else if(sum < 0){
                low++;
            }

            else high--;
        }
    }

    public static void main(String[] args) {
        ThreeSumEqualToZero threeSumEqualToZero = new ThreeSumEqualToZero();
        System.out.println(threeSumEqualToZero.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
