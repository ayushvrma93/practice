package com.twentytwo.recursion;

import com.twentytwo.Utility;

import java.util.ArrayList;
import java.util.List;

public class SortAnArray {

    private void sortUtil(List<Integer> list){

        if(list.isEmpty()) return;

        int lastIndex = list.size() - 1;

        int temp = list.remove(lastIndex);

        sortUtil(list);

        insert(list, temp);

    }

    private void insert(List<Integer> list, int temp) {

        int lastIndex = list.size() - 1;

        if(list.isEmpty() || list.get(lastIndex) <= temp){
            list.add(temp);
            return;
        }

        int lastVal = list.get(lastIndex);
        list.remove(lastIndex);

        insert(list, temp);
        list.add(lastVal);
    }


    public Integer[] sort(Integer[] arr){

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) { list.add(arr[i]); }
        sortUtil(list);
        return list.toArray(new Integer[0]);
    }


    public static void main(String[] args) {

        SortAnArray sortAnArray = new SortAnArray();
        Integer[] arr1 = {2,6,4,3,9};

        Integer[] result1 = sortAnArray.sort(arr1);
        Utility.printArray(result1);
    }
}
