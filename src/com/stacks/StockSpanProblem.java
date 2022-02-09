package com.stacks;

import com.Utility;

import java.util.Arrays;
import java.util.Stack;

//The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the given day
//        , for which the price of the stock on the current day is less than its price on the given day.
//        For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days
//        are {1, 1, 1, 2, 1, 4, 6}
public class StockSpanProblem {

    public int[] get(int[] price){

        Stack<Integer> st = new Stack<>();
        int[] stockSpan = new int[price.length];
        Arrays.fill(stockSpan, 1);

        st.push(0);

        for(int i = 1; i < price.length; i++){

            while(i< price.length && !st.isEmpty() && price[st.peek()] <= price[i]){
                    st.pop();
            }

            if(st.isEmpty()) stockSpan[i] = i + 1;

            else stockSpan[i] = i - st.peek();

            st.push(i);
        }
        return stockSpan;
    }

    public static void main(String[] args) {

        StockSpanProblem stockSpanProblem = new StockSpanProblem();

        int[] price1 = {100, 80, 60, 70, 60, 75, 85};
        int[] result1 = stockSpanProblem.get(price1);

        Utility.printIntArray(result1);
    }
}
