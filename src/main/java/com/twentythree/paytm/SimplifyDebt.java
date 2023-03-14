package com.twentythree.paytm;

import java.util.HashMap;
import java.util.Map;

public class SimplifyDebt {

    class Pair{

        Character person;
        int amount;

        Pair(Character person, Integer amount){
            this.person = person;
            this.amount = amount;
        }
    }

    public int minTransactions(Character[] giver, Character[] taker, Integer[] amount){

        Map<Character, Integer> personToAmount = new HashMap<>();

        for(int i=0; i<giver.length; i++){
            personToAmount.put(giver[i], 0);
            personToAmount.put(taker[i], 0);
        }

        for(int i=0; i<giver.length; i++){

            Character give = giver[i];
            Character take = taker[i];

            if(personToAmount.containsKey(giver[i])){
                int currAmount = personToAmount.get(give);
                currAmount += amount[i];
                personToAmount.put(give, currAmount);
            }

            if(personToAmount.containsKey(take)){
                int currAmount = personToAmount.get(take);
                currAmount -= amount[i];
                personToAmount.put(take, currAmount);
            }
        }
        return getTransactionCount(personToAmount, 0);
    }


    private int getTransactionCount(Map<Character, Integer> map, int transactions){

        Pair minPair = getMin(map);
        int min = minPair.amount;
        Pair maxPair = getMax(map);
        int max = maxPair.amount;

        if(min == 0 && max == 0) return transactions;

        if(Math.abs(min) < max){
            min = min + max;
            max = 0;
        } else {
            max = max + min;
            min = 0;
        }

        map.put(minPair.person, min);
        map.put(maxPair.person, max);

        return getTransactionCount(map, transactions + 1);
    }

    private Pair getMin(Map<Character, Integer> map){

        int min = Integer.MAX_VALUE;
        Character minKey = null;

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() < min){
                min = entry.getValue();
                minKey = entry.getKey();
            }
        }
        return new Pair(minKey, min);
    }

    private Pair getMax(Map<Character, Integer> map){

        int max = Integer.MIN_VALUE;
        Character maxKey = null;

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return new Pair(maxKey, max);
    }

    public static void main(String[] args) {

        SimplifyDebt simplifyDebt = new SimplifyDebt();
        Character[] giver = {'B', 'B', 'D', 'E', 'A'};
        Character[] taker = {'A', 'C', 'B', 'D', 'E'};
        Integer[] amount = {5, 10, 15, 20, 5};

        System.out.println(simplifyDebt.minTransactions(giver, taker, amount));
    }
}