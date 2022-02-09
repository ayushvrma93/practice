package com.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {

    private void DFSUtil(LinkedList<Integer>[] adj, int v, boolean[] visited){

        visited[v] = true;
        System.out.print(v + " ");

        Iterator itr = adj[v].listIterator();

        while (itr.hasNext()){
            int n = (Integer) itr.next();
            if(!visited[n]) DFSUtil(adj, n, visited);
        }
    }


    public void BFSUtil(LinkedList<Integer>[] adj, int v, Queue<Integer> q, boolean[] visited){

        visited[v] = true;
        q.add(v);

        while (!q.isEmpty()){

            int s = q.poll();
            System.out.print(s + " ");

            Iterator itr = adj[s].listIterator();

            while(itr.hasNext()){

                Integer n = (Integer) itr.next();

                if(!visited[n]){
                    visited[n] = true;
                    q.add(n);
                }
            }
        }
    }


    public void DFS(LinkedList<Integer> adj[], int v){
        boolean[] visited = new boolean[adj.length];
        DFSUtil(adj, v, visited);
    }


    public void BFS(LinkedList<Integer>[] adj, int v){
        boolean[] visited = new boolean[adj.length];
        Queue<Integer> q = new LinkedList();
        BFSUtil(adj, v, q, visited);
    }


    public static void main(String[] args) {

        Graphs graphs = new Graphs(4);
        int headVertex = 2;

        graphs.addEdge(0, 1);
        graphs.addEdge(0, 2);
        graphs.addEdge(1, 2);
        graphs.addEdge(2, 0);
        graphs.addEdge(2, 3);
        graphs.addEdge(3, 3);

        LinkedList<Integer>[] adj = graphs.getAdj();

        Traversal traversal = new Traversal();

        System.out.print("DFS for vertex: " + headVertex + ": ");
        traversal.DFS(adj, 2);
        System.out.println();
        System.out.print("BFS for vertex: " + headVertex + ": ");
        traversal.BFS(adj, 2);
    }
}
