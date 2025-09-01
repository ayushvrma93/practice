package com.twentyfour;

import java.util.Arrays;

public class EraseOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

//        for(int i=0;i<n; i++){
//            System.out.print(intervals[i][0] + "->" + intervals[i][1] + " ");
//        }

//        int i=0;

        int count = 1;
        int end = intervals[0][1];

        for(int i=0; i<n; i++){
//            int start = intervals[i][0];

            if (intervals[i][0] >= end){
//                end = intervals[i][1];
                count++;
                end = intervals[i][1];
//                end =
            }
        }

        return n-count;
    }

    public static void main(String[] args) {
        EraseOverlappingIntervals erase = new EraseOverlappingIntervals();
        int[][] arr1 = {{1,2},{2,3},{3,4},{1,3}};
        int[][] arr2 = {{1,2}, {1,2}, {1,2}};
        System.out.println(erase.eraseOverlapIntervals(arr1));
        System.out.println(erase.eraseOverlapIntervals(arr2));
    }
}
