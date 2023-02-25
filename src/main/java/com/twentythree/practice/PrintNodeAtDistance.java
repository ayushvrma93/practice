package com.twentythree.practice;

import com.twentytwo.tree.Tree;
import com.twentytwo.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PrintNodeAtDistance {

    List<Integer> path = new ArrayList<>();

    //the below method will find nodes only in the same subtree
    public void printNodeAtGivenDist(TreeNode root, int dist){

        if(root == null) return;

        if(dist == 0){
            System.out.print(root.data + " ");
            return;
        }

        printNodeAtGivenDist(root.left, dist-1);
        printNodeAtGivenDist(root.right, dist-1);
    }

    //complete solution
    private List<Integer> getAtDistK(TreeNode root, TreeNode target, int k){

        path = new ArrayList<>();
        return null;
    }

    private void findPath(TreeNode root, int target, List<Integer> al){
        if(root == null) return;

        al.add(root.data);

        if(root.data == target){
            List<Integer> copy = new ArrayList<>(al);
            path.addAll(copy);
        }

        findPath(root.left, target, al);
        findPath(root.right, target, al);

        al.remove(al.size()-1);
    }

    public static void main(String[] args) {

        PrintNodeAtDistance print = new PrintNodeAtDistance();
        //print.printNodeAtGivenDist(Tree.getCompleteBinaryTree(), 2);
        print.findPath(Tree.getCompleteBinaryTree(), 5, new ArrayList<>());
        System.out.println(print.path);
    }
}
