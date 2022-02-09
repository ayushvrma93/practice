package com.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Obj{
    int ans;
    int[] ansArr;

    Obj(int ans, int[] ansArr){
        this.ans = ans;
        this.ansArr = ansArr;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "ans=" + ans +
                ", ansArr=" + Arrays.toString(ansArr) +
                '}';
    }
}

public class MinSwapToSort {

    public Obj getMinSwaps(int[] arr){

        Map<Integer, Integer> position = new HashMap<>();
        int[] temp = new int[arr.length];

        for(int i=0;i< arr.length;i++){
            temp[i] = arr[i];
        }

        int ans = 0;

        Arrays.sort(temp);

        for(int i=0; i< temp.length; i++){
            position.put(arr[i], i);
        }

        for(int i=0; i<arr.length; i++){

            if(arr[i] != temp[i]){

                ans++;
                int currVal = arr[i];

                swap(arr, i, position.get(temp[i]));
                position.put(currVal, position.get(temp[i]));
                position.put(temp[i], i);
            }
        }
        return new Obj(ans, arr);
    }

    private void swap(int[] arr, int i, int shouldIndex) {
        int temp = arr[i];
        arr[i] = arr[shouldIndex];
        arr[shouldIndex] = temp;
    }

    public static void main(String[] args) {

        MinSwapToSort minSwapToSort = new MinSwapToSort();
         int[] arr1 = {3,8,6,1};
         int[] arr2 = { 101, 758, 315, 730, 472, 619, 460, 479 };

         System.out.println(minSwapToSort.getMinSwaps(arr1));
         System.out.println(minSwapToSort.getMinSwaps(arr2));
    }
}
