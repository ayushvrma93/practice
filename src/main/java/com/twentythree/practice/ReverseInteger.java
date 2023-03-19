package com.twentythree.practice;

public class ReverseInteger {

    public int reverse(int x) {


        String num = String.valueOf(x);

        String sign = num.charAt(0) == '-' ? "-" : "";

        char[] chars ;


        chars = new char[num.length()];
        int len = chars.length;

        int i=0;
        int j = len-1;

        if(sign.equals("-")) {i=1;}
        chars = num.toCharArray();

        for( ; i<j; i++, j--){

            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        String result = String.valueOf(chars);
        Long l = Long.valueOf(result);

        return l > Integer.MAX_VALUE ? 0 : Integer.valueOf(result);
    }

    public static void main(String[] args) {

        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverse(123));
        System.out.println(reverseInteger.reverse(-123));
        System.out.println(reverseInteger.reverse(124));
    }
}
