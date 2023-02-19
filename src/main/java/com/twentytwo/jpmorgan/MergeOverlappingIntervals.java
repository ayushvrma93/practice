package com.twentytwo.jpmorgan;

import com.twentytwo.arrays.Pair;

import java.util.*;

public class MergeOverlappingIntervals {

    public List<Pair> getMergedIntervals(List<Pair> input){

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.getKey()));

        List<Pair> mergedList = new ArrayList<>();

        for(Pair currPair : input){
            pq.add(currPair);
        }

        int maxEndTime = pq.peek().getValue();
        int minStartTime = pq.peek().getKey();

        while(!pq.isEmpty()){

            pq.poll();

            if(!pq.isEmpty()) {

                if (maxEndTime >= pq.peek().getKey()) {
                    maxEndTime = Math.max(maxEndTime, pq.peek().getValue());
                } else {
                    mergedList.add(new Pair(minStartTime, maxEndTime));
                    minStartTime = pq.peek().getKey();
                    maxEndTime = pq.peek().getValue();
                }
            }
        }
        mergedList.add(new Pair(minStartTime, maxEndTime));
        return mergedList;
    }

    public static void main(String[] args) {

        MergeOverlappingIntervals overlappingIntervals = new MergeOverlappingIntervals();

        List<Pair> inputs1 = new ArrayList<>();
        inputs1.add(new Pair(1, 5));
        inputs1.add(new Pair(4, 8));
        inputs1.add(new Pair(2, 3));
        inputs1.add(new Pair(9, 11));

        List<Pair> inputs2 = new ArrayList<>();
        inputs2.add(new Pair(1, 5));
        inputs2.add(new Pair(4, 8));
        inputs2.add(new Pair(2, 3));
        inputs2.add(new Pair(7, 11));

        List<Pair> inputs3 = new ArrayList<>();
        inputs3.add(new Pair(1, 3));
        inputs3.add(new Pair(2, 6));
        inputs3.add(new Pair(8, 10));
        inputs3.add(new Pair(15, 18));

        List<Pair> inputs4 = new ArrayList<>();
        inputs4.add(new Pair(1, 4));
        inputs4.add(new Pair(4, 5));

        List<Pair> result1 = overlappingIntervals.getMergedIntervals(inputs1);
        List<Pair> result2 = overlappingIntervals.getMergedIntervals(inputs2);
        List<Pair> result3 = overlappingIntervals.getMergedIntervals(inputs3);
        List<Pair> result4 = overlappingIntervals.getMergedIntervals(inputs4);

        result1.stream().forEach((p) -> System.out.println(p));
        System.out.println("Printing result 2..");
        result2.stream().forEach((p) -> System.out.println(p));
        System.out.println("Printing result 3..");
        result3.stream().forEach((p) -> System.out.println(p));
        System.out.println("Printing result 4..");
        result4.stream().forEach((p) -> System.out.println(p));
    }
}
