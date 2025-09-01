package com.twentyfour.dp;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

//    public List<List<Integer>> generate(int numRows) {
//        List<List<Integer>> res = new ArrayList<>();
//
//        List<Integer> temp = new ArrayList<>();
//        temp.add(1);
//        res.add(temp);
//
//        if(numRows == 1){
//            return res;
//        }
//
//        List<Integer> temp2 = new ArrayList<>();
//        temp2.add(1);
//        temp2.add(1);
//        res.add(temp2);
//
//        int[][] resArr = new int[numRows][numRows];
//
//        res = generate(numRows, resArr);
//        return res;
//    }
//
//    public List<List<Integer>> generate(int nr, int[][] res) {
//
//        for(int i=2; i<nr; i++){
//            int[] temp = new int[i+1];
//            temp[0] = 1;
//            temp[i] = 1;
//
//
//            for(int j=1; j<i-1; j++){
//                temp[j] = res[i-1][j-1] + res[i-1][j];
//            }
//
//            res[i] = temp;
//        }
//
//        return Arrays.asList(res);
//    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        res.add(temp);

        if(numRows == 1){
            return res;
        }

        List<Integer> temp2 = new ArrayList<>();
        temp2.add(1);
        temp2.add(1);
        res.add(temp2);

        generate(numRows, res);
        return res;
    }

    public void generate(int nr, List<List<Integer>> res) {

        for(int i=2; i<nr; i++){
            List<Integer> temp = new ArrayList<>(i+1);
            temp.add(1);
            temp.add(1);

            for(int j=1; j<i; j++){
                temp.add(j, res.get(i-1).get(j-1) + res.get(i-1).get(j));
            }

            res.add(temp);
        }
    }

    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();
        System.out.println(pascalTriangle.generate(5));
        System.out.println(pascalTriangle.generate(1));
    }
}
