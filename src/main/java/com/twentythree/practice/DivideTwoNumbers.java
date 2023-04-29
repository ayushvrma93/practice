package com.twentythree.practice;

public class DivideTwoNumbers {

    public int divide(int dividend, int divisor) {

        boolean isMinus = false;

        int quotient = 0;

        if(dividend < 0 && divisor < 0){
            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);
        }

        else if(dividend < 0 || divisor < 0){
            isMinus = true;
            if(dividend < 0) Math.abs(dividend);
            else divisor = Math.abs(divisor);
        }

        while(dividend >= divisor){
            dividend -= divisor;
            quotient++;
        }
        return isMinus ? quotient * -1 : quotient;
    }

    public static void main(String[] args) {
        DivideTwoNumbers divide = new DivideTwoNumbers();
        //System.out.println(Integer.MIN_VALUE == -2147483648);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(divide.divide(-2147483648, -1));
    }
}
