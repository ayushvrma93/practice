package com.integers;

public class ReverseInteger {

    public int get(int number){

        String numStr = String.valueOf(number);
        String minusSign = "";

        if(numStr.startsWith("-")) {
            numStr = numStr.substring(1);
            minusSign = "-";
        }

        char[] charArr = numStr.toCharArray();

        for (int i=0, j=charArr.length-1; i<j; i++, j--){
            char temp = charArr[i];
            charArr[i] = charArr[j];
            charArr[j] = temp;
        }

        String result = minusSign + String.valueOf(charArr);
        Long res = Long.valueOf(result);

        if(res > (2^31 - 1) || res < (-2^31)) return 0;

        return Integer.valueOf(Math.toIntExact(res));
    }


    public static void main(String[] args) {

        int num1 = 123;
        int num2 = 1234;
        int num3 = -12345;

        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.get(num1));
        System.out.println(reverseInteger.get(num2));
        System.out.println(reverseInteger.get(num3));
    }
}
