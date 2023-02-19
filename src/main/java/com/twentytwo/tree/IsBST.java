package com.twentytwo.tree;

public class IsBST {

    //wrong implementation
    public boolean isBstWrong(TreeNode root){

        if(root == null)
            return true;

        if(root.left != null && root.left.data >= root.data)
            return false;

        if(root.right != null && root.right.data < root.data)
            return false;

        return isBstWrong(root.left) && isBstWrong(root.right);
    }


    public boolean isBstRight(TreeNode root, int min, int max){

        if(root == null)
            return true;

        if(root.data <= min || root.data >= max)
            return false;

        return isBstRight(root.left, min, root.data) && isBstRight(root.right, root.data, max);
    }

    public static void main(String[] args) {

        TreeNode root = Tree.getBST();

        IsBST isBST = new IsBST();

        System.out.println(isBST.isBstRight(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
