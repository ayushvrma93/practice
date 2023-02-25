package com.twentythree.angelbroking;

import java.util.Stack;

public class RainWaterTrappingProblem {

    public int byStack(int[] arr){

        Stack<Integer> st = new Stack<>();
        int water = 0;

        for(int i=0; i<arr.length; i++){

            while(!st.isEmpty() && arr[st.peek()] < arr[i]){

                int ph = arr[st.pop()];

                if(st.isEmpty()) break;

                int dist = i - st.peek() - 1;

                int minH = Math.min(arr[st.peek()], arr[i]) - ph;

                water+=(dist * minH);
            }
            st.push(i);
        }
        return water;
    }

    public static void main(String[] args) {
        RainWaterTrappingProblem rain = new RainWaterTrappingProblem();
        int[] arr = {1,3,2,6};
        System.out.println(rain.byStack(arr));
    }
}
