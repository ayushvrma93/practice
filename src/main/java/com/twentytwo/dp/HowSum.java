package com.twentytwo.dp;

import java.util.ArrayList;

public class HowSum {

    public ArrayList<Integer> perform(int targetSum, int[] arr){

        ArrayList<Integer> resultArr = new ArrayList<>();

        if(targetSum == 0)
            return resultArr;

        if(targetSum < 0)
            return null;

        for(int val : arr){
            int remainder = targetSum - val;
            resultArr = perform(remainder, arr);
            if(resultArr != null){
                //ArrayList<Integer> temp = new ArrayList<>(resultArr);
                //temp.add(val);
                resultArr.add(val);
                //resultArr = temp;
                return resultArr;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        HowSum howSum = new HowSum();
        int [] arr1 = {5, 3, 4, 7};
        System.out.println(howSum.perform(7, arr1));
        System.out.println(howSum.perform(9, arr1));
    }
}
