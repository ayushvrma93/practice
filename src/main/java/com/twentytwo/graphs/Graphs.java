package com.twentytwo.graphs;

import java.util.LinkedList;

public class Graphs {

    private int V;
    private LinkedList<Integer>[] adj;


    Graphs(int v){
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
    }

    public LinkedList<Integer>[] getAdj(){
        return this.adj;
    }
}
