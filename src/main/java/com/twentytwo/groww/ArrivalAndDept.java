package com.twentytwo.groww;

import java.util.Arrays;

public class ArrivalAndDept {

    /*
        arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
        dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
     */

    public int getMaxPlatformReq(int[] arr, int[] dept){

        Arrays.sort(arr);
        Arrays.sort(dept);

        int i=0;
        int j=0;

        int platform = 0;
        int maxPlatformReq = 0;

        while(i < arr.length && j < dept.length){

            if(arr[i] <= dept[j]){
                platform++;
                i++;
            }

            else {
                platform--;
                j++;
            }

            maxPlatformReq = Math.max(maxPlatformReq, platform);
        }

        while(i < arr.length){
            platform++;
            maxPlatformReq = Math.max(maxPlatformReq, platform);
            i++;
        }
        return maxPlatformReq;
    }

    public static void main(String[] args) {

        ArrivalAndDept arrivalAndDept = new ArrivalAndDept();
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dept = {910, 1200, 1120, 1130, 1900, 2000};

        System.out.print(arrivalAndDept.getMaxPlatformReq(arr, dept));
    }
}
