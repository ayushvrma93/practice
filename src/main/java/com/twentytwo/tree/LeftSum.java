package com.twentytwo.tree;

public class LeftSum {

    private int sumLeftLeaves(TreeNode root){

        if(root == null)
            return 0;

        if(root.left != null && root.left.left == null && root.left.right == null)
            return root.left.data + sumLeftLeaves(root.right);

        return sumLeftLeaves(root.left) + sumLeftLeaves(root.right);
    }

    public static void main(String[] args) {

        LeftSum leftSum = new LeftSum();
        TreeNode root = Tree.getNonSymmetricalTree();
        System.out.println(leftSum.sumLeftLeaves(root));
    }
}
