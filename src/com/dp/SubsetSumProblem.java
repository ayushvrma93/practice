package com.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetSumProblem {

    public boolean recursive(int[] set, int sum, int size){

        if(sum == 0) return true;

        if(sum < 0 || size <= 0) return false;

        return recursive(set, sum - set[size - 1], size - 1) || recursive(set, sum, size - 1);
    }


    //wrong method
    private boolean byDPUtil(int[] set, int sum, int size, Map<Integer, Boolean> truthMap){

        if(sum == 0) return true;

        if(sum < 0 || size <= 0) {
            truthMap.put(sum, false);
            return false;
        }

        if(truthMap.containsKey(sum)) return truthMap.get(sum);

        Boolean isPossibleByIncluding = byDPUtil(set, sum - set[size - 1], size - 1, truthMap);
        truthMap.put(sum - set[size - 1], isPossibleByIncluding);

        Boolean isPossibleByNotIncluding = byDPUtil(set, sum, size - 1, truthMap);
        truthMap.put(sum, isPossibleByNotIncluding);

        return isPossibleByIncluding || isPossibleByNotIncluding;
    }


    public boolean byDP(int[] set, int sum){
        int size = set.length;
        Map<Integer, Boolean> truthMap = new HashMap<>();
        return byDPUtil(set, sum, size, truthMap);
    }


    private boolean printPathUtil(int[] set, int sum, int size, Map<Integer, Boolean> truthMap, List<Integer> subset, List<List<Integer>> finalResult){

        if(sum == 0) {
            List<Integer> temp = new ArrayList<>(subset);
            finalResult.add(temp);
            return true;
        }

        if(sum < 0 || size <= 0) {
            //truthMap.put(sum, false);
            return false;
        }

        subset.add(set[size - 1]);

        if(truthMap.containsKey(sum)) return truthMap.get(sum);

        Boolean isPossibleByIncluding = printPathUtil(set, sum - set[size - 1], size - 1, truthMap, subset, finalResult);
        truthMap.put(sum - set[size - 1], isPossibleByIncluding);

        subset.remove(subset.size()-1);

        Boolean isPossibleByNotIncluding = printPathUtil(set, sum, size - 1, truthMap, subset, finalResult);
        truthMap.put(sum, isPossibleByNotIncluding);

        return isPossibleByIncluding || isPossibleByNotIncluding;
    }


    public List<List<Integer>> getAllSubsets(int[] set, int sum){

        int size = set.length;
        Map<Integer, Boolean> truthMap = new HashMap<>();
        List<Integer> subset = new ArrayList<>();

        List<List<Integer>> finalResult = new ArrayList<>();
        printPathUtil(set, sum, size, truthMap, subset, finalResult);
        return finalResult;
    }


    public static void main(String[] args) {

        SubsetSumProblem subsetSum = new SubsetSumProblem();

        int[] set1 = {1, 5, 3, 7, 4};
        int sum1 = 12;
        int size1 = 5;

        int[] set2 = {10, 3, 2, 8, 12};
        int sum2 = 16;
        int size2 = 5;

        int set3[] = {2, 3, 5, 6, 8};
        int sum3 = 10;

//        System.out.println("By recursion:"+" "+subsetSum.recursive(set1, sum1, size1));
        System.out.println("By DP:"+" "+subsetSum.byDP(set1, sum1));
//        System.out.println("By recursion:"+" "+subsetSum.recursive(set2, sum2, size2));
          //System.out.println("By recursion:"+" "+subsetSum.recursive(set3, sum3, set3.length));
        System.out.println("By DP:"+" "+subsetSum.byDP(set2, sum2));
        System.out.println("By DP:"+" "+subsetSum.byDP(set3, sum3));

        //System.out.println("Path is:"+" "+subsetSum.getAllSubsets(set1, sum1));
        //System.out.println("Path is:"+" "+subsetSum.getAllSubsets(set2, sum2));
        //System.out.println("Path is:"+" "+subsetSum.getAllSubsets(set3, sum3));


    }
}
