package com.twentytwo.tree;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversal {

    List<Integer> inorderList = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root, List<Integer> result) {
        if(root == null)
            return new ArrayList<Integer>();

        //return inorderTraversalUtil(root);
        return inorderTraversalUtil(root, result);
    }

    public List<Integer> inorderTraversalUtil(TreeNode root) {

        if(root == null)
            return null;

        inorderTraversalUtil(root.left);
        inorderList.add(root.data);
        inorderTraversalUtil(root.right);

        return inorderList;
    }

    public List<Integer> inorderTraversalUtil(TreeNode root, List<Integer> result) {

        if(root == null)
            return null;

        inorderTraversalUtil(root.left, result);
        result.add(root.data);
        inorderTraversalUtil(root.right, result);

        return result;

    }

    public static void main(String[] args) {

        TreeNode tree = Tree.getBST();
        InOrderTraversal inOrderTraversal = new InOrderTraversal();

        List<Integer> result = inOrderTraversal.inorderTraversalUtil(tree, new ArrayList<>());

        for(Integer val : result){
            System.out.print(val + " -> ");
        }
    }
}
