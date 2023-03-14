package com.twentythree.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneCharacters {

    static List<String> result;

    public List<String> getPossibleStrings(Map<Integer, List<Character>> charactersForInt, String digits){
        result = new ArrayList<>();
        getPossibleStringsUtil(charactersForInt, digits, 0, "");
        return result;
    }
    private void getPossibleStringsUtil(Map<Integer, List<Character>> charactersForInt, String digits, int inputIndex, String currStr){

        if(currStr.length() == digits.length()){
            result.add(currStr);
            return;
        }
        int currDigit = Character.getNumericValue(digits.charAt(inputIndex));
        List<Character> chars = charactersForInt.get(currDigit);

        for(int i=0; i<chars.size(); i++) {
            getPossibleStringsUtil(charactersForInt, digits, inputIndex + 1, currStr + chars.get(i));
        }
    }

    public static void main(String[] args) {

        PhoneCharacters phoneCharacters = new PhoneCharacters();
        Map<Integer, List<Character>> charactersForInt = new HashMap<>();
        List<Character> two = new ArrayList<>();
        two.add('a');
        two.add('b');
        two.add('c');

        List<Character> three = new ArrayList<>();
        three.add('d');
        three.add('e');
        three.add('f');

        List<Character> four = new ArrayList<>();
        four.add('g');
        four.add('h');
        four.add('i');

        List<Character> five = new ArrayList<>();
        five.add('j');
        five.add('k');
        five.add('l');

        List<Character> six = new ArrayList<>();
        six.add('m');
        six.add('n');
        six.add('o');

        List<Character> seven = new ArrayList<>();
        seven.add('p');
        seven.add('q');
        seven.add('r');
        seven.add('s');

        List<Character> eight = new ArrayList<>();
        eight.add('t');
        eight.add('u');
        eight.add('v');

        List<Character> nine = new ArrayList<>();
        nine.add('w');
        nine.add('x');
        nine.add('y');
        nine.add('z');

        charactersForInt.put(2, two);
        charactersForInt.put(3, three);
        charactersForInt.put(4, four);
        charactersForInt.put(5, five);
        charactersForInt.put(6, six);
        charactersForInt.put(7, seven);
        charactersForInt.put(8, eight);
        charactersForInt.put(9, nine);

        String digits1 = "23";
        String digits2 = "234";
        String digits3 = "2";

        //System.out.println(phoneCharacters.getPossibleStrings(charactersForInt, digits1, 0));
        //System.out.println(phoneCharacters.getPossibleStrings(charactersForInt, digits2, 0));
        phoneCharacters.getPossibleStrings(charactersForInt, digits2);
        System.out.println(result);
    }
}
