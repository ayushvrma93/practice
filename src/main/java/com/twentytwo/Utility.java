package com.twentytwo;

public class Utility {

    public static void printIntArray(int[] arr){

        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }


    public static <T> void printArray(T[] arr){

        for (T t : arr) {
            System.out.print(t + ", ");
        }
        System.out.println();
    }
}
