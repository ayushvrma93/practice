package com.recursion;

import java.util.Stack;

public class SortAStack {

    private void sortUtil(Stack<Integer> st){

        if(st.isEmpty()) return;

        int top = st.pop();

        sortUtil(st);

        insert(st, top);

    }

    private void insert(Stack<Integer> st, int top) {

        if(st.isEmpty() || st.peek() <= top){
            st.push(top);
            return;
        }

        int currVal = st.pop();

        insert(st, top);

        st.push(currVal);
    }

    public Stack<Integer> sort(Stack<Integer> stack){
        sortUtil(stack);
        return stack;
    }


    public static void main(String[] args) {

        SortAStack sortAStack = new SortAStack();

        Stack<Integer> st1 = new Stack<>();
        st1.push(4);
        st1.push(1);
        st1.push(3);
        st1.push(5);
        st1.push(1);

        Stack<Integer> result1 = sortAStack.sort(st1);
        System.out.println(result1);
    }
}
