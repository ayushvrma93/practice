package com.twentythree.tessel;

/**
 * Consider You are at a system where someone has overriden the List functionality and they have played with length() function in all ways that you cannot call it
 * Now you have given an array of sorted integers and you need to find a given element in that array.
 * */

public class BinarySearchWithoutEnd {


    public static void main(String args[]) {
        int x=10;
        int y=25;
        int z=x+y;

        System.out.println("Sum of x+y = " + z);
    }


    private int getIndex(int[] arr, int low, int high, int key){

        if(low > high) return -1;

        int mid = (low + high)/2;

        if(arr[mid] == key) return mid;

        if(arr[mid] < key) return getIndex(arr, mid+1, high, key);

        return getIndex(arr, low, mid-1, key);
    }

    private int findEnd(int[] arr, int key, int start, int end){

        try{
            return retry(arr, key, start, end);
        } catch(ArrayIndexOutOfBoundsException a){
            return findEnd(arr, key, ((start/2) + start)/2, end);
        }
    }

    private int retry(int[] arr, int key, int start, int end) throws ArrayIndexOutOfBoundsException{

        while(true){

            if(arr[start] > key){
                return start;
            } else if(end == -1){
                start = 2^(start + 1);
            }
        }
    }

    private int getIndexOfElement(int[] arr, int key){

        if(arr.length == 0) return -1;

        int end = findEnd(arr, key, 0, -1);

        int index = getIndex(arr, 0, end, key);

        return index;
    }
}
