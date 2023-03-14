package com.twentythree.practice.backtracking;

import java.util.ArrayList;

public class CombinationSum {

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        ArrayList<ArrayList<Integer>> finalRes = new ArrayList<>();
        combinationSumUtil(A, B, new ArrayList<>(), 0, finalRes);
        return finalRes;
    }

    private void combinationSumUtil(ArrayList<Integer> A, int B, ArrayList<Integer>res, int start, ArrayList<ArrayList<Integer>> finalRes){

        if(B<0) return ;

        if(B==0){
            ArrayList temp = new ArrayList(res);
            finalRes.add(temp);
        }

        for(int i=start; i<A.size(); i++){
            res.add(A.get(i));
            combinationSumUtil(A, B- A.get(i), res, i, finalRes);
            res.remove(res.size()-1);
        }
    }

    public static void main(String[] args) {

        CombinationSum combinationSum = new CombinationSum();
        ArrayList<Integer> A1 = new ArrayList<>();
        A1.add(2);
        A1.add(3);
        int B1 = 2;

        ArrayList<Integer> A2 = new ArrayList<>();
        A2.add(2);
        A2.add(3);
        A2.add(6);
        A2.add(7);
        int B2 = 7;
        //System.out.println(combinationSum.combinationSum(A1,B1));
        System.out.println(combinationSum.combinationSum(A2,B2));
    }
}
