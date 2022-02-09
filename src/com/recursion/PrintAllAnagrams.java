package com.recursion;

public class PrintAllAnagrams {

    public void print(String str, String ans){

        if(str.length() == 0){
            System.out.print(ans + " ");
            return;
        }

        for(int i=0; i<str.length(); i++){

            Character ch = str.charAt(i);
            String left = str.substring(0, i);
            String right = str.substring(i+1);
            String rest = left + right;
            print(rest, ans + ch);
        }
    }

    public static void main(String[] args) {

        PrintAllAnagrams printAllAnagrams = new PrintAllAnagrams();
        printAllAnagrams.print("ABC", "");
    }
}
