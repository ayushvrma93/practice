package com.mensa;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Problem1 {

    /*
        every subarray of size k- max element

        arr[] = {3,9,2,8,1,5}; k= 3

     */
    public List<Integer> getMaxInWindow(int[] arr, int k) {


        Deque<Integer> dq = new LinkedList();
        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 1;

        dq.addFirst(arr[0]);

        int max = Integer.MIN_VALUE;

        while(j <= arr.length && j < k){

            if(arr[j] >= dq.peekLast()){
                dq.pollFirst();
            }
            dq.addLast(arr[j]);

            max = Math.max(max, arr[j]);
        }

        result.add(max);

        return result;

    }

}
