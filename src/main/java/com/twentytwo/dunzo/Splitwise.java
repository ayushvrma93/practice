package com.twentytwo.dunzo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Splitwise {

    public void withoutSimplification(List<Owe> oweList){

        Map<String, Integer> nameAndAmountMap = getIOwe(oweList);

        for(Map.Entry<String, Integer> owe : nameAndAmountMap.entrySet()){
            System.out.println("Owing person: " + owe.getKey() + " Owing amount: " + owe.getValue());
        }
        System.out.println("Owe relation: "+ oweList);
    }


    public void withSimplification(List<Owe> oweList){

        Map<String, Integer> nameAndAmountMap = getActualIOwe(oweList);

        for(Map.Entry<String, Integer> owe : nameAndAmountMap.entrySet()){
            System.out.println("Owing person: " + owe.getKey() + " Owing amount: " + owe.getValue());
        }
    }


    private Map<String, Integer> getIOwe(List<Owe> oweList){

        Map<String, Integer> nameAndAmountMap = new HashMap<>();

        for(Owe owing : oweList){

            if(nameAndAmountMap.containsKey(owing.getSource())){

                Integer amount = nameAndAmountMap.get(owing.getSource());
                amount += owing.getAmount();
                nameAndAmountMap.put(owing.getSource(), amount);
            }

            else{
                nameAndAmountMap.put(owing.getSource(), owing.getAmount());
            }
        }
        return nameAndAmountMap;
    }


    private Map<String, Integer> getActualIOwe(List<Owe> oweList){

        Map<String, Integer> nameAndAmountMap = new HashMap<>();

        for(Owe owing : oweList){
            nameAndAmountMap = populate(owing, nameAndAmountMap);
        }
        return nameAndAmountMap;
    }


    private Map<String, Integer> populate(Owe owing, Map<String, Integer> nameAndAmountMap){

        if(nameAndAmountMap.containsKey(owing.getSource())){

            Integer amount = nameAndAmountMap.get(owing.getSource());
            amount += owing.getAmount();
            nameAndAmountMap.put(owing.getSource(), amount);
        }
        else{
            nameAndAmountMap.put(owing.getSource(), owing.getAmount());
        }

         if(nameAndAmountMap.containsKey(owing.getDestination())){

            Integer amount = nameAndAmountMap.get(owing.getDestination());
            amount -= owing.getAmount();
            nameAndAmountMap.put(owing.getDestination(), amount);
        } else {
             nameAndAmountMap.put(owing.getDestination(), -1 * owing.getAmount());
         }
        return nameAndAmountMap;
    }


    public static void main(String[] args) {

        Splitwise splitwise = new Splitwise();

        Owe owe1 = new Owe("M", "N", 20);
        Owe owe2 = new Owe("L", "M", 70);
        Owe owe3 = new Owe("N", "L", 40);
        Owe owe4 = new Owe("N", "K", 100);

        List<Owe> oweList = new ArrayList<>();

        oweList.add(owe1);
        oweList.add(owe2);
        oweList.add(owe3);
        oweList.add(owe4);

        splitwise.withoutSimplification(oweList);
        System.out.print("With simplification: ");
        splitwise.withSimplification(oweList);
    }
}
