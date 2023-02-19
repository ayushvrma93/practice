package com.twentytwo.arrays;

import java.util.Arrays;
import java.util.List;

public class RotateArrayToLeft {

    public static List<Integer> rotLeft(List<Integer> a, int d) {

        int size = a.size();

        Integer[] ans = new Integer[size];

        for(int i=0; i<size; i++){
            ans[i] = (i + d)%size;
        }

        return Arrays.asList(ans);
    }
}
