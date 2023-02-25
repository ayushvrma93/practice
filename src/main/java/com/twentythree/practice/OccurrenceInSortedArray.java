package com.twentythree.practice;

public class OccurrenceInSortedArray {

    private int getCount(int[] a, int key){
        return getMinIndex(a, key, 0, a.length-1); //+ getMaxIndex(a, key, 0, a.length-1) + 1;
    }

    private int getMinIndex(int[] a, int key, int low, int high){

        int minIndex = Integer.MAX_VALUE;
        int maxIndex = Integer.MIN_VALUE;

        while(low <= high){

            int mid = low + (high - low)/2;

            if(a[mid] == key){
                minIndex = Math.min(minIndex, mid);
                maxIndex = Math.max(maxIndex, mid);
            }

            if(mid > low && a[mid-1] >= key){
                high = mid-1;
            }
            else{
                low = mid + 1;
            }
        }
        return maxIndex - minIndex + 1;
    }

    public static void main(String[] args) {

        OccurrenceInSortedArray occ = new OccurrenceInSortedArray();
        int[] arr1 = {5,7,10,10,10,14,15,15,18,18,18,18};
        System.out.println(occ.getCount(arr1, 10));
        System.out.println(occ.getCount(arr1, 15));
        System.out.println(occ.getCount(arr1, 18));
    }
}
