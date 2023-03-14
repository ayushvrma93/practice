package com.twentythree.practice;

public class BinarySearch {

    private int findPivot(int[] a, int low, int high){

        if(low > high) return -1;

        int mid = (low + high)/2;

        if(mid > low && a[mid] < a[mid-1]) return a[mid];

        if(mid < high && a[mid] > a[mid+1]) return mid+1;

        if(a[mid] < a[low]) return findPivot(a, low, mid-1);

        return findPivot(a, mid+1, high);
    }

    public int search(int[] a, int key){
        int pivot = findPivot(a, 0, a.length-1);
        return pivot;
    }

    public static void main(String[] args) {

        int[] a1 = {4,5,6,1,2};
        int[] a2 = {8,9,10,11,12,13,14,15,1,2};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.search(a1, 4));
        System.out.println(binarySearch.search(a2, 4));
    }
}
