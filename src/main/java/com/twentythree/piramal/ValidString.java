package com.twentythree.piramal;

import java.util.Stack;

public class ValidString {

    public boolean isStringValid(String s){

        Stack<Character> st = new Stack<>();
        int starCount = 0;

        for(int i=0; i<s.length(); i++){

            if(s.charAt(i) == '('){
                st.push(s.charAt(i));
            }

            else if(s.charAt(i) == '*'){
                starCount++;
            }

            else if(s.charAt(i) == ')'){

                if(!st.isEmpty() && st.peek() == ')'){
                    st.pop();
                }
                else if(st.isEmpty() && starCount <=0){
                    return false;
                }
            }
        }
        //if(st.isEmpty()){}
        //System.out.println(starCount);
        //System.out.println(st.size());
        if(starCount < 0) return false;

        if(starCount == st.size() || starCount %2 == 0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidString validString = new ValidString();
        String s1 = "())()()";
        String s2 = "()*()))(";
        String s3 = "()";
        System.out.println(validString.isStringValid(s1));
        System.out.println(validString.isStringValid(s2));
        System.out.println(validString.isStringValid(s3));
    }
}
