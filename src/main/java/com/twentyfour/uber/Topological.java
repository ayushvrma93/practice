package com.twentyfour.uber;

import java.util.*;

public class Topological {

// Main class should be named 'Solution' and should not be public.

/*
Design an Asynchronous Task Management Library

Requirements:
Should allow users to define a task 
Tasks can have dependencies on other tasks
Tasks get added to a Queue
Should have a main task runner (run once for this problem) that runs the tasks in the queue.
Program exits when the queue is empty.
*/

// class Node{
//     String task;
//     List<List<String>> dependencies;
// }



    Queue<String> q = new LinkedList<>();

    public void produce(List<List<String>> dependencies){
        List<String> sorted = topologicalSort(dependencies.size(), dependencies);

        for(String task : sorted){
            q.add(task);
        }
    }

    public void consume(){

        while(!q.isEmpty()){
            String task = q.poll();
            System.out.println(task);
        }

    }

    private List<String> topologicalSort(int n, List<List<String>> dependencies){

        boolean[] visited = new boolean[n];
        List<String> sorted = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(i, visited, stack, dependencies);
            }
        }

        while(!stack.isEmpty()){
            sorted.add(stack.pop());
        }

        return sorted;
    }

    private void dfs(int i, boolean[] visited, Stack<String> stack, List<List<String>> dependencies){

        visited[i] = true;
        for(int j=0; j<dependencies.get(i).size(); j++){
            // String neighbour = dependencies.get(i).get(j);
            if(!visited[j]){
                dfs(j, visited, stack, dependencies);
            }

        }
        stack.push(dependencies.get(i).get(0));
    }

    public static void main(String[] args) throws Exception{
        // System.out.println("Hello, World");

        Topological topological = new Topological();

        List<List<String>> dependencies = new ArrayList<>();

        List<String> d1 = new ArrayList<>();
        d1.add("A");
        d1.add("B");

        List<String> d2 = new ArrayList<>();
        d2.add("A");
        d2.add("C");

        dependencies.add(d1);
        dependencies.add(d2);

        topological.produce(dependencies);

        Thread.sleep(100);
        topological.consume();


    }

}
