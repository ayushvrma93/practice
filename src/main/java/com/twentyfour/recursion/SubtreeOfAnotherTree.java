package com.twentyfour.recursion;

import com.twentytwo.tree.TreeNode;

public class SubtreeOfAnotherTree {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.data = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.data = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public boolean inorder(TreeNode root, TreeNode subRoot) {
        if(root == null) return false;

        boolean left = inorder(root.left, subRoot);
        if(root.data == subRoot.data){
            if(isSimilar(root, subRoot)){
                return true;
            }

        }
        boolean right = inorder(root.right, subRoot);
        return left || right;
    }

    public boolean isSimilar(TreeNode root, TreeNode subroot){
        if(root == null && subroot == null){
            return true;
        }

        if((root == null && subroot != null) || (root != null && subroot == null)){
            return false;
        }

        if(root.data != subroot.data){
            return false;
        }

        return isSimilar(root.left, subroot.left) && isSimilar(root.right, subroot.right);
    }

    public static void main(String[] args) {
        SubtreeOfAnotherTree subtreeOfAnotherTree = new SubtreeOfAnotherTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(5);

        TreeNode subroot = new TreeNode(4);
        subroot.left = new TreeNode(1);
        subroot.right = new TreeNode(2);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(2);
        root2.left.right.left = new TreeNode(0);
        root2.right = new TreeNode(5);


        System.out.println(subtreeOfAnotherTree.inorder(root, subroot));
        System.out.println(subtreeOfAnotherTree.inorder(root2, subroot));
    }
}
