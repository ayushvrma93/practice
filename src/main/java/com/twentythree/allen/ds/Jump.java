package com.twentythree.allen.ds;

public class Jump {

    public boolean canJump(int[] nums) {

        int len = nums.length;

        if(len == 1) return true;


        if(nums[0] == 0) return false;

        int currMax = -1;


        for(int i=0; i<len; i++){

            currMax = Math.max(currMax, i+nums[i]);

            if(i == currMax && nums[i] == 0 && i < len-1){
                return false;
            }

            if(currMax >= len-1){
                return true;
            }
            //currMax--;
        }
        return true;
    }

    public static void main(String[] args) {
        Jump jump = new Jump();
        int[] arr1 = {1,1,2,2,0,1,1};
        System.out.print(jump.canJump(arr1));
    }
}
