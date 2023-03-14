package com.twentythree.practice.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllCombinations {

    List<String> finalRes = new ArrayList<>();

    public void getAll(List<String> str){
        finalRes = new ArrayList<>();
        getAllUtil(str, "", 0);
    }

    private void getAllUtil(List<String> list, String res, int i){

        if(i == list.size()){
            finalRes.add(res);
            return;
        }

        for(int j=0; j<list.get(i).length(); j++){
            res = res + list.get(i).charAt(j);
            getAllUtil(list, res, i+1);
            res = res.substring(0, res.length()-1);
        }
    }

    public static void main(String[] args) {

        AllCombinations allCombinations = new AllCombinations();
        List<String> stringList1 = Arrays.asList(new String[]{"ab", "cd"});
        allCombinations.getAll(stringList1);
        System.out.println(allCombinations.finalRes);
    }
}
