package com.twentythree.practice;

import com.twentytwo.tree.Tree;
import com.twentytwo.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PrintNodeAtDistance {

    List<TreeNode> path = new ArrayList<>();

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
        findPath(root, target);
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<path.size(); i++){
            findKDistFromNode(path.get(i), k-i, result, i==0?null : path.get(i-1));
        }
        return result;
    }

    private void findPath(TreeNode root, int target, List<TreeNode> al){

        if(root == null) return;

        al.add(root);

        if(root.data == target){
            List<TreeNode> copy = new ArrayList<>(al);
            path.addAll(copy);
        }

        findPath(root.left, target, al);
        findPath(root.right, target, al);

        al.remove(al.size()-1);
    }

    private void findKDistFromNode(TreeNode root, int dist, List<Integer> result, TreeNode blocker){

        if(dist < 0 || root == null || (blocker != null && root == blocker)){
            return;
        }

        if(dist == 0){
            result.add(root.data);
        }

        findKDistFromNode(root.left, dist-1, result, blocker);
        findKDistFromNode(root.right, dist-1, result, blocker);
    }


    public boolean findPath(TreeNode node, TreeNode target)
    {
        if (node == null)
            return false;

        if (node == target || findPath(node.left, target)
                || findPath(node.right, target)) {
            path.add(node);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        PrintNodeAtDistance print = new PrintNodeAtDistance();
        //print.printNodeAtGivenDist(Tree.getCompleteBinaryTree(), 2);
        //print.findPath(Tree.getCompleteBinaryTree(), 5, new ArrayList<>());
        TreeNode root = Tree.getTreeForNodeAtKDist();
        TreeNode target = root.left.right;
        //print.findPath(root, target);
        System.out.println(print.getAtDistK(root, target, 2));
//        for(TreeNode curr : print.path){
//            System.out.println(curr.data + " ");
//        }
    }
}
