package com.twentythree.practice;

public class MaxDistToClosest {

    public int maxDistToClosest(int[] seats) {

        int start = -1;
        int end = -1;
        int dist = 0;

        int max_start = 0;
        int max_end = 0;
        int max_dist = 0;

        boolean flag = false;

        int n = seats.length;

        for(int i=0; i<n; i++){

            if(seats[i]==0 && start == -1){
                start = i;
            }

            else if(seats[i] == 1 && start != -1){
                end = i-1;
            }

            if(start != -1 && end != -1){
                dist = end - start +1;
                flag = true;
            }

            if(dist > max_dist){
                max_start = start;
                max_end = end;
                max_dist = dist;
            }

            if(flag){
                start = -1;
                end = -1;
                flag = !flag;
            }
        }

        if(seats[n-1] == 0){
            end = n-1;
            dist = end-start+1;
            if(dist > max_dist){
                return end;
            }
        }

        return (max_start + max_end)/2;
    }

    public static void main(String[] args) {

        MaxDistToClosest maxDistToClosest = new MaxDistToClosest();

        int[] arr1 = {1,0,0,0,1,0,1};
        int[] arr2 = {1,0,0,0, 1, 0,0,0,0,0,0,1};
        int[] arr3 = {1,0,1};
        int[] arr4 = {1,0,0,0};
        System.out.println(maxDistToClosest.maxDistToClosest(arr1));
        System.out.println(maxDistToClosest.maxDistToClosest(arr2));
        System.out.println(maxDistToClosest.maxDistToClosest(arr3));
        System.out.println(maxDistToClosest.maxDistToClosest(arr4));
    }
}
