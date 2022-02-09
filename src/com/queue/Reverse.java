package com.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Reverse {

    public Queue<Integer> iterative(Queue<Integer> q){

        if(q == null || q.isEmpty() || q.size() == 1) return q;

        Stack<Integer> st = new Stack<>();

        while (!q.isEmpty()){
            int x = q.poll();
            st.push(x);
        }

        while (!st.isEmpty()) q.add(st.pop());

        return q;
    }

    public Queue<Integer> recursive(Queue<Integer> q){

        if(q.isEmpty()) return q;

        int val = q.poll();

        q = recursive(q);

        q.add(val);

        return q;

    }

    public static void main(String[] args) {

        Reverse reverse = new Reverse();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= 5; i++) q.add(i);

        //q = reverse.iterative(q);
        q = reverse.recursive(q);

        System.out.println(q);
    }
}
