package com.twentyfour.misc;

import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {

    public int[][] merge(int[][] intervals){
        Arrays.sort(intervals, Comparator.comparingInt(o->o[0]));
        int n = intervals.length;
        boolean merging = false;

        int[][] result = new int[n][2];

        int i=0, j = 0;

        while(i < intervals.length){
            merging = false;
            int start = intervals[i][0];
            int end = intervals[i][1];
            int max = intervals[i][1];

            while(i < intervals.length && intervals[i][1] >= intervals[i+1][0]){
                end = Math.max(max, intervals[i+1][1]);
                merging = true;
                i++;
            }


            int[] temp = new int[2];
            temp[0] = start;
            temp[1] = end;

            result[j++] = temp;
            i++;
        }
        if(!merging || i == n-1) {
            int[] temp = new int[2];
            temp[0] = intervals[n-1][0];
            temp[1] = intervals[n-1][1];
        }
        return Arrays.copyOfRange(result, 0, j-1);
    }

    public static void main(String[] args){
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] interval1 = {{1,3}, {2,6}, {8,10}, {15,18}};
    }
}
