package com.twentythree.practice.strings;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPermutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> finalRes = new ArrayList<>();
        String num = "";
        for(int curr : nums){
            num = num + curr;
        }
        permute(num, "", finalRes);
        return finalRes;
    }

    private void permute(String num, String res, List<List<Integer>> finalRes){

        if(num.length() == 0){
            List<Integer> temp = new ArrayList<>();
            for(int j=0; j<res.length(); j++){
                temp.add(Character.getNumericValue(res.charAt(j)));
            }
            finalRes.add(temp);
        }

        for(int i=0; i<num.length(); i++){
            Character curr = num.charAt(i);
            String left = num.substring(0,i);
            String right = num.substring(i+1);
            String rest = left + right;
            permute(rest, res + curr, finalRes);
        }
    }

    public String swap(String str, int i, int j){

        char[] chars = str.toCharArray();
        Character temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return String.valueOf(chars);
    }

    public static void main(String[] args) {

        PrintAllPermutations printAllPermutations = new PrintAllPermutations();
        int[] a1 = new int[3];
        a1[0] = 0;
        a1[1] = -1;
        a1[2] = 1;
        //List<List<Integer>> result = printAllPermutations.permute(a1);
        //Utility.getINSTANCE().printList(result);
        System.out.println(printAllPermutations.permute(a1));
    }
}
