package com.twentytwo.arrays;

public class SearchInSortedRotatedArray {

    public int search(int[] arr, int key){

        int l = 0;
        int h = arr.length -1;

        return searchUtil(arr, l, h, key);
    }

    private int searchUtil(int[] arr, int l, int h, int key) {

        if(l >= h) return -1;

        int mid = (l + h) / 2;

        if(key == arr[mid]) return mid;

        if(arr[l] <= arr[mid]) {
            if (key >= arr[l] && key <= arr[mid]) return searchUtil(arr, l, mid - 1, key);
            return searchUtil(arr, mid + 1, l, key);
        }

        if(key >= arr[mid] && key <= arr[h]) return searchUtil(arr, mid + 1, h, key);

        return searchUtil(arr, l, mid - 1, key);
    }


    public static void main(String[] args) {

        SearchInSortedRotatedArray search = new SearchInSortedRotatedArray();

        int[] arr1 = {12, 14, 1, 2, 3, 4, 5, 6};
        int[] arr2 = {12, 14, 1, 2, 3, 4, 5, 6};
        int result1 = search.search(arr1, 14);
        int result2 = search.search(arr2, 4);
        System.out.println(result1);
        System.out.println(result2);
    }
}
