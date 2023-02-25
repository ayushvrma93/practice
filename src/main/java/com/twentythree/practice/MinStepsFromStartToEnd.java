package com.twentythree.practice;

public class MinStepsFromStartToEnd {

    private int getMinSteps(int[][] arr, int m, int n, int steps){

        if(m < 0 || n < 0) return Integer.MAX_VALUE;

        if(m==0 && n==0) return steps;

        return Math.min(getMinSteps(arr, m-1, n, steps+1), getMinSteps(arr, m, n-1, steps+1));
    }

    public static void main(String[] args) {

        MinStepsFromStartToEnd minSteps = new MinStepsFromStartToEnd();

        int[][] arr = {{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}};
        System.out.println(minSteps.getMinSteps(arr, 3, 2, 0));
    }
}
