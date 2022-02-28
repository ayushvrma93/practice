package com.blitz;

import java.util.Stack;

public class GetMinFromStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();


    public void push(int value){

        stack.push(value);
        int min = minStack.isEmpty() ? value : Math.min(value, minStack.peek()) ;
        minStack.push(min);
    }


    public Integer pop(){

        if(stack.isEmpty()) return null;

        int val = stack.pop();
        minStack.pop();
        return val;
    }

    public Integer getMin(){
        if(stack.isEmpty()) return null;
        return minStack.peek();
    }
}
