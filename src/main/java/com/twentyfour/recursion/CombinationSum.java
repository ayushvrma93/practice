package com.twentyfour.recursion;

import java.util.ArrayList;

public class CombinationSum {

    public void combSum(int i, int target, int[] arr, ArrayList<Integer> ds, ArrayList<ArrayList<Integer>> res){
        if(target < 0 || i >= arr.length){
            return;
        }

        if(target == 0){
            res.add(new ArrayList<>(ds));
            return;
        }

        ds.add(arr[i]);

        combSum(i, target - arr[i], arr, ds, res);
        ds.remove(ds.size()-1);
        combSum(i+1, target, arr, ds, res);
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        int[] arr = {2,3,6,7};
        combinationSum.combSum(0, 7, arr, new ArrayList<>(), res);
        System.out.println(res);
    }
}
