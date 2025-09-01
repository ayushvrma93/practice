package com.twentyfour.google;

import java.util.HashMap;
import java.util.Map;

public class ReplaceStrings {

//    Write a library which supports defining “placeholders and values”, and substitute placeholders in strings with their values.
//
//    Basic example:
//    HOME="/usr/local/home/root"
//    DATE="2020-09-16"
//            "%HOME%/data/file_%DATE%.txt" -> "/usr/local/home/root/data/file_2020-09-16.txt"
//            "%HOME%%DATE%"


        private Map<String, String> placeHolder;

        public ReplaceStrings(){
            this. placeHolder = new HashMap<>();
        }

        public void addPlaceHolder(String key, String value){
            placeHolder.put(key, value);
        }

        public String substitute(String input){

            String result = new String(input);


            for(Map.Entry<String, String> entry : placeHolder.entrySet()){

                String ph = "%" + entry.getKey() + "%";

                result = result.replace(ph, entry.getValue());

            }

            return result;

        }


        public String substituteOptimised(String input){

            StringBuilder result = new StringBuilder(input);
            int i=0, n=input.length();

            while (i < n) {
                int i1 = result.indexOf("%", i);
                if (i1 == -1) {
                    break; // No more '%' found, exit loop
                }

                int i2 = result.indexOf("%", i1 + 1);
                if (i2 == -1) {
                    break; // No closing '%' found, exit loop
                }

                // Remove both '%' characters
                result.deleteCharAt(i1);
                i2--; // Adjust i2 after the first '%' is removed
                result.deleteCharAt(i2);

                // Get the placeholder between the two '%' characters
                String placeholder = result.substring(i1, i2);

                // Replace the placeholder with the corresponding value
                result.replace(i1, i2, placeHolder.getOrDefault(placeholder, placeholder));

                // Move 'i' forward to continue after the current replaced string
                i = i1 + placeholder.length();
                n = result.length(); // Update 'n' since the string length might change
            }

            return result.toString();
        }

    public static void main(String[] args){

        ReplaceStrings ss = new ReplaceStrings();

        ss.addPlaceHolder("HOME", "/usr/local/home/root");
        ss.addPlaceHolder("DATE", "2020-09-16");

        String result = ss.substitute("%HOME%/data/file_%DATE%.txt");
        String result1 = ss.substituteOptimised("%HOME%/data/file_%DATE%.txt");
        System.out.println(result);
        System.out.println(result1);
    }

}
