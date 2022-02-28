package com;

// Start typing here

import java.util.ArrayList;
import java.util.List;

public class FindPath{


    static class Node{

        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public List<Integer> getPathBetweenNodes(Node root, int n1, int n2){

        Node lca = findLCA(root, n1, n2);

        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();

        boolean leftPath = getPath(lca, n1, path1);
        boolean rightPath = getPath(lca, n2, path2);

        List<Integer> finalPath = new ArrayList<>(path1);

        for(Integer val : path2){
            finalPath.add(val);
        }

        return finalPath;
    }




    private Node findLCA(Node root, int n1, int n2){

        if(root == null) return null;

        if(root.data > n1 && root.data > n2){
            return findLCA(root.left, n1, n2);
        }

        if(root.data < n1 && root.data < n2){
            return findLCA(root.right, n1, n2);
        }

        return root;
    }


    private boolean getPath(Node lca, int givenNodeValue, List<Integer> path){

        if(lca == null) return false;

        path.add(lca.data);

        if(lca.data == givenNodeValue) return true;

        int nodeValue = lca.data;

        if(nodeValue < givenNodeValue) {
            getPath(lca.right, givenNodeValue, path);
            return true;
        }
        if(nodeValue > givenNodeValue) {
            getPath(lca.left, givenNodeValue, path);
            return true;
        }

        path.remove(path.size() - 1);

        return false;
    }

    public static void main(String[] args){

        FindPath findPath = new FindPath();

        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(6);

        List<Integer> finalPath = findPath.getPathBetweenNodes(root, 5, 6);


        for(Integer val : finalPath){
            System.out.print(val + " ");
        }
    }


}
