package com.twentytwo.java;

import java.util.ArrayList;
import java.util.List;

public class TestStreamApi {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("my");
        strings.add("name");
        strings.add("is");
        strings.add("ayush");
        strings.add("verma");

        List<String> result = new ArrayList<>();

        strings.stream().forEach(s -> result.add(s));

        result.stream().forEach(System.out :: println);

        List<Integer> integers = new ArrayList<>();

        integers.stream().forEach(i -> integers.add(i++));

        //System.out.println(integers.size());

        for(int i=0; i< integers.size(); i++){
            System.out.print(i + " ");
        }


    }
}
