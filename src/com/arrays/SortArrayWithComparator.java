package com.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class SortArrayWithComparator {

    public void sortArray() {

    int[] arr1 = {1, 3};
    int[] arr2 = {0, 5};

    int[][] res = {arr1, arr2};

    Arrays.sort(res, (Comparator.comparingInt(o -> o[0])));

//    List<List<Integer>> list = Arrays.asList(res);
//
//    for(int i=0; i<list.size(); i++){
//
//        List<Integer> currList = list.get(i);
//
//        for(int j=0; j< currList.size(); j++){
//
//        }
//    }

    //list.stream().forEach(e -> System.out.print(e + " "));

    }

    public static void main(String[] args) {

        SortArrayWithComparator sortArrayWithComparator = new SortArrayWithComparator();

        sortArrayWithComparator.sortArray();
    }


}
