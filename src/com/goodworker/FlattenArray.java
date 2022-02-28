package com.goodworker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlattenArray {

    public List<Integer> getFlattenList(String input){

        List<Integer> result = new ArrayList<>();

        for(int i=0; i<input.length(); i++){

            if((input.charAt(i) == '[') || (input.charAt(i) == ']') || (input.charAt(i) == ',') || (input.charAt(i) == ' ')) continue;

            result.add(Integer.valueOf(input.charAt(i) - '0'));
        }

        return result;
    }


    public static void main(String[] args) {

        FlattenArray flattenArray = new FlattenArray();
        List<Object> list = new ArrayList<>();
        list.add(1);

        list.add(Arrays.asList(1,2,3));
        String str = String.valueOf(list);
        //[1, 2, [3, [4, 5, [6], [7, 8]], [9]]]
        List<Integer> result = flattenArray.getFlattenList(str);
        System.out.println(result);
    }
}
