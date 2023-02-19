package com.twentythree.practice;

public class NoOfWays {

    public int noOfWays(int n){

        if(n == 0) return 1;
        if(n < 0) return 0;

        return noOfWays(n-1) + noOfWays(n - 2);
    }

    public static void main(String[] args) {
        NoOfWays noOfWays = new NoOfWays();
        System.out.println(noOfWays.noOfWays(3));
    }
}

