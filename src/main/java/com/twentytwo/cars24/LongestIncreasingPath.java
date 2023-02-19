package com.twentytwo.cars24;

import java.util.Arrays;

public class LongestIncreasingPath {

    private int getLongestIncreasingPathUtil(int[][] nums, int row, int col, int[][] dp){

        if(dp[row][col] != -1){
            return dp[row][col];
        }

        int up = (row > 0 && nums[row-1][col] > nums[row][col]) ? getLongestIncreasingPathUtil(nums, row-1, col, dp) : 0;
        int left = (col > 0 && nums[row][col-1] > nums[row][col]) ? getLongestIncreasingPathUtil(nums, row, col-1, dp) : 0;
        int down = (row < nums.length - 1 && nums[row+1][col] > nums[row][col]) ? getLongestIncreasingPathUtil(nums, row+1, col, dp) : 0;
        int right =(col < nums[0].length - 1 && nums[row][col+1] > nums[row][col]) ? getLongestIncreasingPathUtil(nums, row, col+1, dp) : 0;

        return dp[row][col] = 1 + Math.max(Math.max(Math.max(up, left), down), right);

    }

    public int getLongestIncreasingPath(int[][] nums){
        int row = nums.length;
        int col = nums[0].length;
        int maxLength=0;

        int[][] dp = new int[row][col];

        for(int i=0; i<row; i++){
            Arrays.fill(dp[i], -1);
        }

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                maxLength = Math.max(maxLength, getLongestIncreasingPathUtil(nums, i, j, dp));
            }
        }
        return maxLength;
    }


    private int getLongestIncreasingPathUtil2(int[][] nums, int row, int col, int[][] dp){

//        if(row == 0 || col == 0) return result;
//        if(row == nums.length || col == nums[0].length) return result;
//
        if(dp[row][col] != -1){
            return dp[row][col];
        }

//        if(nums[row-1][col] > nums[row][col] || nums[row][col-1] > nums[row][col]
//                || nums[row+1][col] > nums[row][col] || nums[row][col+1] > nums[row][col]){
//
//            result = result + 1;
//            dp[row][col] = result;
//        }
//
//        else return dp[row][col] = result;

        int up = (row > 0 && nums[row-1][col] > nums[row][col]) ? getLongestIncreasingPathUtil2(nums, row-1, col, dp) : 0;
        int left = (col > 0 && nums[row][col-1] > nums[row][col]) ? getLongestIncreasingPathUtil2(nums, row, col-1, dp) : 0;
        int down = (row < nums.length - 1 && nums[row+1][col] > nums[row][col]) ? getLongestIncreasingPathUtil2(nums, row+1, col, dp) : 0;
        int right =(col < nums[0].length - 1 && nums[row][col+1] > nums[row][col]) ? getLongestIncreasingPathUtil2(nums, row, col+1, dp) : 0;


        return dp[row][col] = Math.max(Math.max(Math.max(up, left), down), right) + 1;

    }

    public int getLongestIncreasingPath2(int[][] nums){
        int row = nums.length;
        int col = nums[0].length;
        int ans = 0;

        int[][] dp = new int[row][col];

        for(int i=0; i<row; i++){
            Arrays.fill(dp[i], -1);
        }

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                ans = Math.max(ans, getLongestIncreasingPathUtil2(nums, i, j, dp));
            }
        }
        return ans;
    }


    public static void main(String[] args) {

        LongestIncreasingPath main = new LongestIncreasingPath();

        int[][] nums1 = {{9,9,4}, {6,6,8}, {2,1,1}};
        //System.out.println(main.getLongestIncreasingPath(nums1));

        /*
        [3,4,5],
        [3,2,6],
        [2,2,1]
        */

        int[][] nums2 = {{3,4,5}, {3,2,6}, {2,2,1}};
        //System.out.println(main.getLongestIncreasingPath(nums2));
        System.out.println(main.getLongestIncreasingPath2(nums2));

    }
}
