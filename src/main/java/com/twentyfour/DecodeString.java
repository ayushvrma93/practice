package com.twentyfour;

import java.util.Stack;

public class DecodeString {

    public String decodeString(String s) {
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder();

        char[] ca = s.toCharArray();
        int n = ca.length;

        int i=0,j=0;

        while(i<n && j<n){
            char ch = ca[i];

            if(Character.isDigit(ch)){
                st.push(ch - '0');
                i++;
            }

            else if(ch=='['){
                j=i;
                while(j<n && ca[j]!=']'){
                    j++;
                }
                int popped = Integer.valueOf(st.pop());
                String sub = s.substring(i+1,j);
                for(int k=0;k<popped; k++){
                    sb.append(sub);
                }
                i=j+1;
            } else{
                sb.append(ch);
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
//        System.out.println(decodeString.decodeString("3[a]2[bc]"));
//        System.out.println(decodeString.decodeString("3[a2[c]]"));
        System.out.println(decodeString.decodeString("2[abc]3[cd]ef"));
    }
}
