package com.twentytwo.uber;

import java.util.*;

public class BreakingBad {

/*
You are given a set of symbols for the elements in the periodic table
eg. [... Li, Be, B, C, N, F, Ne, Na, Co, Ni, Cu, Ga, Al, Si...]
Write the function BreakingBad(name, symbols) that given a name and a set of symbols returns the phrase with the following format. For example:
Symbols = [H, He, Li, Be, B, C, N, F, Ne, Na, Co, Ni, Cu, Ga, Al, Si, Fa]
BreakingBad("henry alba", symbols) results in [He]nry [Al]ba


Additional Examples:
BreakingBad("connor riddle", symbols) results in [Co]nnor riddle
BreakingBad("nicole carry", symbols) results in [Ni]cole [C]arry
BreakingBad("jerry seinfeld", symbols) results in jerry seinfeld
BreakingBad("ben f gabe", symbols) results in [Be]n [F] [Ga]be

Assume,
W = number of words in the sentence, E = number of elements, L = average length of element

Solution 1:
Pre-processing:
Construction of HashSet: O(E * L) L => 2
For Each word in Phrase
     -> longest symbol -> smallest symbol
     -> 2 letters of the word -> search in hashset
     -> 1 letter of the word -> search in hashset
Complexity:
     O(W * (L * log(E)))

*/


    public static String getString(String name, List<String> symbols){

        Set<String> sym = new HashSet<>();

//        for(String currSym : symbols){
//            sym.add(currSym.toLowerCase());
//        }

        symbols.forEach(s -> sym.add(s.toLowerCase()));

        StringBuilder resultString = new StringBuilder();

        String[] names = name.split(" ");

        for(String currWord : names){

            boolean isWordFound = false;

            int length = 2;

            while(length > 0){

                String currPat = currWord.substring(0, 2);

                if(sym.contains(currPat)){
                    isWordFound = true;
                    resultString.append("[").append(currPat).append("]").append(currWord.substring(2)).append(" ");
                    break;
                } else{
                    length--;
                }
            }

            if(!isWordFound){ resultString.append(currWord); }
        }
        return resultString.toString();
    }

    public static void main(String[] args) {

        String[] symbols = {"H", "He", "Li", "Be", "B", "Ca", "N", "F", "Ne", "Na", "Co", "Ni", "Cu", "Ga", "Al", "Si", "Fa"};
        List<String> symList = Arrays.asList(symbols);
        System.out.println(getString("connor riddle", symList));
        System.out.println(getString("nicole carry", symList));
    }
}
